package cn.edu.xmu.software.ijoker.ws;

import java.util.HashMap;

import android.os.Handler;

public abstract class AbstractWSMethod {
	protected String methodName;
	protected final String TAG = AbstractWSMethod.class.getName();

	public abstract void invokeWSMethod(Handler handler,
			HashMap<String, Object> parms);
}
