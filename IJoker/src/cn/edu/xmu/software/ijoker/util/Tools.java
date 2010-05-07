package cn.edu.xmu.software.ijoker.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class Tools {

	// parse the RequestToken from returned XML to get the value of Token.
	public static String paxToken(String xml) {
		String regEx = "<token>(.+?)</token>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(xml);
		boolean rs = m.find();
		if (rs) {
			System.out.println("start with: " + m.start() + "end with: "
					+ m.end() + "value: " + m.group());
		}
		return m.group(1);
	}

	/**
	 * Sends an HTTP GET request to a url
	 * 
	 * @param endpoint
	 *            - The URL of the server. (Example:
	 *            " http://www.yahoo.com/search")
	 * @param requestParameters
	 *            - all the request parameters (Example:
	 *            "param1=val1&param2=val2"). Note: This method will add the
	 *            question mark (?) to the request - DO NOT add it yourself
	 * @return - The response from the end point
	 */
	public static String sendGetRequest(String endpoint,
			String requestParameters) {
		Log.i("sendGetRequest", endpoint);
		String result = null;
		if (endpoint.startsWith("http://")) {
			// Send a GET request to the servlet
			try {
				// Send data
				String urlStr = endpoint;
				if (requestParameters != null && requestParameters.length() > 0) {
					urlStr += "?" + requestParameters;
				}
				Log.i("urlStr", urlStr);
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();

				// Get the response
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				result = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Log.i("sendGetRequest", result);
		return result;
	}

	public static InputStream sendGetRequest2(String endpoint,
			String requestParameters) {
		Log.i("sendGetRequest", endpoint);
		InputStream is = null;
		if (endpoint.startsWith("http://")) {
			// Send a GET request to the servlet
			try {
				// Send data
				String urlStr = endpoint;
				if (requestParameters != null && requestParameters.length() > 0) {
					urlStr += "?" + requestParameters;
				}
				Log.i("urlStr", urlStr);
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();
				is = conn.getInputStream();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return is;
	}

	public static String paramsToString(Hashtable<String, ?> params) {
		Vector<String> v = new Vector<String>(params.keySet());
		Collections.sort(v);
		// Display (sorted) hashtable.
		String result = "";
		for (Enumeration<String> e = v.elements(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			String val = (String) params.get(key);
			result += key;
			result += "=";
			result += val;
			result += "&";
			Log.i("params", "Key: " + key + ",Val: " + val);
		}
		Log.i("params", "result:" + result);
		return result;
	}
}
