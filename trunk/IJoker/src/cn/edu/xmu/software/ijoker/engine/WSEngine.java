package cn.edu.xmu.software.ijoker.engine;

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

public class WSEngine extends Thread {
	private Handler handler;
	private final String wsdl = "http://59.77.5.181:8080/IJokerDataService/services/jokeListService?wsdl";
	private final String nameSpace = "http://59.77.5.181:8080/IJokerDataService/services/jokeListService";
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
		String data = null;
		SoapObject result = WSUtils.callWebService(this.nameSpace,
				this.methodName, this.parms, this.wsdl);
		List<Joke> list = null;
		if (result != null && result.getPropertyCount() > 0) {
			list = (List<Joke>) result.getProperty("getJokeListReturn");
			data = list.toString();
			// Log.i(TAG, "get data from webservice with method: "
			// + this.methodName + "\nget result: " + result.toString());
			Log.i(TAG, "get data from webservice with method: "
					+ this.methodName + "\nget result: " + data);
		}
		// construct the message;
		Message message = handler.obtainMessage();
		Bundle b = new Bundle();
		b.putString("data", data);
		message.setData(b);
		handler.sendMessage(message);
	}

}
