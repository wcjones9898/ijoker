package cn.edu.xmu.software.ijoker.ws;

import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.exception.CallWebServiceException;
import cn.edu.xmu.software.ijoker.util.Consts;
import cn.edu.xmu.software.ijoker.util.WSUtils;

public class AuthorizationWSMethod extends AbstractWSMethod {

	public AuthorizationWSMethod(String methodName, Handler handler,
			HashMap<String, Object> parms) {
		super(methodName, handler, parms);
	}

	@Override
	public void invokeWSMethod() throws CallWebServiceException {
		// User user = null;
		String userId = null;
		SoapObject result = null;
		try {
			result = (SoapObject) WSUtils
					.callWebService(this.methodName, parms);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			throw new CallWebServiceException(e.getMessage(), e);
		}
		if (result != null && result.getPropertyCount() > 0) {
			try {
				userId = result.getProperty(Consts.AUTHORIZATIONRETURN)
						.toString();
				// int id = Integer.parseInt(o.getProperty("id").toString());
				// String nickName = o.getProperty("nickName").toString();
				// String passWord = o.getProperty("passWord").toString();
				// userId = o.toString();
				// String userName = o.getProperty("userName").toString();
				// user = new User(id, userName, passWord, userId, nickName);
				Log.i(TAG, "get data from webservice with method: "
						+ this.methodName + "\nget result: " + userId);
			} catch (Exception e) {
				// TODO send error message.
				Log.e(TAG, e.getMessage(), e);
			}
		}
		// 生成动态数组，加入数据
		// user = new User(1, "ijoker", "123", "1", "ijoker");
		// construct the message;
		Message message = Message.obtain(handler, Consts.MSG_LOGIN_READY);
		int wsResult, error;
		if (userId != null) {
			wsResult = Consts.FLAG_LOGIN_SUCCESS;
			error = Consts.ERROR_NOERROR;
			Bundle b = new Bundle();
			b.putString("userId", userId);
			message.setData(b);
		} else {
			wsResult = Consts.FLAG_LOGIN_FAILURE;
			error = Consts.ERROR_USERNAME_NOEXIST;
		}
		message.arg1 = wsResult;
		message.arg2 = error;
		handler.sendMessage(message);
		Log.i(TAG, "send login ready message to loading!");
	}
}
