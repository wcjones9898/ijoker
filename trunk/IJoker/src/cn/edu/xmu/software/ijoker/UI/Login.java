package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import cn.edu.xmu.software.ijoker.R;

public class Login extends Activity {
	private Button login_btn, register_btn;
	private TextView username, password;
	private CheckBox rempw_cbx, autoLogin_cbx;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		find();

	}

	private void find() {
		login_btn = (Button) findViewById(R.id.login_btn);
		login_btn.setOnClickListener(login);
		username = (TextView) findViewById(R.id.username);
		password = (TextView) findViewById(R.id.password);
		rempw_cbx = (CheckBox) findViewById(R.id.rempw_cbx);
		autoLogin_cbx = (CheckBox) findViewById(R.id.autologin_cbx);
		register_btn = (Button) findViewById(R.id.register_btn);
		register_btn.setOnClickListener(register);
	}

	private Button.OnClickListener login = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (validate()) {
				check();

			}
		}
	};

	private boolean validate() {
		if (username.getText().equals("") || password.getText().equals("")) {
			// TODO check the text passby.
		}
		return true;
	}

	private void check() {
		Intent intent = new Intent();
		Bundle userInfo = new Bundle();
		userInfo.putString("username", username.getText().toString());
		userInfo.putString("password", password.getText().toString());
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
}