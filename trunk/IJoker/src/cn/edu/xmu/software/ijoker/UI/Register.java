package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.service.RegisterService;
import cn.edu.xmu.software.ijoker.util.Consts;

public class Register extends Activity {
	private String username;
	private String password;
	private String confirm_password;
	private Button register_btn;
	private EditText username_txt, password_txt, repeatPassword_txt;
	private static final String TAG = Register.class.getName();
	private ProgressDialog progressDialog;
	private RegisterService registerService;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Consts.MSG_REGISTER_READY:
				if (msg.arg1 == Consts.FLAG_REGISTER_SUCCESS) {
					Log
							.i(TAG,
									"register successful! now step into loadding UI");
					SharedPreferences settings = getSharedPreferences(
							Consts.preferencesSetting, 0);
					Editor editer = settings.edit();
					editer.putString(Consts.username, username);
					editer.commit();
					callLoadingUI();
				} else {
					switch (msg.arg2) {
					case Consts.ERROR_USERNAME_NOEXIST:
						Log.i(TAG, "have error message: " + Consts.USER_EXIST);
						Toast.makeText(Register.this, Consts.USER_EXIST,
								Toast.LENGTH_SHORT).show();
						break;
					case Consts.ERROR_CALLWEBSERVICE:
						Log.i(TAG, "have error message: "
								+ Consts.NETWORK_FAILED);
						Toast.makeText(Register.this, Consts.NETWORK_FAILED,
								Toast.LENGTH_SHORT).show();
						break;
					default:
					}
				}
				break;
			case Consts.ERROR_CALLWEBSERVICE:
				Toast.makeText(Register.this, Consts.NETWORK_FAILED,
						Toast.LENGTH_SHORT).show();
			default:
			}
			progressDialog.dismiss();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		find();
	}

	private void find() {

		register_btn = (Button) findViewById(R.id.register_btn);
		register_btn.setOnClickListener(register);

		username_txt = (EditText) findViewById(R.id.username);
		password_txt = (EditText) findViewById(R.id.password);
		repeatPassword_txt = (EditText) findViewById(R.id.repeatPassword);
	}

	private Button.OnClickListener register = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			username = username_txt.getText().toString();
			password = password_txt.getText().toString();
			confirm_password = repeatPassword_txt.getText().toString();
			if (validate()) {
				progressDialog = ProgressDialog.show(Register.this, "提示",
						"正在提交注册，请稍候...", true);
				registerService = new RegisterService(handler);
				registerService.register(username, password, username);
				Log.i(TAG, "call register webservice with username: "
						+ username + " password: " + password);
			}
		}
	};

	private void callLoadingUI() {
		Intent intent = new Intent();
		Bundle userInfo = new Bundle();
		userInfo.putString("username", username.toString());
		userInfo.putString("password", password.toString());
		intent.putExtra("android.intent.extra.userInfo", userInfo);
		intent.setClass(Register.this, Loading.class);
		startActivity(intent);
		finish();
	}

	private boolean validate() {
		boolean flag = true;
		if (username.equals("")) {
			Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
			username_txt.startAnimation(shake);
			flag = flag && false;
			Log.i(TAG, "username validate: " + flag);
		}
		if (password.equals("")) {
			Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
			password_txt.startAnimation(shake);
			flag = flag && false;
			Log.i(TAG, "password validate: " + flag);
		}
		if (confirm_password.equals("")) {
			Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
			repeatPassword_txt.startAnimation(shake);
			flag = flag && false;
			Log.i(TAG, "confirm_password validate: " + flag);
		}
		if (!password.equals(confirm_password)) {
			flag = flag && false;
			Toast.makeText(Register.this, Consts.PASSOWORD_ERROR,
					Toast.LENGTH_SHORT).show();
			Log.i(TAG, "confirm_password not equal with password!");
		}
		return flag;
	}

}
