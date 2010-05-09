package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
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

	}

	@Override
	protected void onStart() {
		super.onStart();
		int errorCode = getIntent().getIntExtra("errorCode", -1);
		if (errorCode != -1) {
			switch (errorCode) {
			case Consts.ERROR_USERNAME_NOEXIST:
				Log.i(TAG, "have error message: " + Consts.USER_NOTEXIST);
				Toast.makeText(Login.this, Consts.USER_NOTEXIST,
						Toast.LENGTH_SHORT).show();
				break;
			default:
			}
		}
	}

	private void find() {
		login_btn = (Button) findViewById(R.id.login_btn);
		login_btn.setOnClickListener(login);
		tv_username = (TextView) findViewById(R.id.username);
		tv_password = (TextView) findViewById(R.id.password);
		rempw_cbx = (CheckBox) findViewById(R.id.rempw_cbx);
		autoLogin_cbx = (CheckBox) findViewById(R.id.autologin_cbx);
		register_btn = (Button) findViewById(R.id.register_btn);
		register_btn.setOnClickListener(register);
	}

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