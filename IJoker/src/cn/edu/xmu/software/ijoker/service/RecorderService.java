package cn.edu.xmu.software.ijoker.service;

import java.io.File;
import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import cn.edu.xmu.software.ijoker.engine.Uploader;

public class RecorderService {
	public final String TAG = RecorderService.class.getName();
	private MediaRecorder mRecorder = null;
	private File currentRecord;
	private Handler handler;
	private Uploader uploader;

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
				e.printStackTrace();
			}
			mRecorder.setOutputFile(currentRecord.getAbsolutePath());
			try {
				mRecorder.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopRecord() {
		if (mRecorder != null) {
			mRecorder.stop();
			mRecorder.release();
			mRecorder = null;
		}
	}

	public void uploadFile() {
		uploader = new Uploader(handler);
		uploader.doStart();
	}

	public void startRecord() {
		stopRecord();
		Log.i("Start Rec", "MessageManager");
		mRecorder = new MediaRecorder();
		initRecorder();
		mRecorder.start();
	}

}
