package cn.edu.xmu.software.ijoker.engine;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.util.Consts;

public class Uploader extends Thread {
	private Handler handler;
	private String serverUrl;
	private String filePath;
	private final int BUFFERSIZE = 100;
	private Socket socket = null;
	private static final String TAG = Uploader.class.getName();

	public Uploader(Handler handler) {
		this.handler = handler;
	}

	public void doStart(String serverUrl, String filePath) {
		this.serverUrl = serverUrl;
		this.filePath = filePath;
		this.start();
	}

	@Override
	public void run() {
		super.run();
		try {
			connectToServer(serverUrl);
			fileTransfer(filePath);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			sendMessage(e.getMessage());
		}
	}

	private void connectToServer(String serverUrl) throws UnknownHostException,
			IOException {
		// long start = System.currentTimeMillis();
		//
		// int current;
		socket = new Socket(serverUrl, 1001);
	}

	private void fileTransfer(String filePath) throws IOException {
		int bytesRead = 0;
		if (socket != null) {
			byte[] bytesArray = new byte[BUFFERSIZE];
			File myFile = new File(filePath);
			FileInputStream fis = new FileInputStream(myFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			OutputStream os = socket.getOutputStream();
			while ((bytesRead = bis.read(bytesArray)) > 0) {
				os.write(bytesArray, 0, bytesRead);
			}
			os.flush();
			socket.close();
			// PrintWriter out = new PrintWriter(
			// new BufferedWriter(
			// new OutputStreamWriter(socket.getOutputStream())),true);
			// out.println("Bill White");
		}
	}

	private void sendMessage(String detailMessage) {
		Message message = handler.obtainMessage(Consts.ERROR_UPLOAD);
		Bundle b = new Bundle();
		b.putString("errorMessage", detailMessage);
		message.setData(b);
		handler.sendMessage(message);
	}
}
