package cn.edu.xmu.software.ijoker.engine;

import java.io.IOException;
import android.util.Log;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;




public class CartoonDownloader extends Thread{

    
	public static  byte[] downLoad(String url, Integer Port)
	{

		GetMethod fileGet = new GetMethod(url);
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				Port);
		try {

			client.executeMethod(fileGet);
			Log.i("downloader", "download Succeed");
			return fileGet.getResponseBody();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		return null;
	}
}
