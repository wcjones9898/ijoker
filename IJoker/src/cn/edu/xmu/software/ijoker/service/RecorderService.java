package cn.edu.xmu.software.ijoker.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class RecorderService extends Service {
	public final String TAG = RecorderService.class.getName();
	private File currentRecord;
	private MediaRecorder mRecorder = null;
	private MediaPlayer mediaPlayer = new MediaPlayer();
	private int recorderState = 0;
	private int playerState = 0;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		mediaPlayer.stop();
		mediaPlayer.release();
		mRecorder.stop();
		mRecorder.release();
		if (currentRecord != null)
			currentRecord.delete();
		super.onDestroy();
	}

	private final IRecordService.Stub mBinder = new IRecordService.Stub() {

		@Override
		public String getCurrentRecord() throws RemoteException {
			// TODO Auto-generated method stub
			if (currentRecord != null)
				return currentRecord.getAbsolutePath();
			return null;
		}

		@Override
		public int getPlayerState() throws RemoteException {
			// TODO Auto-generated method stub
			return playerState;
		}

		@Override
		public int getRecorderState() throws RemoteException {
			// TODO Auto-generated method stub
			return recorderState;
		}

		@Override
		public void startPlayer() throws RemoteException {
			if (playerState == 3 || playerState == 0) {
				FileInputStream fis;
				try {
					fis = new FileInputStream(currentRecord);
					mediaPlayer.setDataSource(fis.getFD());
					mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {
							playerState = 1;
							mediaPlayer.start();
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
			} else if (playerState == 1)
				return;
		}

		@Override
		public void startRecorder() throws RemoteException {
			if (recorderState == 3 || recorderState == 0) {
				if (mRecorder != null) {
					mRecorder.stop();
					mRecorder.release();
					mRecorder = null;
				}
				mRecorder = new MediaRecorder();
				if (mRecorder != null) {
					mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					mRecorder
							.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
					mRecorder
							.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				}
				// mRecorder.reset();
				try {
					if (currentRecord != null)
						currentRecord.delete();
					currentRecord = File.createTempFile("record", ".amr",
							Environment.getExternalStorageDirectory());
					Log.i("record path", currentRecord.getPath());
				} catch (IOException e1) {
					Log.e(TAG, e1.getMessage(), e1);
				}
				mRecorder.setOutputFile(currentRecord.getAbsolutePath());
				try {
					mRecorder.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				recorderState = 1;
				mRecorder.start();
			} else if (recorderState == 1)
				return;
		}

		@Override
		public void stopPlayer() throws RemoteException {
			if (!mediaPlayer.isPlaying())
				return;
			playerState = 3;
			mediaPlayer.stop();
		}

		@Override
		public void stopRecorder() throws RemoteException {
			recorderState = 3;
			mRecorder.stop();
		}

	};

	// public void uploadFile(String userId, String jokeTitle, String keyword) {
	// uploader = new Uploader(handler);
	// uploader.doStart(currentRecord, jokeTitle, keyword, userId,
	// lengthinsecond);
	// }

	// public void startRecordProgressUpdater() {
	// ++lengthinsecond;
	// float progress = (float) lengthinsecond / 300;
	// progressBar.setProgress((int) (progress * 100));
	// if (isRecording) {
	// Runnable notification = new Runnable() {
	// public void run() {
	// startRecordProgressUpdater();
	// }
	// };
	// handler.postDelayed(notification, 1000);
	// }
	// }

	// public void startPlayProgressUpdater() {
	// if (isPlaying) {
	// float progress = ((float) mediaPlayer.getCurrentPosition() / 1000)
	// / lengthinsecond;
	// progressBar.setProgress((int) (progress * 100));
	// Runnable notification = new Runnable() {
	// public void run() {
	// startPlayProgressUpdater();
	// }
	// };
	// handler.postDelayed(notification, 1000);
	// }
	// }

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}
}
