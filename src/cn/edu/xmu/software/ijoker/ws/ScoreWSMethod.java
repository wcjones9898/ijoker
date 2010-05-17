package cn.edu.xmu.software.ijoker.ws;

import java.util.HashMap;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.exception.CallWebServiceException;
import cn.edu.xmu.software.ijoker.util.Consts;
import cn.edu.xmu.software.ijoker.util.WSUtils;

public class ScoreWSMethod extends AbstractWSMethod {

	public ScoreWSMethod(String methodName, Handler handler,
			HashMap<String, Object> parms) {
		super(methodName, handler, parms);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void invokeWSMethod() throws CallWebServiceException {
		try {
			WSUtils.callWebService(this.methodName, parms);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			throw new CallWebServiceException(e.getMessage(), e);
		}
		Message message = Message.obtain(handler, Consts.MSG_LIKE_SUCCEED);
		handler.sendMessage(message);
		Log.i(TAG, "send make score succeed!");
	}

}
