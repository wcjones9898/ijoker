package cn.edu.xmu.software.ijoker.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import cn.edu.xmu.software.ijoker.UI.JokeList;
import cn.edu.xmu.software.ijoker.engine.CartoonDownloader;
import cn.edu.xmu.software.ijoker.util.Consts;

public class CartoonDownloadThread extends Thread{
	private Handler handler ;
	private String path;
	private byte[] picData;
	private Integer port ;
	private byte[] buffer;
	private int bufferType;
	public CartoonDownloadThread(Handler handler,Integer port,int bufferType) {
		this.handler = handler;
		this.port = port;
		this.bufferType = bufferType;
	}

	// 启动线程;
	public void doStart(String path) {
		this.path = path;
		this.start();
	}
	private byte[] createImageView()
	{
		return CartoonDownloader.downLoad(path,port);

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
		b.putInt("bufferType", bufferType);
		b.putByteArray("picData", picData);
		
		
		message.setData(b);
		handler.sendMessage(message);
	}

}
