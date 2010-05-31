package cn.edu.xmu.software.ijoker.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import cn.edu.xmu.software.ijoker.engine.CartoonDownloader;
import cn.edu.xmu.software.ijoker.util.Consts;

public class CartoonDownloadThread extends Thread{
	private Handler handler ;
	private String path;
	private byte[] picData;
	private Integer port ;
	public CartoonDownloadThread(Handler handler,Integer port) {
		this.handler = handler;
		this.port = port;
	}

	// 启动线程;
	public void doStart(String path) {
		this.path = path;
		this.start();
	}
	private byte[] createImageView()
	{
		byte[] imageData = CartoonDownloader.downLoad(path,port);
  
		return imageData;

	}
	@Override
	public void run() {
		
		super.run();
		picData = createImageView();
		this.sendMessage();
	}

	private void sendMessage() {
		Message message = handler.obtainMessage(Consts.MSG_CARTOONPIC_DATA);
		Bundle b = new Bundle();
		b.putByteArray("picData", picData);
		message.setData(b);
		handler.sendMessage(message);
	}

}
