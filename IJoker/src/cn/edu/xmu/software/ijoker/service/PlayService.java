package cn.edu.xmu.software.ijoker.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import cn.edu.xmu.software.ijoker.util.Consts;

public class PlayService extends Thread {
	public final String TAG = PlayService.class.getName();
	private MediaPlayer mp = null;
	private File downloadingMediaFile;
	private int totalKbRead = 0;
	private int counter = 0;
	private boolean isInterrupted;
	private Handler handler;
	private Context context;
	private String location;
	private long mediaLengthInKb, mediaLengthInSeconds;
	private ProgressBar progressBar;
	private static final int INTIAL_KB_BUFFER = 8 * 10 / 8;// assume

	// 96kbps*10secs/8bits
	// per byte
	public PlayService(Context context, Handler handler, ProgressBar progressBar) {
		this.handler = handler;
		this.context = context;
		this.progressBar = progressBar;
	}

	public void doStart(String location, long mediaLengthInKb,
			long mediaLengthInSeconds) {
		this.location = location;
		this.mediaLengthInKb = mediaLengthInKb;
		this.mediaLengthInSeconds = mediaLengthInSeconds;
		this.start();
	}

	@Override
	/**
	 * Progressivly download the media to a temporary location and update the
	 * MediaPlayer as new content becomes available.
	 */
	public void run() {
		super.run();
		try {
			downloadAudioIncrement(location);
		} catch (Exception e) {
			Log.e(getClass().getName(),
					"Unable to initialize the MediaPlayer for fileUrl="
							+ location, e);
			Message message = handler.obtainMessage(Consts.ERROR_PLAY);
			handler.sendMessage(message);
		}
	}

	/**
	 * Download the url stream to a temporary location and then call the
	 * setDataSource for that local file
	 */
	public void downloadAudioIncrement(String mediaUrl) throws IOException {

		URLConnection cn = new URL(mediaUrl).openConnection();
		Log.i(TAG, "connet to the url:" + mediaUrl);
		cn.connect();
		InputStream stream = cn.getInputStream();
		if (stream == null) {
			Log.e(getClass().getName(),
					"Unable to create InputStream for mediaUrl:" + mediaUrl);
		}

		downloadingMediaFile = new File(context.getCacheDir(),
				"downloadingMedia.dat");
		if (downloadingMediaFile.exists()) {
			downloadingMediaFile.delete();
		}
		FileOutputStream out = new FileOutputStream(downloadingMediaFile);
		byte buf[] = new byte[16384];
		int totalBytesRead = 0, incrementalBytesRead = 0;
		do {
			int numread = stream.read(buf);
			if (numread <= 0)
				break;
			out.write(buf, 0, numread);
			totalBytesRead += numread;
			incrementalBytesRead += numread;
			totalKbRead = totalBytesRead / 1000;
			testMediaBuffer();
			fireDataLoadUpdate();
		} while (validateNotInterrupted());
		stream.close();
		if (validateNotInterrupted()) {
			fireDataFullyLoaded();
		}
	}

	/**
	 * Test whether we need to transfer buffered data to the MediaPlayer.
	 * Interacting with MediaPlayer on non-main UI thread can causes crashes to
	 * so perform this using a Handler.
	 */
	private void testMediaBuffer() {
		Runnable updater = new Runnable() {
			public void run() {
				if (mp == null) {
					// Only create the MediaPlayer once we have the minimum
					// buffered data
					if (totalKbRead >= INTIAL_KB_BUFFER) {
						try {
							Log.i(TAG, "prepare for play!");
							Message m = handler
									.obtainMessage(Consts.MSG_PREPARE_PLAY);
							handler.sendMessage(m);
						} catch (Exception e) {
							Log.e(getClass().getName(),
									"Error copying buffered conent.", e);
						}
					}
				} else if (mp.getDuration() - mp.getCurrentPosition() <= 1000) {
					// NOTE: The media player has stopped at the end so transfer
					// any existing buffered data
					// We test for < 1second of data because the media player
					// can stop when there is still
					// a few milliseconds of data left to play
					transferBufferToMediaPlayer();
				}
			}
		};
		handler.post(updater);
	}

	public void stopPlayer() {
		isInterrupted = true;
		validateNotInterrupted();
	}

	/**
	 * Transfer buffered data to the MediaPlayer. NOTE: Interacting with a
	 * MediaPlayer on a non-main UI thread can cause thread-lock and crashes so
	 * this method should always be called using a Handler.
	 */
	private void transferBufferToMediaPlayer() {
		try {
			// First determine if we need to restart the player after
			// transferring data...e.g. perhaps the user pressed pause
			boolean wasPlaying = mp.isPlaying();
			int curPosition = mp.getCurrentPosition();

			// Copy the currently downloaded content to a new buffered File.
			// Store the old File for deleting later.
			File oldBufferedFile = new File(context.getCacheDir(),
					"playingMedia" + counter + ".dat");
			File bufferedFile = new File(context.getCacheDir(), "playingMedia"
					+ (counter++) + ".dat");

			// This may be the last buffered File so ask that it be delete on
			// exit. If it's already deleted, then this won't mean anything. If
			// you want to
			// keep and track fully downloaded files for later use, write
			// caching code and please send me a copy.
			bufferedFile.deleteOnExit();
			moveFile(downloadingMediaFile, bufferedFile);

			// Pause the current player now as we are about to create and start
			// a new one. So far (Android v1.5),
			// this always happens so quickly that the user never realized we've
			// stopped the player and started a new one
			mp.pause();

			// Create a new MediaPlayer rather than try to re-prepare the prior
			// one.
			mp = createMediaPlayer(bufferedFile);
			mp.seekTo(curPosition);

			// Restart if at end of prior buffered content or mediaPlayer was
			// previously playing.
			// NOTE: We test for < 1second of data because the media player can
			// stop when there is still
			// a few milliseconds of data left to play
			boolean atEndOfFile = mp.getDuration() - mp.getCurrentPosition() <= 1000;
			if (wasPlaying || atEndOfFile) {
				mp.start();
			}

			// Lastly delete the previously playing buffered File as it's no
			// longer needed.
			oldBufferedFile.delete();

		} catch (Exception e) {
			Log.e(getClass().getName(),
					"Error updating to newly loaded content.", e);
		}
	}

	private MediaPlayer createMediaPlayer(File mediaFile) throws IOException {
		MediaPlayer mPlayer = new MediaPlayer();
		mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.e(getClass().getName(), "Error in MediaPlayer: (" + what
						+ ") with extra (" + extra + ")");
				return false;
			}
		});

		// It appears that for security/permission reasons, it is better to pass
		// a FileDescriptor rather than a direct path to the File.
		// Also I have seen errors such as "PVMFErrNotSupported" and
		// "Prepare failed.: status=0x1" if a file path String is passed to
		// setDataSource(). So unless otherwise noted, we use a FileDescriptor
		// here.
		FileInputStream fis = new FileInputStream(mediaFile);
		mPlayer.setDataSource(fis.getFD());
		mPlayer.prepare();
		return mPlayer;
	}

	public void startPlayer() {
		try {
			File bufferedFile = new File(context.getCacheDir(), "playingMedia"
					+ (counter++) + ".dat");
			// We double buffer the data to avoid potential read/write errors
			// that could happen if the
			// download thread attempted to write at the same time the
			// MediaPlayer was trying to read.
			// For example, we can't guarantee that the MediaPlayer won't open a
			// file for playing and leave it locked while
			// the media is playing. This would permanently deadlock the file
			// download. To avoid such a deadloack,
			// we move the currently loaded data to a temporary buffer file that
			// we start playing while the remaining
			// data downloads.
			moveFile(downloadingMediaFile, bufferedFile);

			Log.i(getClass().getName(), "Buffered File path: "
					+ bufferedFile.getAbsolutePath());
			Log.i(getClass().getName(), "Buffered File length: "
					+ bufferedFile.length() + "");

			mp = createMediaPlayer(bufferedFile);

			// We have pre-loaded enough content and started the MediaPlayer so
			// update the buttons & progress meters.
			mp.start();
			startPlayProgressUpdater();
		} catch (IOException e) {
			Log.e(getClass().getName(), "Error initializing the MediaPlayer.",
					e);
			return;
		}
	}

	private boolean validateNotInterrupted() {
		if (isInterrupted) {
			if (mp != null) {
				mp.pause();
				// mp.release();
			}
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Move the file in oldLocation to newLocation.
	 */
	public void moveFile(File oldLocation, File newLocation) throws IOException {

		if (oldLocation.exists()) {
			BufferedInputStream reader = new BufferedInputStream(
					new FileInputStream(oldLocation));
			BufferedOutputStream writer = new BufferedOutputStream(
					new FileOutputStream(newLocation, false));
			try {
				byte[] buff = new byte[8192];
				int numChars;
				while ((numChars = reader.read(buff, 0, buff.length)) != -1) {
					writer.write(buff, 0, numChars);
				}
			} catch (IOException ex) {
				throw new IOException("IOException when transferring "
						+ oldLocation.getPath() + " to "
						+ newLocation.getPath());
			} finally {
				try {
					if (reader != null) {
						writer.close();
						reader.close();
					}
				} catch (IOException ex) {
					Log.e(getClass().getName(),
							"Error closing files when transferring "
									+ oldLocation.getPath() + " to "
									+ newLocation.getPath());
				}
			}
		} else {
			throw new IOException(
					"Old location does not exist when transferring "
							+ oldLocation.getPath() + " to "
							+ newLocation.getPath());
		}
	}

	private void fireDataLoadUpdate() {
		Runnable updater = new Runnable() {
			public void run() {
				float loadProgress = ((float) totalKbRead / (float) mediaLengthInKb);
				progressBar.setSecondaryProgress((int) (loadProgress * 100));
			}
		};
		handler.post(updater);
	}

	private void fireDataFullyLoaded() {
		Runnable updater = new Runnable() {
			public void run() {
				transferBufferToMediaPlayer();
				// Delete the downloaded File as it's now been transferred to
				// the currently playing buffer file.
				downloadingMediaFile.delete();
			}
		};
		handler.post(updater);
	}

	public void startPlayProgressUpdater() {
		float progress = (((float) mp.getCurrentPosition() / 1000) / mediaLengthInSeconds);
		progressBar.setProgress((int) (progress * 100));

		if (mp.isPlaying()) {
			Runnable notification = new Runnable() {
				public void run() {
					startPlayProgressUpdater();
				}
			};
			handler.postDelayed(notification, 1000);
		}
	}
	// private Handler handler = new Handler() {
	//
	// @Override
	// public void handleMessage(Message msg) {
	// super.handleMessage(msg);
	// switch (msg.what) {
	// case Consts.CMD_PAUSE:
	// mp.pause();
	// break;
	// case Consts.CMD_PLAY:
	// Joke newJoke = msg.getData().getParcelable("data");
	// playJoke(newJoke);
	// break;
	// case Consts.CMD_STOP:
	// if (mp != null) {
	// mp.stop();
	// mp.release();
	// mp = null;
	// }
	// break;
	// default:
	// }
	//
	// }
	//
	// };
	//
	// private void playJoke(Joke newJoke) {
	// try {
	//
	// Log.i("play a new joke: ", newJoke.getLocation());
	// playingJoke = newJoke;
	// if (mp == null)
	// mp = new MediaPlayer();
	// else
	// mp.reset();
	// mp.setDataSource(playingJoke.getLocation());
	// mp.setOnPreparedListener(new OnPreparedListener() {
	//
	// @Override
	// public void onPrepared(MediaPlayer mp) {
	// mp.start();
	// }
	//
	// });
	// mp.prepare();
	// mp.setOnCompletionListener(new OnCompletionListener() {
	// public void onCompletion(MediaPlayer arg0) {
	// if (mp != null) {
	// mp.release();
	// mp = null;
	// }
	// Intent intent = new Intent(Consts.ACTION_STOP_PLAY);
	// sendBroadcast(intent);
	// }
	// });
	// mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
	//
	// @Override
	// public boolean onError(MediaPlayer mp, int what, int extra) {
	// Log.e(TAG, "MediaPlayer error!");
	// if (mp != null) {
	// mp.release();
	// mp = null;
	// }
	// Intent intent = new Intent(Consts.ACTION_STOP_PLAY);
	// sendBroadcast(intent);
	// return false;
	// }
	// });
	// } catch (Exception e) {
	// Log.e(TAG, e.getMessage(), e);
	// if (mp != null) {
	// mp.stop();
	// mp.release();
	// mp = null;
	// }
	// Intent intent = new Intent(Consts.ACTION_ERROR_PLAY);
	// sendBroadcast(intent);
	// }
	// }

	// private final IPlayService.Stub mBinder = new IPlayService.Stub() {
	//
	// @Override
	// public void pause() throws RemoteException {
	// Message message = handler.obtainMessage(Consts.CMD_PAUSE);
	// handler.sendMessage(message);
	// }
	//
	// @Override
	// public void stop() throws RemoteException {
	// Message message = handler.obtainMessage(Consts.CMD_STOP);
	// handler.sendMessage(message);
	//
	// }
	//
	// @Override
	// public void play(Joke joke) throws RemoteException {
	// Message message = handler.obtainMessage(Consts.CMD_PLAY);
	// Bundle b = new Bundle();
	// b.putParcelable("data", joke);
	// message.setData(b);
	// handler.sendMessage(message);
	//
	// }
	//
	// @Override
	// public Joke getJokePlaying() throws RemoteException {
	// return playingJoke;
	// }
	//
	// @Override
	// public boolean isPlaying() throws RemoteException {
	// if (mp != null) {
	// Log.i(TAG, "the mediaplayer playing status: " + mp.isPlaying());
	// return mp.isPlaying();
	// } else
	// return false;
	// }
	//
	// };

}
