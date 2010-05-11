package com.studio.android.ijoke;

import com.studio.android.ijoke.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RecorderActivity extends Activity implements OnErrorListener,OnCompletionListener{

	private ServiceThread service;
	private RecorderService recorderService = new RecorderService();
	IntentFilter filter = new IntentFilter();
	//����Intent
	Intent CMDIntent = new Intent("com.studio.android.ijoke.recorderThread");
	
	public void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.engine);
		filter.addAction("com.studio.android.ijoke.recorderThread");
                filter.addCategory(Intent.CATEGORY_DEFAULT);
		service = new ServiceThread("recorderService");
		registerReceiver(service, filter);
		
		
		//��������Intent
		CMDIntent.addCategory(Intent.CATEGORY_DEFAULT);
		//ע�Ქ�Ű�ť
		Button btnStart =(Button)findViewById(R.id.BTN_START);
		btnStart.setOnClickListener(new View.OnClickListener() {  
			public void onClick(View v) {  

				CMDIntent.putExtra("cmd", Command.STATUS_PLAYING);
				//recorderService.notifyRecorder(Command.STATUS_PLAYING);
				RecorderActivity.this.sendBroadcast(CMDIntent);
				Log.i("test", "call another service");
			}  
		});  
		
		// ע��ֹͣ���Ű�ť
		Button btnStop = (Button)findViewById(R.id.BTN_STOP);
		btnStop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CMDIntent.putExtra("cmd", Command.STATUS_STOPPED);
				//recorderService.notifyRecorder(Command.STATUS_STOPPED);
				RecorderActivity.this.sendBroadcast(CMDIntent);
				Log.i("test", "call another service");
				
				// TODO Auto-generated method stub
				
			}
		});
		
		//ע���ϴ���ť
		Button btnUpload = (Button)findViewById(R.id.BTN_UPLOAD);
		btnUpload.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CMDIntent.putExtra("cmd", Command.CMD_UPLOAD);
				//recorderService.notifyRecorder(Command.STATUS_STOPPED);
				RecorderActivity.this.sendBroadcast(CMDIntent);
				Log.i("test", "call another service");
				
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		unregisterReceiver(service);
	}
	@Override
	public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
	}


	// UI�ĸ��¿���
	class updateUI extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
