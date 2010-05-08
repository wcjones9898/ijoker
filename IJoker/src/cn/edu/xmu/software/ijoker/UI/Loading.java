package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.service.LoginService;
import cn.edu.xmu.software.ijoker.util.Consts;

public class Loading extends Activity {
	private static final String TAG = Loading.class.getName();
	private String username;
	private String password;
	private LoginService loginService;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Consts.MSG_LOGIN_READY:
				if (msg.arg1 == 0) {
					Log.i(TAG, "login success! now step into functions UI");
					callFunctionsUI();
				} else {
					Log
							.i(TAG,
									"can not login,check the imformation and login again!");
					callLoginUI();
				}
				break;
			default:
			}
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("android.intent.extra.userInfo");
		username = bundle.getString("username");
		password = bundle.getString("password");
		loginService = new LoginService(handler);
		loginService.authenticate(username, password);
		Log.i(TAG, "call authenticate webservice with username: " + username
				+ " password: " + password);

	}

	private void callFunctionsUI() {
		Intent intent = new Intent();
		intent.setClass(Loading.this, Functions.class);
		startActivity(intent);
	}

	private void callLoginUI() {
		Intent intent = new Intent();
		intent.setClass(Loading.this, Login.class);
		startActivity(intent);
	}
}
// class Loader extends Thread {
//
// @Override
// public void run() {
// // TODO Auto-generated method stub
//		
// try {
// wait(3000);
// } catch (InterruptedException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
//	
// }
