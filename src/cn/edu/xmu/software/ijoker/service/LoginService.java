package cn.edu.xmu.software.ijoker.service;

import java.util.HashMap;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import cn.edu.xmu.software.ijoker.engine.WSEngine;
import cn.edu.xmu.software.ijoker.util.Consts;

public class LoginService extends AbstractService {
	private static final String TAG = LoginService.class.getName();
	private Handler handler;

	public LoginService(Handler handler, Context context) {
		super(handler, context);
		// TODO Auto-generated constructor stub
	}

	public void authenticate(String username, String password) {
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("username", username);
		parms.put("password", password);
		parms.put(Consts.SERVER_URL, this.getServiceBaseURL());
		wsEngine.doStart(Consts.METHODNAME_AUTHORIZATION, parms);
		Log.i(TAG, "call webservice to authenticate username: " + username
				+ " password: " + password);
	}
}
