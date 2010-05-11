package cn.edu.xmu.software.ijoker.service;

import java.io.IOException;
import java.util.ArrayList;
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
import cn.edu.xmu.software.ijoker.entity.ClassItem;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.util.Consts;

public class PlayService extends Service {
	public final String TAG = PlayService.class.getName();
	private MediaPlayer mp = new MediaPlayer();
	private ArrayList<Joke> jokeList;
	private ArrayList<ClassItem> divisionList;
	private int playingPosition = -1;
	private int currentPosition;
	private NotificationManager nm;
	public static final int NOTIFY_ID = R.layout.player;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Consts.MSG_JOKELIST_UPDATE:
				initJokeList();
				break;
			case Consts.MSG_DIVISIONLIST_UPDATE:
				initDivisionList();
				break;
			case Consts.MSG_JOKELIST_READY:
				jokeList = msg.getData().getParcelableArrayList("data");
				Intent intent1 = new Intent(Consts.ACTION_JOKELIST_READY);
				intent1.putExtra("errorCode", Consts.ERROR_NOERROR);
				sendBroadcast(intent1);
				Log
						.i(
								TAG,
								"playservice get the jokelist from webservice and pass the jokelist to jokelist UI!"
										+ jokeList.size());
				break;
			case Consts.MSG_DIVISIONLIST_READY:
				divisionList = msg.getData().getParcelableArrayList("data");
				Intent intent2 = new Intent(Consts.ACTION_DIVISIONLIST_READY);
				intent2.putExtra("errorCode", Consts.ERROR_NOERROR);
				sendBroadcast(intent2);
				Log
						.i(
								TAG,
								"playservice get the divisionlist from webservice and pass the divisionlist to divisionlist UI!"
										+ divisionList.size());
				break;
			case Consts.ERROR_CALLWEBSERVICE:
				Intent i = new Intent();
				String methodName = msg.getData().getString("methodName");
				if (methodName.equalsIgnoreCase(Consts.METHODNAME_GETJOKELIST))
					i.setAction(Consts.ACTION_JOKELIST_READY);
				else if (methodName
						.equalsIgnoreCase(Consts.METHODNAME_GETDIVISIONLIST))
					i.setAction(Consts.ACTION_DIVISIONLIST_READY);
				i.putExtra("errorCode", Consts.ERROR_CALLWEBSERVICE);
				sendBroadcast(i);
				break;
			default:
			}

		}

	};

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	private void playJoke(String file) {
		try {

			Notification notification = new Notification();
			nm.notify(NOTIFY_ID, notification);
			if (playingPosition == currentPosition) {
				mp.start();
				Log.i(TAG, "media player go on play the joke!");
			} else {
				Log.i("play a new joke: ", file);
				mp.reset();
				mp.setDataSource(file);

				playingPosition = currentPosition;
				mp.prepare();
				mp.start();
				mp.setOnCompletionListener(new OnCompletionListener() {
					public void onCompletion(MediaPlayer arg0) {
						Intent intent = new Intent(Consts.ACTION_STOP_PLAY);
						sendBroadcast(intent);
					}
				});
			}
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
			// if (jokeList == null) {
			// initJokeList();
			// }
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

		@Override
		public void updateJokeList() throws RemoteException {
			Message message = handler.obtainMessage(Consts.MSG_JOKELIST_UPDATE);
			handler.sendMessage(message);
		}

		@Override
		public Joke getJokePlaying() throws RemoteException {
			return jokeList.get(playingPosition);
		}

		@Override
		public boolean isPlaying() throws RemoteException {
			Log.i(TAG, "the mediaplayer playing status: " + mp.isPlaying());
			return mp.isPlaying();
		}

		@Override
		public List<ClassItem> getDivisionList() throws RemoteException {
			// TODO Auto-generated method stub
			return divisionList;
		}

		@Override
		public void updateDivisionList() throws RemoteException {
			Message message = handler
					.obtainMessage(Consts.MSG_DIVISIONLIST_UPDATE);
			handler.sendMessage(message);

		}
	};

	public void initJokeList() {
		Log.i(TAG, "init the jokelist for playservice!");
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("classId", "1");
		wsEngine.doStart(Consts.METHODNAME_GETJOKELIST, parms);
	}

	public void initDivisionList() {
		Log.i(TAG, "init the divisionlist for playservice!");
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		wsEngine.doStart(Consts.METHODNAME_GETDIVISIONLIST, parms);
	}

	public void onCreate() {
		super.onCreate();
		// initJokeList();
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	public void onDestroy() {
		super.onDestroy();
		mp.stop();
		mp.release();
		nm.cancel(NOTIFY_ID);
	}

}
