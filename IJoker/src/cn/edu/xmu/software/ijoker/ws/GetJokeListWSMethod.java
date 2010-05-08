package cn.edu.xmu.software.ijoker.ws;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.util.Consts;

public class GetJokeListWSMethod extends AbstractWSMethod {
	public GetJokeListWSMethod(String methodName, Handler handler,
			HashMap<String, Object> parms) {
		super(methodName, handler, parms);
	}

	@Override
	public void invokeWSMethod() {
		ArrayList<Joke> list = null;
		// SoapObject result = WSUtils.callWebService(this.methodName, parms);
		// if (result != null && result.getPropertyCount() > 0) {
		// list = (ArrayList<Joke>) result.getProperty("getJokeListReturn");
		// Log.i(TAG, "get data from webservice with method: "
		// + this.methodName + "\nget result: " + list.toString());
		// }
		// 生成动态数组，加入数据
		list = new ArrayList<Joke>();
		Calendar calendar = Calendar.getInstance();
		String now = calendar.get(Calendar.YEAR) + "-"
				+ calendar.get(Calendar.MONTH) + "-"
				+ calendar.get(Calendar.DATE);
		Joke joke1 = new Joke();
		joke1.setAuthor("邱鸿斌");
		joke1.setId(1);
		joke1.setLocation("http://59.77.5.42:80/jokes/real.mp3");
		joke1.setTitle("joke1");
		joke1.setUploadTime(now);
		list.add(joke1);
		Joke joke2 = new Joke();
		joke2.setAuthor("白志斌");
		joke2.setId(2);
		joke2.setLocation("http://59.77.5.42:80/jokes/real.mp3");
		joke2.setTitle("joke2");
		joke2.setUploadTime(now);
		list.add(joke2);
		Joke joke3 = new Joke();
		joke3.setAuthor("翁晓奇");
		joke3.setId(3);
		joke3.setLocation("http://59.77.5.42:80/jokes/real.mp3");
		joke3.setTitle("joke3");
		joke3.setUploadTime(now);
		list.add(joke3);
		Log.i(TAG, "create jokeList: " + list.toString() + "; size: "
				+ list.size());
		// construct the message;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message message = handler.obtainMessage(Consts.MSG_JOKELIST_READY);
		Bundle b = new Bundle();
		b.putParcelableArrayList("data", list);
		message.setData(b);
		handler.sendMessage(message);

	}
}
