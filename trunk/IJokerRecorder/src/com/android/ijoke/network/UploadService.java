package com.android.ijoke.network;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UploadService extends Service{

	private final int BUFFERSIZE = 100;
	private Socket socket = null;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public void onReceive(String filepath)
	{
		connectToServer("192.168.2.1");
		fileTransfer(filepath);
	}
	private void connectToServer(String serverUrl)
	{
		long start = System.currentTimeMillis();

		int current;
		try {
			socket = new Socket(serverUrl,1001);
			Log.i("text", "socket create");
		} catch (UnknownHostException e) {
			Log.i("Error", "Unkown Host");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void fileTransfer(String filePath)
	{
		int bytesRead = 0;
		try {
			if(socket != null)
			{
				byte[] bytesArray = new byte[BUFFERSIZE];
				File  myFile = new File(filePath);
				FileInputStream fis = new FileInputStream(myFile);
				BufferedInputStream bis = new BufferedInputStream(fis);
				OutputStream os = socket.getOutputStream();
				while((bytesRead = bis.read(bytesArray))>0)
				{
				   os.write(bytesArray, 0, bytesRead);
				}
				os.flush();
				socket.close();
//			PrintWriter out = new PrintWriter( 
//					new BufferedWriter( 
//							new OutputStreamWriter(socket.getOutputStream())),true);
//			out.println("Bill White");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
