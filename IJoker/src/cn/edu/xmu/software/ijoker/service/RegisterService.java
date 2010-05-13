package cn.edu.xmu.software.ijoker.service;

import java.util.HashMap;

import cn.edu.xmu.software.ijoker.engine.WSEngine;
import cn.edu.xmu.software.ijoker.util.Consts;
import android.os.Handler;
import android.util.Log;

public class RegisterService extends Thread {
	private String username;
	private String nickname;
	private String password;
	private static final String TAG = RegisterService.class.getName();
	private Handler handler;

	public RegisterService(Handler handler) {
		super();
		this.handler = handler;
	}

	@Override
	public void run() {
		super.run();
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("userName", username);
		parms.put("nickName", nickname);
		parms.put("passWord", password);
		wsEngine.doStart(Consts.METHODNAME_REGISTER, parms);
		Log.i(TAG, "call webservice to register username: " + username
				+ " password: " + password);
	}

	public void register(String username, String password, String nickname) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.start();
	}
}
