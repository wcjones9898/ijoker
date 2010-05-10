package cn.edu.xmu.software.ijoker.ws;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.exception.CallWebServiceException;
import cn.edu.xmu.software.ijoker.util.Consts;
import cn.edu.xmu.software.ijoker.util.WSUtils;

public class GetJokeListWSMethod extends AbstractWSMethod {
	public GetJokeListWSMethod(String methodName, Handler handler,
			HashMap<String, Object> parms) {
		super(methodName, handler, parms);
	}

	@Override
	public void invokeWSMethod() throws CallWebServiceException {
		ArrayList<Joke> list = null;
		SoapObject result = null;
		try {
			result = WSUtils.callWebService(this.methodName, parms);
			Log.i(TAG, "get data from webservice with method: "
					+ this.methodName + "\nget result: " + result.toString());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			throw new CallWebServiceException(e.getMessage(), e);
		}
		if (result != null && result.getPropertyCount() > 0) {
			Vector<SoapObject> vector = (Vector<SoapObject>) result
					.getProperty(Consts.JOKELISTRETURN);
			list = new ArrayList<Joke>();
			Iterator<SoapObject> iterator = vector.iterator();
			while (iterator.hasNext()) {
				SoapObject o = iterator.next();
				Joke joke = new Joke();
				try {
					joke.setAuthor(new String(o.getProperty("author").toString().getBytes(),  
					        "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				joke.setDislike(Integer.parseInt(o.getProperty("dislike")
						.toString()));
				joke.setId(Integer.parseInt(o.getProperty("id").toString()));
				joke.setLocation(Consts.MEDIA_CENTER_BASE_URL + o.getProperty("location"));
				joke.setTitle(o.getProperty("title").toString());
				joke.setUploadTime(o.getProperty("uploadTime").toString());
				list.add(joke);
			}
			Log.i(TAG, "get data from webservice with method: "
					+ this.methodName + "\nget result: " + list.toString());
		}
		// 生成动态数组，加入数据
		// list = new ArrayList<Joke>();
		// Calendar calendar = Calendar.getInstance();
		// String now = calendar.get(Calendar.YEAR) + "-"
		// + calendar.get(Calendar.MONTH) + "-"
		// + calendar.get(Calendar.DATE);
		// Joke joke1 = new Joke();
		// joke1.setAuthor("邱鸿斌");
		// joke1.setId(1);
		// joke1.setLocation("http://59.77.5.42:80/jokes/real.mp3");
		// joke1.setTitle("joke1");
		// joke1.setUploadTime(now);
		// list.add(joke1);
		// Joke joke2 = new Joke();
		// joke2.setAuthor("白志斌");
		// joke2.setId(2);
		// joke2.setLocation("http://59.77.5.42:80/jokes/real.mp3");
		// joke2.setTitle("joke2");
		// joke2.setUploadTime(now);
		// list.add(joke2);
		// Joke joke3 = new Joke();
		// joke3.setAuthor("翁晓奇");
		// joke3.setId(3);
		// joke3.setLocation("http://59.77.5.42:80/jokes/real.mp3");
		// joke3.setTitle("joke3");
		// joke3.setUploadTime(now);
		// list.add(joke3);
		// Log.i(TAG, "create jokeList: " + list.toString() + "; size: "
		// + list.size());
		// construct the message;
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		Message message = handler.obtainMessage(Consts.MSG_JOKELIST_READY);
		Bundle b = new Bundle();
		b.putParcelableArrayList("data", list);
		message.setData(b);
		handler.sendMessage(message);

	}
}
