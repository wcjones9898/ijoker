package cn.edu.xmu.software.ijoker.UI;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.service.RecorderService;
import cn.edu.xmu.software.ijoker.util.Consts;

public class RecorderUI extends BaseActivity {

	private EditText jokeTitle_txt, keyword_txt;
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
				recorderService.uploadFile();
				break;
			case Consts.STATUS_PLAYING:
				Log.i(TAG, "uploadService");
				recorderService.onRestartRecorder();
				break;
			case Consts.STATUS_STOPPED:
				recorderService.onStopRecorder();
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
				Message message = handler.obtainMessage(Consts.STATUS_PLAYING);
				handler.sendMessage(message);
			}
		});
		clear_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Message message = handler.obtainMessage(Consts.STATUS_STOPPED);
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
	}

}
