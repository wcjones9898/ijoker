package cn.edu.xmu.software.ijoker.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaRecorder.OnInfoListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import cn.edu.xmu.software.ijoker.engine.Uploader;
import cn.edu.xmu.software.ijoker.util.Consts;

public class RecorderService {
	public final String TAG = RecorderService.class.getName();
	private MediaRecorder mRecorder = null;
	private File currentRecord;
	private Handler handler;
	private Uploader uploader;
	private boolean isRecording;
	private long lengthinsecond = 0;
	private ProgressBar progressBar;
	private MediaPlayer mediaPlayer = null;
	private Context context;
	private boolean isPlaying;

	public RecorderService(Context context, Handler handler,
			ProgressBar progressBar) {
		this.handler = handler;
		this.context = context;
		this.progressBar = progressBar;
	}

	public void initRecorder() {
		if (mRecorder != null) {
			mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
			mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			mRecorder.setMaxDuration(300000);
			mRecorder.setOnInfoListener(new OnInfoListener() {

				@Override
				public void onInfo(MediaRecorder mr, int what, int extra) {
					if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED)
						if (mr != null) {
							mr.stop();
							mr.release();
							mr = null;
						}
					isRecording = false;
					Message message = handler
							.obtainMessage(Consts.MSG_RECORD_TIMEUP);
					handler.sendMessage(message);
				}

			});
			// currentRecord = File.createTempFile("record", ".amr",
			// Environment.getExternalStorageDirectory());
			currentRecord = new File(context.getCacheDir(), "record.amr");
			if (currentRecord.exists()) {
				currentRecord.delete();
			}
			Log.i("currentRecord path:", currentRecord.getPath());
			mRecorder.setOutputFile(currentRecord.getAbsolutePath());
			lengthinsecond = 0;
			try {
				mRecorder.prepare();
			} catch (IllegalStateException e) {
				Log.e(TAG, e.getMessage(), e);
			} catch (IOException e) {
				Log.e(TAG, e.getMessage(), e);
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}
		}
	}

	public void clearRecord() {
		if (currentRecord != null) {
			currentRecord.delete();
			currentRecord = null;
		}
		Log.i(TAG, "file has been deleted: " + currentRecord);
	}

	public void stopListen() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
		isPlaying = false;
	}

	public void stopRecord() {
		if (mRecorder != null) {
			mRecorder.stop();
			mRecorder.release();
			mRecorder = null;
		}
		isRecording = false;
	}

	public void uploadFile(String userId, String jokeTitle, String keyword) {
		uploader = new Uploader(handler);
		uploader.doStart(currentRecord, jokeTitle, keyword, userId,
				lengthinsecond);
	}

	public boolean isRecording() {
		return isRecording;
	}

	public void setRecording(boolean isRecording) {
		this.isRecording = isRecording;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public void listenRecord() {
		// stopListen();
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				if (mp != null) {
					mp.stop();
					mp.release();
					mp = null;
				}
				isPlaying = false;
				Message message = handler
						.obtainMessage(Consts.STATUS_LISTEN_STOP);
				handler.sendMessage(message);
			}
		});
		FileInputStream fis;
		try {
			fis = new FileInputStream(currentRecord);
			mediaPlayer.setDataSource(fis.getFD());
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer mp) {
					mediaPlayer.start();
					isPlaying = true;
					startPlayProgressUpdater();

				}

			});
			mediaPlayer.prepare();
		} catch (FileNotFoundException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IllegalStateException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}

	public void startRecord() {
		stopRecord();
		if (currentRecord != null)
			currentRecord.delete();
		Log.i("Start Rec", "MessageManager");
		mRecorder = new MediaRecorder();
		initRecorder();
		mRecorder.start();
		isRecording = true;
		startRecordProgressUpdater();
	}

	public void startRecordProgressUpdater() {
		++lengthinsecond;
		float progress = (float) lengthinsecond / 300;
		progressBar.setProgress((int) (progress * 100));
		if (isRecording) {
			Runnable notification = new Runnable() {
				public void run() {
					startRecordProgressUpdater();
				}
			};
			handler.postDelayed(notification, 1000);
		}
	}

	public void startPlayProgressUpdater() {
		if (isPlaying) {
			float progress = ((float) mediaPlayer.getCurrentPosition() / 1000)
					/ lengthinsecond;
			progressBar.setProgress((int) (progress * 100));
			Runnable notification = new Runnable() {
				public void run() {
					startPlayProgressUpdater();
				}
			};
			handler.postDelayed(notification, 1000);
		}
	}
}
