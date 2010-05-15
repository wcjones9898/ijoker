package cn.edu.xmu.software.ijoker.UI;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.service.IPlayService;
import cn.edu.xmu.software.ijoker.service.PlayService;
import cn.edu.xmu.software.ijoker.service.ScoreService;
import cn.edu.xmu.software.ijoker.util.Consts;

public class PlayerUI extends BaseActivity {

	private ImageView head_img, level_img;
	private TextView author_txt, uploadTime_txt, title_txt;
	private ImageButton like_btn, play_btn, share_btn;
	private ProgressBar progress_bar;
	private Joke joke;
	private IPlayService playService;
	private ScoreService scoreService;
	// private int player_position = 0;
	public boolean is_valid = false;
	private static final String TAG = PlayerUI.class.getName();
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Consts.MSG_LIKE_SUCCEED:
				Toast.makeText(PlayerUI.this, Consts.SCORE_SUCCESS,
						Toast.LENGTH_SHORT).show();
				Log
						.i(TAG,
								"playservice invoke webservice and make a score succeed!");

				break;
			case Consts.ERROR_CALLWEBSERVICE:
				Toast.makeText(PlayerUI.this, Consts.NETWORK_FAILED,
						Toast.LENGTH_SHORT).show();
				break;
			default:
			}

		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		revParams();
		find();
		// loadValues();
		this.bindService(new Intent(PlayerUI.this, PlayService.class),
				serviceConnection, Context.BIND_AUTO_CREATE);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Consts.ACTION_STOP_PLAY);
		intentFilter.addAction(Consts.ACTION_LIKE_READY);
		registerReceiver(receiver, intentFilter);
	}

	// 接收传递进来的笑话信息
	private void revParams() {
		Intent intent = getIntent();
		if (intent != null) {
			Bundle jokeInfo = intent
					.getBundleExtra("android.intent.extra.jokeInfo");
			if (jokeInfo == null)
				is_valid = false;
			else
				joke = (Joke) jokeInfo.getParcelable("joke");
			is_valid = true;
		} else
			is_valid = false;
	}

	private ServiceConnection serviceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			playService = IPlayService.Stub.asInterface((IBinder) service);
			loadValues();
		}

		public void onServiceDisconnected(ComponentName className) {
			playService = null;
		}
	};

	private void find() {
		head_img = (ImageView) findViewById(R.id.head_img);
		level_img = (ImageView) findViewById(R.id.level_img);
		author_txt = (TextView) findViewById(R.id.author_txt);
		uploadTime_txt = (TextView) findViewById(R.id.uploadtime_txt);
		title_txt = (TextView) findViewById(R.id.title_txt);
		like_btn = (ImageButton) findViewById(R.id.like_btn);
		play_btn = (ImageButton) findViewById(R.id.play_btn);
		share_btn = (ImageButton) findViewById(R.id.share_btn);
		like_btn.setOnClickListener(like);
		play_btn.setOnClickListener(play);
		play_btn.setOnTouchListener(playTouched);
		share_btn.setOnClickListener(share);

		progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
	}

	private void loadValues() {
		head_img.setBackgroundResource(R.drawable.head);
		author_txt.setText(joke.getAuthor());
		uploadTime_txt.setText("上传时间: " + joke.getUploadTime().toString());
		level_img.setBackgroundResource(R.drawable.star3);
		title_txt.setText(joke.getTitle());
		try {
			if (playService.isPlaying()) {
				Joke playingJoke = playService.getJokePlaying();
				if (playingJoke.getId() == joke.getId()) {
					play_btn.setBackgroundResource(R.drawable.pause);
					play_btn.setOnClickListener(pause);
					play_btn.setOnTouchListener(pauseTouched);
				}
			}
		} catch (RemoteException e) {
			Log.i(TAG, e.getMessage(), e);
		}
		progress_bar.setMax(100);
		progress_bar.setProgress(30);
		progress_bar.setSecondaryProgress(70);

	}

	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			play_btn.setBackgroundResource(R.drawable.play);
			play_btn.setOnClickListener(play);
			play_btn.setOnTouchListener(playTouched);
		}
	};
	private ImageButton.OnClickListener like = new ImageButton.OnClickListener() {
		@Override
		public void onClick(View v) {
			scoreService = new ScoreService(handler);
			scoreService.scoreIt(joke.getId());
			like_btn.setEnabled(false);
		}
	};

	private ImageButton.OnClickListener share = new ImageButton.OnClickListener() {
		@Override
		public void onClick(View v) {
			// try {
			// // playService.like();
			// } catch (RemoteException e) {
			// Log.e(TAG, e.getMessage(), e);
			// }
		}
	};

	private ImageButton.OnClickListener play = new ImageButton.OnClickListener() {
		@Override
		public void onClick(View v) {
			try {
				playService.play(joke);
			} catch (RemoteException e) {
				Log.e(TAG, e.getMessage(), e);
			}
			play_btn.setOnClickListener(pause);
			play_btn.setOnTouchListener(pauseTouched);
		}
	};

	private ImageButton.OnClickListener pause = new ImageButton.OnClickListener() {
		@Override
		public void onClick(View v) {
			try {
				playService.pause();
			} catch (RemoteException e) {
				Log.e(TAG, e.getMessage(), e);
			}
			play_btn.setOnClickListener(play);
			play_btn.setOnTouchListener(playTouched);
		}
	};

	protected void onResume() {
		super.onResume();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Consts.ACTION_STOP_PLAY);
		intentFilter.addAction(Consts.ACTION_LIKE_READY);
		registerReceiver(receiver, intentFilter);
		this.bindService(new Intent(PlayerUI.this, PlayService.class),
				serviceConnection, Context.BIND_AUTO_CREATE);
	}

	protected void onDestroy() {
		super.onDestroy();
		unbindService(serviceConnection);
		unregisterReceiver(receiver);
	}

	// 点击效果
	private ImageButton.OnTouchListener playTouched = new ImageButton.OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				play_btn.setBackgroundResource(R.drawable.play_touched);
				break;
			case MotionEvent.ACTION_UP:
				play_btn.setBackgroundResource(R.drawable.pause);
				break;
			}
			return false;
		}
	};

	private ImageButton.OnTouchListener pauseTouched = new ImageButton.OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				play_btn.setBackgroundResource(R.drawable.pause_touched);
				break;
			case MotionEvent.ACTION_UP:
				play_btn.setBackgroundResource(R.drawable.play);
				break;
			}
			return false;
		}
	};

}
