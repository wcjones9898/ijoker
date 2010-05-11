package cn.edu.xmu.software.ijoker.ws;

import java.util.ArrayList;
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
				joke.setAuthor(o.getProperty("author").toString());
				joke
						.setLike(Integer.parseInt(o.getProperty("like")
								.toString()));
				joke.setId(Integer.parseInt(o.getProperty("id").toString()));
				joke.setLocation(Consts.MEDIA_CENTER_BASE_URL
						+ o.getProperty("location"));
				joke.setTitle(o.getProperty("title").toString());
				joke.setUploadTime(o.getProperty("uploadTime").toString());
				list.add(joke);
			}
			Log.i(TAG, "get data from webservice with method: "
					+ this.methodName + "\nget result: " + list.toString());
		}
		// 生成动态数组，加入数据
		Message message = handler.obtainMessage(Consts.MSG_JOKELIST_READY);
		Bundle b = new Bundle();
		b.putParcelableArrayList("data", list);
		message.setData(b);
		handler.sendMessage(message);

	}
}
