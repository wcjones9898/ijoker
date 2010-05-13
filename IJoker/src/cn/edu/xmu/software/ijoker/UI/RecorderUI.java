package cn.edu.xmu.software.ijoker.UI;

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
import android.widget.ProgressBar;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.service.RecorderService;
import cn.edu.xmu.software.ijoker.util.Consts;

public class RecorderUI extends BaseActivity {

	private EditText jokeTitle_txt, keyword_txt;
	private String jokeTitle;
	private String keyword;
	private String userId;
	private ProgressBar record_progress;
	private Button listen_btn, record_btn, clear_btn, upload_btn;
	private RecorderService recorderService;
	private static final String TAG = RecorderUI.class.getName();
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Consts.CMD_UPLOAD:
				jokeTitle = jokeTitle_txt.getText().toString();
				keyword = keyword_txt.getText().toString();
				if (validate()) {
					SharedPreferences settings = getSharedPreferences(
							Consts.preferencesSetting, 0);
					userId = settings.getString(Consts.userId, "");
					Log.i(TAG, "save data to preferences with userId: "
							+ userId);
					recorderService.uploadFile(userId, jokeTitle, keyword);
				}
				break;
			case Consts.STATUS_RECORD_PLAYING:
				Log.i(TAG, "uploadService");
				recorderService.startRecord();
				break;
			case Consts.STATUS_RECORD_STOPPED:
				recorderService.stopRecord();
			case Consts.STATUS_LISTEN_START:
				recorderService.listenRecord();
			default:
			}

		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recorder);
		recorderService = new RecorderService(handler);
		find();
	}

	private void find() {
		jokeTitle_txt = (EditText) findViewById(R.id.jokeTitle_txt);
		keyword_txt = (EditText) findViewById(R.id.keyword_txt);
		record_progress = (ProgressBar) findViewById(R.id.record_progress);
		listen_btn = (Button) findViewById(R.id.listen_btn);
		record_btn = (Button) findViewById(R.id.record_btn);
		clear_btn = (Button) findViewById(R.id.clear_btn);
		clear_btn.setText("停止");
		upload_btn = (Button) findViewById(R.id.upload_btn);
		record_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Message message = handler
						.obtainMessage(Consts.STATUS_RECORD_PLAYING);
				handler.sendMessage(message);
			}
		});
		clear_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Message message = handler
						.obtainMessage(Consts.STATUS_RECORD_STOPPED);
				handler.sendMessage(message);
			}
		});
		upload_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Message message = handler.obtainMessage(Consts.CMD_UPLOAD);
				handler.sendMessage(message);
			}
		});
		listen_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Message message = handler
						.obtainMessage(Consts.STATUS_LISTEN_START);
				handler.sendMessage(message);
			}
		});
	}

	private boolean validate() {
		boolean flag = true;
		if (jokeTitle.equals("")) {
			Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
			jokeTitle_txt.startAnimation(shake);
			flag = flag && false;
			Log.i(TAG, "jokeTitle validate: " + flag);
		}
		if (keyword.equals("")) {
			Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
			keyword_txt.startAnimation(shake);
			flag = flag && false;
			Log.i(TAG, "keyword validate: " + flag);
		}
		return flag;
	}
}
