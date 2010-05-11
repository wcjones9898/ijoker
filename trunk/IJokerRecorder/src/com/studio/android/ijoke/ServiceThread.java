package com.studio.android.ijoke;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

public class ServiceThread  extends BroadcastReceiver{

	private final Object mLock = new Object();
	private Looper mLooper = null;
	private int recorderState = -1;
	RecorderService recorderService = new RecorderService();
	public ServiceThread(String name)
	{
		//Thread t = new Thread (null, this, name);
		//t.setPriority(Thread.MIN_PRIORITY);
		//t.start();
//		synchronized (mLock)
//		{
//			while(mLooper == null)
//			{
//				try
//				{
//					mLock.wait();
//				}
//				catch(InterruptedException ex)
//				{
//					
//				}
//			}
//		}
	}

	public void run()
	{
		
		synchronized (mLock) {
			Looper.prepare();
			mLooper = Looper.myLooper();
			this.recorderService.notifyRecorder(this.recorderState);
			mLock.notifyAll();
		}
		Looper.loop();
		
		
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("test",""+intent.getIntExtra("cmd", -1));
		this.recorderService.notifyRecorder(intent.getIntExtra("cmd", -1));
	}
	
	
}
