package cn.edu.xmu.software.ijoker.ws;

import java.util.HashMap;

import android.os.Handler;
import cn.edu.xmu.software.ijoker.exception.CallWebServiceException;

public abstract class AbstractWSMethod {
	protected String methodName;
	protected Handler handler;
	protected HashMap<String, Object> parms;
	protected final String TAG = AbstractWSMethod.class.getName();

	public AbstractWSMethod(String methodName, Handler handler,
			HashMap<String, Object> parms) {
		this.methodName = methodName;
		this.handler = handler;
		this.parms = parms;
	}

	public abstract void invokeWSMethod() throws CallWebServiceException;
}
