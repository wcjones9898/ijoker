package cn.edu.xmu.software.ijoker.ws;

import java.util.HashMap;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.exception.CallWebServiceException;
import cn.edu.xmu.software.ijoker.util.Consts;
import cn.edu.xmu.software.ijoker.util.WSUtils;

public class RegisterWSMethod extends AbstractWSMethod {

	public RegisterWSMethod(String methodName, Handler handler,
			HashMap<String, Object> parms) {
		super(methodName, handler, parms);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void invokeWSMethod() throws CallWebServiceException {
		String userId = null;
//		SoapObject result = null;
		try {
			userId = WSUtils.callWebService(this.methodName, parms).toString();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			throw new CallWebServiceException(e.getMessage(), e);
		}
//		if (result != null && result.getPropertyCount() > 0) {
//			Log.i(TAG, "get data from webservice with method: "
//					+ this.methodName + "\nget result: " + result.toString());
//			try {
//				SoapObject o = (SoapObject) result
//						.getProperty(Consts.REGISTERSERVICERETURN);
//				userId = o.toString();
//				Log.i(TAG, "get data from webservice with method: "
//						+ this.methodName + "\nget result: " + userId);
//			} catch (Exception e) {
//				// TODO send error message.
//				Log.e(TAG, e.getMessage(), e);
//			}
//		}
		Message message = Message.obtain(handler, Consts.MSG_REGISTER_READY);
		int wsResult, error;
		if (userId != null) {
			wsResult = Consts.FLAG_REGISTER_SUCCESS;
			error = Consts.ERROR_NOERROR;
			// Bundle b = new Bundle();
			// b.putString("userId", userId);
			// message.setData(b);
		} else {
			wsResult = Consts.FLAG_REGISTER_FAILURE;
			error = Consts.ERROR_USERNAME_EXIST;
		}
		message.arg1 = wsResult;
		message.arg2 = error;
		handler.sendMessage(message);
		Log.i(TAG, "send register successful message to register!");
	}

}
