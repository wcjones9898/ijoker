package cn.edu.xmu.software.ijoker.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
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
	private String jokeTitle;
	private String keyword;
	private String userId;
	private static final String TAG = Uploader.class.getName();

	public Uploader(Handler handler) {
		this.handler = handler;
	}

	public void doStart(File currentRecord, String jokeTitle, String keyword,
			String userId) {
		this.jokeTitle = jokeTitle;
		this.keyword = keyword;
		this.userId = userId;
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
		message.setData(b);
		handler.sendMessage(message);
	}

	private void upLoad() throws UploadException {
		PostMethod filePost = new PostMethod(Consts.SERVER_UPLOAD_URL);
		PostMethod fileGet = new PostMethod(Consts.SERVER_UPLOAD_URL);
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
				Log.i(TAG, "upload file: " + currentRecord.getAbsolutePath()
						+ " successful!");
				// 上传成功
				// filePost.releaseConnection();
				String a = filePost.getResponseBody().toString();
				Log.i(TAG, "get synchronizationTicket: " + a);
				NameValuePair[] data = { new NameValuePair("title", jokeTitle),

				new NameValuePair("userId", userId),
						new NameValuePair("keyWord", keyword),
						new NameValuePair("synchronizationTicket", a) };
				fileGet.getParams().setContentCharset("gbk");

				// 填入各个表单域的值

				// 将表单的值放入postMethod中
				fileGet.setRequestBody(data);
				status = client.executeMethod(fileGet);
				if (status == HttpStatus.SC_MOVED_PERMANENTLY
						|| status == HttpStatus.SC_MOVED_TEMPORARILY) {
					// 从头中取出转向的地址
					Header locationHeader = filePost
							.getResponseHeader("location");
					String location = null;
					if (locationHeader != null) {
						location = locationHeader.getValue();
						Log.i(TAG, "The page was redirected to:" + location);
					} else {
						Log.i(TAG, "Location field value is null.");
					}
				}

			} else {
				Log.i(TAG, "upload file: " + currentRecord.getAbsolutePath()
						+ " failed!");
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
			fileGet.releaseConnection();
		}
		if (currentRecord != null)
			currentRecord.delete();
		Message message = handler.obtainMessage(Consts.UPLOAD_SUCCESS);
		Bundle b = new Bundle();
		message.setData(b);
		handler.sendMessage(message);
	}
}
