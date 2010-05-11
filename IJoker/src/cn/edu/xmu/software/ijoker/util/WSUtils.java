package cn.edu.xmu.software.ijoker.util;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Iterator;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import cn.edu.xmu.software.ijoker.exception.CallWebServiceException;

import android.util.Log;

public class WSUtils {
	private static final String TAG = WSUtils.class.getName();

	public WSUtils() {
	}

	/**
	 * @param endPoint
	 * @param methodName
	 * @param params
	 * @param wsdl
	 * @return SoapObject
	 * @throws XmlPullParserException
	 * @throws IOException
	 * @throws CallWebServiceException
	 */
	public static SoapObject callWebService(String methodName,
			Map<String, Object> params) throws IOException,
			XmlPullParserException, CallWebServiceException, ConnectException {
		String wsdl = Consts.SERVICE_BASE_URL + methodName + "?wsdl";
		String nameSpace = Consts.SERVICE_BASE_URL + methodName;
		Log.i(TAG, "wsdl: " + wsdl + "\n namespace: " + nameSpace
				+ " \n methodName: " + methodName);
		// String SOAP_ACTION = nameSpace + methodName;
		SoapObject request = new SoapObject(nameSpace, methodName);
		SoapObject result = null;

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
		androidHT.call(null, envelope);
		Object o = envelope.bodyIn;
		if (o instanceof SoapFault) {
			SoapFault soapFault = (SoapFault) o;
			throw new CallWebServiceException(soapFault.getMessage(), soapFault);
		}
		result = (SoapObject) o;
		Log.i(TAG, result.toString());

		return result;
	}
}
