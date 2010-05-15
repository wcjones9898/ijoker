package cn.edu.xmu.software.ijoker.UI;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
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
import android.widget.Toast;
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
	private boolean isRecording;
	private boolean isPlaying;
	private ProgressDialog progressDialog;
	private static final String TAG = RecorderUI.class.getName();
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			progressDialog.dismiss();
			switch (msg.what) {
			case Consts.UPLOAD_SUCCESS:
				Toast.makeText(RecorderUI.this, Consts.UPLOAD_SUCCESSFUL,
						Toast.LENGTH_SHORT).show();
				upload_btn.setEnabled(false);
				recorderService.clearRecord();
				clear_btn.setEnabled(false);
				break;
			case Consts.ERROR_UPLOAD:
				Toast.makeText(RecorderUI.this, Consts.UPLOAD_ERROR,
						Toast.LENGTH_SHORT).show();
				break;
			case Consts.STATUS_LISTEN_STOP:
				listen_btn.setEnabled(false);
				listen_btn.setText("试听");
				isPlaying = !isPlaying;
				break;
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
		listen_btn.setEnabled(false);
		record_btn = (Button) findViewById(R.id.record_btn);
		clear_btn = (Button) findViewById(R.id.clear_btn);
		clear_btn.setEnabled(false);
		upload_btn = (Button) findViewById(R.id.upload_btn);
		upload_btn.setEnabled(false);
		record_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isRecording) {
					recorderService.startRecord();
					record_btn.setText("停止");
				} else {
					recorderService.stopRecord();
					listen_btn.setEnabled(true);
					clear_btn.setEnabled(true);
					record_btn.setEnabled(false);
					upload_btn.setEnabled(true);
					record_btn.setText("录音");
				}
				isRecording = !isRecording;
			}
		});
		clear_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				recorderService.clearRecord();
				record_btn.setEnabled(true);
				clear_btn.setEnabled(false);
				listen_btn.setEnabled(false);
				upload_btn.setEnabled(false);
			}
		});
		upload_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				jokeTitle = jokeTitle_txt.getText().toString();
				keyword = keyword_txt.getText().toString();
				if (validate()) {
					progressDialog = ProgressDialog.show(RecorderUI.this, "提示",
							"正在上传笑话，请稍候...", true);
					SharedPreferences settings = getSharedPreferences(
							Consts.preferencesSetting, 0);
					userId = settings.getString(Consts.userId, "");
					Log.i(TAG, "save data to preferences with userId: "
							+ userId);
					recorderService.uploadFile(userId, jokeTitle, keyword);
				}
			}
		});
		listen_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPlaying) {
					recorderService.listenRecord();
					listen_btn.setText("停止");
				} else {
					recorderService.stopListen();
					listen_btn.setText("试听");
				}
				isPlaying = !isPlaying;
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
