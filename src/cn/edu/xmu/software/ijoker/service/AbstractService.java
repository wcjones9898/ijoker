package cn.edu.xmu.software.ijoker.service;

import cn.edu.xmu.software.ijoker.util.Consts;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

public class AbstractService {
	protected Handler handler;
	protected Context context;
	protected final static String TAG = AbstractService.class.getName();

	public AbstractService(Handler handler, Context context) {
		this.handler = handler;
		this.context = context;
	}

	protected String getMediaCenterURL() {
		SharedPreferences settings = context.getSharedPreferences(
				Consts.preferencesSetting, 0);
		String media_ip = settings.getString(Consts.MEDIA_IP, "59.77.5.42:80");
		return Consts.HTTP + media_ip;
	}

	protected String getServiceBaseURL() {
		SharedPreferences settings = context.getSharedPreferences(
				Consts.preferencesSetting, 0);
		String server_ip = settings.getString(Consts.SERVER_IP,
				"59.77.5.42:8080");
		return Consts.HTTP + server_ip + Consts.SERVICE_BASE_URL;
	}

	protected String getServerUploadURL() {
		SharedPreferences settings = context.getSharedPreferences(
				Consts.preferencesSetting, 0);
		String server_ip = settings.getString(Consts.SERVER_IP,
				"59.77.5.42:8080");
		return Consts.HTTP + server_ip + Consts.SERVER_UPLOAD_URL;
	}
}
