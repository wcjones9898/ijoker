package com.studio.android.ijoke;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class ErrorHandler extends Handler {
	public ErrorHandler(Looper looper)
	{
		super(looper);
	}
	public void handleMessage(Message msg)
	{
		// 线程所做的处理
	}

}
