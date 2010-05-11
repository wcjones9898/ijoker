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
import cn.edu.xmu.software.ijoker.entity.ClassItem;
import cn.edu.xmu.software.ijoker.exception.CallWebServiceException;
import cn.edu.xmu.software.ijoker.util.Consts;
import cn.edu.xmu.software.ijoker.util.WSUtils;

public class GetDivisionListWSMethod extends AbstractWSMethod {

	public GetDivisionListWSMethod(String methodName, Handler handler,
			HashMap<String, Object> parms) {
		super(methodName, handler, parms);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void invokeWSMethod() throws CallWebServiceException {
		ArrayList<ClassItem> list = null;
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
					.getProperty(Consts.DIVISIONLISTRETURN);
			list = new ArrayList<ClassItem>();
			Iterator<SoapObject> iterator = vector.iterator();
			while (iterator.hasNext()) {
				SoapObject o = iterator.next();
				ClassItem classItem = new ClassItem();
				classItem.setClassId(o.getProperty("classId").toString());
				classItem.setClassLevel(Integer.parseInt(o.getProperty(
						"classLevel").toString()));
				classItem.setClassName(o.getProperty("className").toString());
				classItem.setId(Integer
						.parseInt(o.getProperty("id").toString()));
				classItem.setJokeNum(Integer.parseInt(o.getProperty("jokeNum")
						.toString()));
				list.add(classItem);
			}
			Log.i(TAG, "get data from webservice with method: "
					+ this.methodName + "\nget result: " + list.toString());
		}
		Message message = handler.obtainMessage(Consts.MSG_DIVISIONLIST_READY);
		Bundle b = new Bundle();
		b.putParcelableArrayList("data", list);
		message.setData(b);
		handler.sendMessage(message);

	}

}
