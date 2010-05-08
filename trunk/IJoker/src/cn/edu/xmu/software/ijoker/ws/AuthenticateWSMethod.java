package cn.edu.xmu.software.ijoker.ws;

import java.util.HashMap;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.util.Consts;

public class AuthenticateWSMethod extends AbstractWSMethod {

	public AuthenticateWSMethod(String methodName, Handler handler,
			HashMap<String, Object> parms) {
		super(methodName, handler, parms);
	}

	@Override
	public void invokeWSMethod() {
		// ArrayList<Joke> list = null;
		// SoapObject result = WSUtils.callWebService(this.methodName, parms);
		// if (result != null && result.getPropertyCount() > 0) {
		// list = (ArrayList<Joke>) result.getProperty("getJokeListReturn");
		// Log.i(TAG, "get data from webservice with method: "
		// + this.methodName + "\nget result: " + list.toString());
		// }
		// 生成动态数组，加入数据

		// construct the message;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message message = Message.obtain(handler, Consts.MSG_LOGIN_READY, 0, 0);
		handler.sendMessage(message);
		Log.i(TAG, "send login ready message to loading!");
	}
}
