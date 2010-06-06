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
	private int totalBRead = 0;
	private int counter = 0;
	private boolean isInterrupted;
	private Handler handler;
	private Context context;
	private String location;
	private long mediaLengthInB, mediaLengthInSeconds;
	private ProgressBar progressBar;
	private static final int INTIAL_KB_BUFFER = 96 * 10 * 1024 / 8;// assume

	// 96kbps*10secs/8bits
	// per byte
	public PlayService(Context context, Handler handler, ProgressBar progressBar) {
		this.handler = handler;
		this.context = context;
		this.progressBar = progressBar;
	}

	public void doStart(String location, long mediaLengthInB,
			long mediaLengthInSeconds) {
		this.location = location;
		this.mediaLengthInB = mediaLengthInB;
		this.mediaLengthInSeconds = mediaLengthInSeconds;
		Log.i(TAG, "mediaLengthInB:" + mediaLengthInB
				+ "MeddiaLengthInSeconds:" + mediaLengthInSeconds);
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
			sendMessage();
		}
	}

	private void sendMessage() {
		Message message = handler.obtainMessage(Consts.ERROR_PLAY);
		handler.sendMessage(message);
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
			this.sendMessage();
			return;
		}

		downloadingMediaFile = new File(context.getCacheDir(),
				"downloadingMedia.dat");
		if (downloadingMediaFile.exists()) {
			downloadingMediaFile.delete();
		}
		FileOutputStream out = new FileOutputStream(downloadingMediaFile);
		byte buf[] = new byte[16384];
		int totalBytesRead = 0;
		do {
			int numread = stream.read(buf);
			if (numread <= 0)
				break;
			out.write(buf, 0, numread);
			totalBytesRead += numread;
			totalBRead = totalBytesRead;
			fireDataLoadUpdate();
			testMediaBuffer();
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
					if (totalBRead >= INTIAL_KB_BUFFER) {
						try {
							Log.i(TAG, "prepare for play!");
							Message m = handler
									.obtainMessage(Consts.MSG_START_PLAY);
							handler.sendMessage(m);
							startPlayer();
						} catch (Exception e) {
							Log.e(getClass().getName(),
									"Error copying buffered conent.", e);
							sendMessage();
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
		if (mp != null) {
			if (mp.isPlaying())
				mp.stop();
			mp.reset();
			mp.release();
			mp = null;
		}
	}

	public void pausePlayer() {
		if (mp != null) {
			if (mp.isPlaying())
				mp.pause();
		}
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
			boolean wasPlaying = true;
			int curPosition = 0;
			if (mp != null) {
				wasPlaying = mp.isPlaying();
				curPosition = mp.getCurrentPosition();
			}

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
			if (mp != null) {
				if (mp.isPlaying())
					mp.stop();
				mp.reset();
				mp.release();
				mp = null;
			}

			// Create a new MediaPlayer rather than try to re-prepare the prior
			// one.
			mp = createMediaPlayer(bufferedFile);
			if (wasPlaying)
				mp.seekTo(curPosition);

			// Restart if at end of prior buffered content or mediaPlayer was
			// previously playing.
			// NOTE: We test for < 1second of data because the media player can
			// stop when there is still
			// a few milliseconds of data left to play
			boolean atEndOfFile = mp.getDuration() - mp.getCurrentPosition() <= 1000;
			if (wasPlaying || atEndOfFile) {
				if (validateNotInterrupted())
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

	private MediaPlayer createMediaPlayer(File mediaFile) throws Exception {
		MediaPlayer mPlayer = new MediaPlayer();
		mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.e(getClass().getName(), "Error in MediaPlayer: (" + what
						+ ") with extra (" + extra + ")");
				sendMessage();
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
			if (mp == null) {
				File bufferedFile = new File(context.getCacheDir(),
						"playingMedia" + (counter) + ".dat");
				// We double buffer the data to avoid potential read/write
				// errors
				// that could happen if the
				// download thread attempted to write at the same time the
				// MediaPlayer was trying to read.
				// For example, we can't guarantee that the MediaPlayer won't
				// open a
				// file for playing and leave it locked while
				// the media is playing. This would permanently deadlock the
				// file
				// download. To avoid such a deadloack,
				// we move the currently loaded data to a temporary buffer file
				// that
				// we start playing while the remaining
				// data downloads.
				moveFile(downloadingMediaFile, bufferedFile);

				Log.i(getClass().getName(), "Buffered File path: "
						+ bufferedFile.getAbsolutePath());
				Log.i(getClass().getName(), "Buffered File length: "
						+ bufferedFile.length() + "");

				mp = createMediaPlayer(bufferedFile);
			}
			// We have pre-loaded enough content and started the MediaPlayer so
			// update the buttons & progress meters.
			mp.start();
			startPlayProgressUpdater();
		} catch (Exception e) {
			Log.e(getClass().getName(), "Error initializing the MediaPlayer.",
					e);
			sendMessage();
		}
	}

	private boolean validateNotInterrupted() {
		if (isInterrupted) {
			if (mp != null) {
				if (mp.isPlaying())
					mp.stop();
				mp.reset();
				mp.release();
				mp = null;
			}
			downloadingMediaFile.delete();
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
				float loadProgress = ((float) totalBRead / (float) mediaLengthInB);
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
		if (mp != null) {
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
	}

}
