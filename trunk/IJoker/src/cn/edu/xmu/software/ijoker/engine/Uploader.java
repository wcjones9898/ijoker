package cn.edu.xmu.software.ijoker.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.exception.UploadException;
import cn.edu.xmu.software.ijoker.util.Consts;

public class Uploader extends Thread {
	private Handler handler;
	private File currentRecord;
	private static final String TAG = Uploader.class.getName();

	public Uploader(Handler handler) {
		this.handler = handler;
	}

	public void doStart(File currentRecord) {
		this.currentRecord = currentRecord;
		this.start();
	}

	@Override
	public void run() {
		super.run();
		try {
			upLoad();
		} catch (UploadException e) {
			Log.e(TAG, e.getMessage(), e);
			sendMessage(e.getMessage());
		}
	}

	private void sendMessage(String detailMessage) {
		Message message = handler.obtainMessage(Consts.ERROR_UPLOAD);
		Bundle b = new Bundle();
		b.putString("errorMessage", detailMessage);
		message.setData(b);
		handler.sendMessage(message);
	}

	private void upLoad() throws UploadException {
		PostMethod filePost = new PostMethod(Consts.SERVER_UPLOAD_URL);
		try {

			Part[] parts = { new FilePart(currentRecord.getName(),
					currentRecord) };
			filePost.setRequestEntity(new MultipartRequestEntity(parts,
					filePost.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(
					5000);
			int status = client.executeMethod(filePost);
			if (status == HttpStatus.SC_OK) {
				System.out.println("上传成功");
				// 上传成功
			} else {
				System.out.println("上传失败");
				// 上传失败
			}

		} catch (FileNotFoundException e) {
			throw new UploadException(e.getMessage(), e);
		} catch (HttpException e) {
			throw new UploadException(e.getMessage(), e);
		} catch (IOException e) {
			throw new UploadException(e.getMessage(), e);
		} catch (Exception e) {
			throw new UploadException(e.getMessage(), e);
		} finally {
			filePost.releaseConnection();
		}
	}
}
