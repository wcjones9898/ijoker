package cn.edu.xmu.software.ijoker.service;

import java.util.HashMap;

import android.os.Handler;
import android.util.Log;
import cn.edu.xmu.software.ijoker.engine.WSEngine;

public class LoginService extends Thread {
	private String username;
	private String password;
	private static final String TAG = LoginService.class.getName();
	private Handler handler;

	public LoginService(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void run() {
		super.run();
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("username", username);
		parms.put("password", password);
		wsEngine.doStart("authorization", parms);
		Log.i(TAG, "call webservice to authenticate username: " + username
				+ " password: " + password);
	}

	public void authenticate(String username, String password) {
		this.username = username;
		this.password = password;
		this.start();
	}
}
