package com.studio.android.ijoke;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Command extends BroadcastReceiver{
	public static final int STATUS_PLAYING = 1;
	public static final int STATUS_PAUSED = 2;
	public static final int STATUS_STOPPED = 3;
	public static final int STATUS_INIT = 0;
	
	public static final int CMD_UPLOAD = 4;
	private int status = 0;
	public Command()
	{
		this.status = 0;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
	}
	

}
