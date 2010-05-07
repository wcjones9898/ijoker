package cn.edu.xmu.software.ijoker.ws;

import java.util.ArrayList;
import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.util.WSUtils;

public class GetJokeListWSMethod extends AbstractWSMethod {
	public GetJokeListWSMethod(String methodName, Handler handler,
			HashMap<String, Object> parms) {
		this.methodName = methodName;
		this.handler = handler;
		this.parms = parms;
	}

	@Override
	public void invokeWSMethod() {
		SoapObject result = WSUtils.callWebService(this.methodName, parms);
		ArrayList<Joke> list = null;
		if (result != null && result.getPropertyCount() > 0) {
			list = (ArrayList<Joke>) result.getProperty("getJokeListReturn");
			Log.i(TAG, "get data from webservice with method: "
					+ this.methodName + "\nget result: " + list.toString());
		}
		// construct the message;
		Message message = handler.obtainMessage();
		Bundle b = new Bundle();
		b.putParcelableArrayList("data", list);
		message.setData(b);
		handler.sendMessage(message);

	}
}
