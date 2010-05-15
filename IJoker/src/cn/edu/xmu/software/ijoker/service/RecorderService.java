package cn.edu.xmu.software.ijoker.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.engine.Uploader;
import cn.edu.xmu.software.ijoker.util.Consts;

public class RecorderService {
	public final String TAG = RecorderService.class.getName();
	private MediaRecorder mRecorder = null;
	private File currentRecord;
	private Handler handler;
	private Uploader uploader;
	private MediaPlayer mediaPlayer = null;

	public RecorderService(Handler handler) {
		this.handler = handler;
	}

	public void initRecorder() {
		if (mRecorder != null) {
			mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
			mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			try {
				currentRecord = File.createTempFile("record", ".amr",
						Environment.getExternalStorageDirectory());
				Log.i("currentRecord path:", currentRecord.getPath());
			} catch (IOException e) {
				Log.e(TAG, e.getMessage(), e);
			}
			mRecorder.setOutputFile(currentRecord.getAbsolutePath());
			try {
				mRecorder.prepare();
			} catch (IllegalStateException e) {
				Log.e(TAG, e.getMessage(), e);
			} catch (IOException e) {
				Log.e(TAG, e.getMessage(), e);
			}
		}
	}

	public void clearRecord() {
		if (currentRecord != null)
			currentRecord.deleteOnExit();
	}

	public void stopListen() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	public void stopRecord() {
		if (mRecorder != null) {
			mRecorder.stop();
			mRecorder.release();
			mRecorder = null;
		}
	}

	public void uploadFile(String userId, String jokeTitle, String keyword) {
		uploader = new Uploader(handler);
		uploader.doStart(currentRecord, jokeTitle, keyword, userId);
		if (currentRecord != null)
			currentRecord.deleteOnExit();
	}

	public void listenRecord() {
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.stop();
				mp.release();
				mp = null;
				Message message = handler
						.obtainMessage(Consts.STATUS_LISTEN_STOP);
				Bundle b = new Bundle();
				message.setData(b);
				handler.sendMessage(message);
			}
		});
		FileInputStream fis;
		try {
			fis = new FileInputStream(currentRecord);
			mediaPlayer.setDataSource(fis.getFD());
			mediaPlayer.prepare();
		} catch (FileNotFoundException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IllegalStateException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		}
		mediaPlayer.start();
	}

	public void startRecord() {
		stopRecord();
		if (currentRecord != null)
			currentRecord.deleteOnExit();
		Log.i("Start Rec", "MessageManager");
		mRecorder = new MediaRecorder();
		initRecorder();
		mRecorder.start();
	}

}
