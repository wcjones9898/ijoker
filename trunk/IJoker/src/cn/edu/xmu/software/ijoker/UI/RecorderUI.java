package cn.edu.xmu.software.ijoker.UI;

import java.io.File;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.engine.Uploader;
import cn.edu.xmu.software.ijoker.service.IRecordService;
import cn.edu.xmu.software.ijoker.service.RecorderService;
import cn.edu.xmu.software.ijoker.util.Consts;

public class RecorderUI extends BaseActivity {

	private EditText jokeTitle_txt, keyword_txt;
	private String jokeTitle;
	private String keyword;
	private String userId;
	private ProgressBar record_progress;
	private Button listen_btn, record_btn, clear_btn, upload_btn;
	private IRecordService recordService;
	private Uploader uploader;
	// private boolean isRecording;
	// private boolean isPlaying;
	private ProgressDialog progressDialog;
	private static final String TAG = RecorderUI.class.getName();
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Consts.UPLOAD_SUCCESS:
				progressDialog.dismiss();
				Toast.makeText(RecorderUI.this, Consts.UPLOAD_SUCCESSFUL,
						Toast.LENGTH_SHORT).show();
				upload_btn.setEnabled(false);

				// recorderService.clearRecord();
				clear_btn.setEnabled(false);
				break;
			case Consts.ERROR_UPLOAD:
				progressDialog.dismiss();
				Toast.makeText(RecorderUI.this, Consts.UPLOAD_ERROR,
						Toast.LENGTH_SHORT).show();
				break;
			case Consts.STATUS_LISTEN_STOP:
				listen_btn.setEnabled(false);
				listen_btn.setText("试听");
				break;
			case Consts.MSG_RECORD_TIMEUP:
				// recorderService.stopRecord();
				listen_btn.setEnabled(true);
				clear_btn.setEnabled(true);
				record_btn.setEnabled(false);
				upload_btn.setEnabled(true);
				record_btn.setText("录音");
				break;
			default:
			}
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recorder);
		find();
		this.bindService(new Intent(RecorderUI.this, RecorderService.class),
				mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unbindService(mConnection);
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
				try {
					if (recordService.getRecorderState() == 1) {
						recordService.stopRecorder();
						listen_btn.setEnabled(true);
						clear_btn.setEnabled(true);
						record_btn.setEnabled(false);
						upload_btn.setEnabled(true);
						record_btn.setText("录音");
					} else {
						recordService.startRecorder();
						record_btn.setText("停止");
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		clear_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// recorderService.clearRecord();
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
					try {
						uploader = new Uploader(handler);
						String filePath = recordService.getCurrentRecord();
						File currentRecord = new File(filePath);
						uploader.doStart(currentRecord, jokeTitle, keyword,
								userId);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		listen_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					if (recordService.getPlayerState() == 1) {
						recordService.stopPlayer();
						listen_btn.setText("试听");
					} else {
						recordService.startPlayer();
						listen_btn.setText("停止");
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			recordService = IRecordService.Stub.asInterface((IBinder) arg1);
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			recordService = null;

		}

	};
}
