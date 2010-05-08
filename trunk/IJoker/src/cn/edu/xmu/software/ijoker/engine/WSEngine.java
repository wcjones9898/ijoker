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
		AbstractWSMethod abstractWSMethod;
		try {
			abstractWSMethod = CallWSMethodFactory.CreateWSMethod(methodName,
					handler, parms);
			abstractWSMethod.invokeWSMethod();
		} catch (ClassNotFoundException e) {
			// TODO add message to handler;
			Log.e(TAG, e.getMessage(), e);
		}
	}
}
