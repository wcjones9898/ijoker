package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.service.LoginService;
import cn.edu.xmu.software.ijoker.util.Consts;

public class Loading extends BaseActivity {
	private static final String TAG = Loading.class.getName();
	private String username;
	private String password;
	private LoginService loginService;
	private ProgressBar progressBar;
	private int intCounter = 0;
	private boolean isLoading = true;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isLoading = false;
			switch (msg.what) {
			case Consts.MSG_LOGIN_READY:
				if (msg.arg1 == Consts.FLAG_LOGIN_SUCCESS) {
					Log.i(TAG, "login success! now step into functions UI");
					callFunctionsUI();
				} else {
					Log
							.i(TAG,
									"can not login,check the imformation and login again!");
					callLoginUI(msg.arg2);
				}
				break;
			case Consts.ERROR_CALLWEBSERVICE:
				callLoginUI(Consts.ERROR_CALLWEBSERVICE);
			default:
			}
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		progressBar = (ProgressBar) findViewById(R.id.loadingprogress);
		progressBar.setIndeterminate(false);
		progressBar.setMax(100);
		progressBar.setProgress(0);
		// progressBar.setAnimation()
		updateProgressBar();
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
		finish();
	}

	private void callLoginUI(int errorCode) {
		SharedPreferences settings = getSharedPreferences(
				Consts.preferencesSetting, 0);
		Editor editer = settings.edit();
		editer.putString(Consts.session, "");
		Log.i(TAG, "save data to preferences with session: " + "");
		editer.commit();
		Intent intent = new Intent();
		intent.setClass(Loading.this, Login.class);
		intent.putExtra("errorCode", errorCode);
		startActivity(intent);
		finish();
	}

	private void updateProgressBar() {
		intCounter = (5 + intCounter) % 100;
		progressBar.setProgress(intCounter);
		Log.i(TAG, "set progress: " + intCounter);
		if (isLoading) {
			Runnable notification = new Runnable() {
				public void run() {
					updateProgressBar();
				}
			};
			handler.postDelayed(notification, 200);
		}
	}
}
