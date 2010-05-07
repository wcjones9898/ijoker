package cn.edu.xmu.software.ijoker.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.engine.WSEngine;
import cn.edu.xmu.software.ijoker.entity.Joke;

public class PlayService extends Service {
	public final String TAG = PlayService.class.getName();
	private MediaPlayer mp = new MediaPlayer();
	private List<Joke> jokeList;
	private int currentPosition;
	private NotificationManager nm;
	private static final int NOTIFY_ID = R.layout.player;
	public static final String ACTION_STOP_PLAY = "cn.edu.xmu.software.ijoker.action.STOP_PLAY";
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
		}

	};

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	private void playJoke(String file) {
		Log.i("playJoke:", file);
		try {

			Notification notification = new Notification();
			nm.notify(NOTIFY_ID, notification);
			Log.i("playJoke:", "------------------------");
			mp.reset();
			mp.setDataSource(file);
			mp.prepare();
			mp.start();
			mp.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer arg0) {
					Intent intent = new Intent(ACTION_STOP_PLAY);
					sendBroadcast(intent);
				}
			});
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}

	private final IPlayService.Stub mBinder = new IPlayService.Stub() {

		/*
		 * @Override public void play(String location) throws RemoteException {
		 * Log.i(TAG, "play file: " + location); try { // currentPosition =
		 * position; playJoke(location); } catch (IndexOutOfBoundsException e) {
		 * Log.e(TAG, e.getMessage()); } }
		 */

		@Override
		public void pause() throws RemoteException {
			Notification notification = new Notification();
			nm.notify(NOTIFY_ID, notification);
			mp.pause();
		}

		@Override
		public void addJokeList(String location) throws RemoteException {
			// jokeList.add(location);

		}

		@Override
		public void clearJokeList() throws RemoteException {
			jokeList.clear();

		}

		@Override
		public void stop() throws RemoteException {
			nm.cancel(NOTIFY_ID);
			mp.stop();
		}

		@Override
		public void chose(int position) throws RemoteException {
			currentPosition = position;
		}

		@Override
		public List<Joke> getJokeList() throws RemoteException {
			Log
					.i(
							TAG,
							"playservice get the jokelist from webservice and pass the jokelist to jokelist UI!"
									+ jokeList.size());
//			if (jokeList == null) {
				WSEngine wsEngine = new WSEngine(handler);
				HashMap<String, Object> parms = new HashMap<String, Object>();
				// parms.put("listStyle", 1);
				wsEngine.doStart("getJokeList", parms);
//			}
			return jokeList;
		}

		@Override
		public void play() throws RemoteException {
			playJoke(jokeList.get(currentPosition).getLocation());
		}

		@Override
		public void like(boolean isLike) throws RemoteException {
			// invoke the webservice to add the like to joke;
		}
	};

	public void onCreate() {
		super.onCreate();
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 生成动态数组，加入数据
		jokeList = new ArrayList<Joke>();
		Joke joke1 = new Joke();
		joke1.setAuthor("邱鸿斌");
		joke1.setId(1);
		joke1.setLocation("http://59.77.5.42:80/jokes/real.mp3");
		joke1.setTitle("joke1");
		joke1.setUploadTime(new Date().toString());
		jokeList.add(joke1);
		Joke joke2 = new Joke();
		joke2.setAuthor("白志斌");
		joke2.setId(2);
		joke2.setLocation("http://59.77.5.42:80/jokes/real.mp3");
		joke2.setTitle("joke2");
		joke2.setUploadTime(new Date().toString());
		jokeList.add(joke2);
		Joke joke3 = new Joke();
		joke3.setAuthor("翁晓奇");
		joke3.setId(3);
		joke3.setLocation("http://59.77.5.42:80/jokes/real.mp3");
		joke3.setTitle("joke3");
		joke3.setUploadTime(new Date().toString());
		jokeList.add(joke3);
		Log.i(TAG, "create jokeList: " + jokeList + "; size: "
				+ jokeList.size());
	}

	public void onDestroy() {
		super.onDestroy();
		mp.stop();
		mp.release();
		nm.cancel(NOTIFY_ID);
	}

}
