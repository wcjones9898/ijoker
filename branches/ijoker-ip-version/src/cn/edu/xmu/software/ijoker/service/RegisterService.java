package cn.edu.xmu.software.ijoker.service;

import java.util.HashMap;

import android.os.Handler;
import android.util.Log;
import cn.edu.xmu.software.ijoker.engine.WSEngine;
import cn.edu.xmu.software.ijoker.util.Consts;

public class RegisterService {
	private static final String TAG = RegisterService.class.getName();
	private Handler handler;

	public RegisterService(Handler handler) {
		super();
		this.handler = handler;
	}

	public void register(String username, String password, String nickname) {
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("userName", username);
		parms.put("nickName", nickname);
		parms.put("passWord", password);
		wsEngine.doStart(Consts.METHODNAME_REGISTER, parms);
		Log.i(TAG, "call webservice to register username: " + username
				+ " password: " + password);
	}
}
