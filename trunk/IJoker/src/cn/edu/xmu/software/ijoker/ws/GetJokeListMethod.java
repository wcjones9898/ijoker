package cn.edu.xmu.software.ijoker.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.util.WSUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class GetJokeListMethod extends AbstractWSMethod {

	@Override
	public void invokeWSMethod(Handler handler, HashMap<String, Object> parms) {
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
