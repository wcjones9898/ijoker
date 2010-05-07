package cn.edu.xmu.software.ijoker.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

public class WSUtils {
	private static final String wsdl = "http://59.77.5.181:8080/IJokerDataService/services/jokeListService?wsdl";
	private static final String nameSpace = "http://59.77.5.181:8080/IJokerDataService/services/jokeListService";

	public WSUtils() {
	}

	/**
	 * @param endPoint
	 * @param methodName
	 * @param params
	 * @param wsdl
	 * @return SoapObject
	 */
	public static SoapObject callWebService(String methodName,
			Map<String, Object> params) {

		final String SOAP_ACTION = nameSpace + methodName;
		SoapObject request = new SoapObject(nameSpace, methodName);
		SoapObject soapResult = null;

		if (params != null && !params.isEmpty()) {
			for (Iterator it = params.entrySet().iterator(); it.hasNext();) {// 遍历MAP
				Map.Entry<String, Object> e = (Map.Entry<String, Object>) it
						.next();
				request.addProperty(e.getKey().toString(), e.getValue());
			}
		}

		/**
		 * 设置Soap版本 类型：VER11.
		 */
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		// envelope.dotNet = false;// 是否是dotNet WebService
		envelope.setOutputSoapObject(request);

		AndroidHttpTransport androidHT = new AndroidHttpTransport(wsdl);
		try {
			androidHT.call(null, envelope);
		} catch (IOException e) {
			Log.e("IOException:", e.getMessage());
		} catch (XmlPullParserException e) {
			Log.e("XmlPullParserException", e.getMessage());
		}
		soapResult = (SoapObject) envelope.bodyIn;
		return soapResult;
	}
}
