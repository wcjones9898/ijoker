package cn.edu.xmu.software.ijoke.serviceimpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import cn.edu.xmu.software.ijoke.service.UploadReceiveService;

public class UploadReceiveServiceImpl implements UploadReceiveService, Runnable {

	private final int SERVERPORT = 1001;
	private final int BUFFERSIZE = 100;
	private static int count = 0;
	public void uploadService() {
		// TODO Auto-generated method stub
		Thread serverMain = new Thread(new UploadReceiveServiceImpl());
		serverMain.start();
	}

	public void run() {
		// TODO Auto-generated method stub
		System.out.println("S:Connecting....");
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(SERVERPORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			if (serverSocket != null) {
				try {
					Socket client = serverSocket.accept();
					System.out.println("S:Receiving");
					File myFile = new File("recorder" + count++ + ".amr");
					FileOutputStream fos = new FileOutputStream(myFile);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					InputStream is = client.getInputStream();
					int bytesRead = 1;
					int current = 0;
					byte[] mybyteArray = new byte[BUFFERSIZE];
					while ((bytesRead = is.read(mybyteArray)) > 0) {
						bos.write(mybyteArray, 0, bytesRead);
					}
					System.out.print(current);
					bos.flush();
					bos.close();
					client.close();
					System.out.print("S:Received:'" + myFile.getAbsolutePath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

}
