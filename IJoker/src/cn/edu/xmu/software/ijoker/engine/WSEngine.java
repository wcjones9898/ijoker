package cn.edu.xmu.software.ijoker.engine;

import java.util.HashMap;

import android.os.Handler;
import android.util.Log;
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
		try {
			AbstractWSMethod abstractWSMethod = CallWSMethodFactory
					.CreateWSMethod(methodName);
			abstractWSMethod.invokeWSMethod(handler, parms);
		} catch (InstantiationException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IllegalAccessException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}
}
