package cn.edu.xmu.software.ijoker.engine;

import java.util.HashMap;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.util.Consts;
import cn.edu.xmu.software.ijoker.ws.AbstractWSMethod;
import cn.edu.xmu.software.ijoker.ws.CallWSMethodFactory;

public class WSEngine extends Thread {
	private Handler handler;

	private String methodName;
	private HashMap<String, Object> parms;
	private static final String TAG = WSEngine.class.getName();

	public WSEngine(Handler handler) {
		this.handler = handler;
	}

	// 启动线程;
	public void doStart(String methodName, HashMap<String, Object> parms) {
		this.methodName = methodName;
		this.parms = parms;
		this.start();
	}

	@Override
	public void run() {
		super.run();
		AbstractWSMethod abstractWSMethod = null;
		try {
			abstractWSMethod = CallWSMethodFactory.CreateWSMethod(methodName,
					handler, parms);
			abstractWSMethod.invokeWSMethod();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			sendMessage(e.getMessage());
		}
	}

	private void sendMessage(String detailMessage) {
		Message message = handler.obtainMessage(Consts.ERROR_CALLWEBSERVICE);
		Bundle b = new Bundle();
		b.putString("errorMessage", detailMessage);
		message.setData(b);
		handler.sendMessage(message);
	}
}
