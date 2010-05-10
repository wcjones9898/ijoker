package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.util.Consts;

public class Login extends Activity {
	private Button login_btn, register_btn;
	private TextView tv_username, tv_password;
	private CheckBox rempw_cbx, autoLogin_cbx;
	private String username;
	private String password;
	private static final String TAG = Login.class.getName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		find();
		restorePrefs();
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (getIntent().hasExtra("errorCode")) {
			int errorCode = getIntent().getIntExtra("errorCode", -1);
			switch (errorCode) {
			case Consts.ERROR_USERNAME_NOEXIST:
				Log.i(TAG, "have error message: " + Consts.USER_NOTEXIST);
				Toast.makeText(Login.this, Consts.USER_NOTEXIST,
						Toast.LENGTH_SHORT).show();
				break;
			case Consts.ERROR_CALLWEBSERVICE:
				Log.i(TAG, "have error message: " + Consts.NETWORK_FAILED);
				Toast.makeText(Login.this, Consts.NETWORK_FAILED,
						Toast.LENGTH_SHORT).show();
				break;
			default:
			}
			this.getIntent().putExtra("errorCode", -1);
		}
		// restorePrefs();
	}

	private void find() {
		login_btn = (Button) findViewById(R.id.login_btn);
		login_btn.setOnClickListener(login);
		tv_username = (TextView) findViewById(R.id.username);
		tv_password = (TextView) findViewById(R.id.password);
		rempw_cbx = (CheckBox) findViewById(R.id.rempw_cbx);
		rempw_cbx.setOnCheckedChangeListener(remPwListener);
		autoLogin_cbx = (CheckBox) findViewById(R.id.autologin_cbx);
		autoLogin_cbx.setOnCheckedChangeListener(autoLoginListener);
		register_btn = (Button) findViewById(R.id.register_btn);
		register_btn.setOnClickListener(register);
	}

	private void restorePrefs() {
		if (getSessionInPreference()) {
			SharedPreferences settings = getSharedPreferences(
					Consts.preferencesSetting, 0);
			boolean rememberPassword = settings.getBoolean(
					Consts.rememberPassword, false);
			Log.i(TAG, "rememberPassword from sharedPreferences: "
					+ rememberPassword);
			if (rememberPassword) {
				username = settings.getString(Consts.username, "");
				Log.i(TAG, "username from sharedPreferences: " + username);
				tv_username.setText(username);
				password = settings.getString(Consts.password, "");
				Log.i(TAG, "password from sharedPreferences: " + password);
				tv_password.setText(password);
				rempw_cbx.setChecked(rememberPassword);
			}
			boolean autoLogin = settings.getBoolean(Consts.autoLogin,
					false);
			Log.i(TAG, "autoLogin from sharedPreferences: " + autoLogin);
			if (autoLogin) {
				autoLogin_cbx.setChecked(autoLogin);
				if (validate())
					check();
			}
		}
	}

	private boolean getSessionInPreference() {
		SharedPreferences settings = getSharedPreferences(
				Consts.preferencesSetting, 0);
		String session = settings.getString(Consts.session, "");
		if (session.equals(""))
			return false;
		return true;
	}

	private CheckBox.OnCheckedChangeListener autoLoginListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (!getSessionInPreference()) {
				SharedPreferences settings = getSharedPreferences(
						Consts.preferencesSetting, 0);
				Editor editer = settings.edit();
				editer.putBoolean(Consts.autoLogin, isChecked);
				editer.commit();
				Log.i(TAG, "save data to preferences with autoLogin: "
						+ isChecked);
			}
		}
	};
	private CheckBox.OnCheckedChangeListener remPwListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (!getSessionInPreference()) {
				SharedPreferences settings = getSharedPreferences(
						Consts.preferencesSetting, 0);
				Editor editer = settings.edit();
				editer.putBoolean(Consts.rememberPassword, isChecked);
				username = tv_username.getText().toString();
				password = tv_password.getText().toString();
				editer.putString(Consts.username, username);
				editer.putString(Consts.password, password);
				Log.i(TAG, "save data to preferences with rempw: " + isChecked
						+ " username: " + username + " password: " + password);
				editer.commit();
			}
		}
	};

	private Button.OnClickListener login = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			username = tv_username.getText().toString();
			password = tv_password.getText().toString();
			if (validate()) {
				check();

			}
		}
	};

	private boolean validate() {
		boolean flag = true;
		if (username.equals("")) {
			Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
			tv_username.startAnimation(shake);
			flag = flag && false;
			Log.i(TAG, "username validate: " + flag);
		}
		if (password.equals("")) {
			Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
			tv_password.startAnimation(shake);
			flag = flag && false;
			Log.i(TAG, "password validate: " + flag);
		}
		return flag;
	}

	private void check() {
		Intent intent = new Intent();
		Bundle userInfo = new Bundle();
		userInfo.putString("username", username.toString());
		userInfo.putString("password", password.toString());
		intent.putExtra("android.intent.extra.userInfo", userInfo);
		intent.setClass(Login.this, Loading.class);
		startActivity(intent);
		finish();
	}

	private Button.OnClickListener register = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Login.this, Register.class);
			startActivity(intent);
		}
	};

	@Override
	protected void onResume() {
		super.onResume();

	}

}