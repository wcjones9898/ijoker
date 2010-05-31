package cn.edu.xmu.software.ijoker.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.exception.CallWebServiceException;
import cn.edu.xmu.software.ijoker.util.Consts;
import cn.edu.xmu.software.ijoker.util.WSUtils;

public class GetCartoonListWSMethod extends AbstractWSMethod{

	public GetCartoonListWSMethod(String methodName, Handler handler,
			HashMap<String, Object> parms) {
		super(methodName, handler, parms);
	}
	@Override
	public void invokeWSMethod() throws CallWebServiceException {
		// TODO Auto-generated method stub
		ArrayList<String> list = null;
		SoapObject result = null;
		try {
			result = (SoapObject) WSUtils.callWebService(this.methodName, parms);
			Log.i(TAG, "get data from webservice with method: "
					+ this.methodName + "\nget result: " + result.toString());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			throw new CallWebServiceException(e.getMessage(), e);
		}
		list = new ArrayList<String>();
		Vector<SoapPrimitive> vector = (Vector<SoapPrimitive>) result
		.getProperty(Consts.CARTOONLISTRETURN);
		Iterator<SoapPrimitive> iterator = vector.iterator();
		if (result != null && result.getPropertyCount() > 0) {
			{
				while (iterator.hasNext()) {
					SoapPrimitive o = iterator.next();
//					SoapObject o = iterator.next();
					String path = o.toString();
					list.add(path);

				}
				
			}
		
			Log.i(TAG, "get data from webservice with method: "
					+ this.methodName + "\nget result: " + list.toString());
		}
		// 生成动态数组，加入数据
		Log.i("test","GetCartoonListWSMethod");
		Message message = handler.obtainMessage(Consts.MSG_CARTOONLIST_READY);
		Bundle b = new Bundle();
		b.putStringArrayList("data", list);
		message.setData(b);
		handler.sendMessage(message);
		
	
	}

}
