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
import android.os.Bundle;
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
				// if (jokeList == null)
				initJokeList(msg.getData().getInt("page"));
				break;
			case Consts.MSG_DIVISIONLIST_UPDATE:
				// if (divisionList == null)
				initDivisionList();
				break;
			case Consts.MSG_LIKE_REQUST:
				scoreIt();
				break;
			case Consts.MSG_LIKE_SUCCEED:
				Joke joke = jokeList.get(currentPosition);
				joke.like();
				jokeList.set(currentPosition, joke);
				Intent intent0 = new Intent(Consts.ACTION_LIKE_READY);
				intent0.putExtra("errorCode", Consts.ERROR_NOERROR);
				sendBroadcast(intent0);
				Log
						.i(TAG,
								"playservice invoke webservice and make a score succeed!");
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
				else if (methodName.equalsIgnoreCase(Consts.METHODNAME_SCORE))
					i.setAction(Consts.ACTION_LIKE_READY);
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
//				mp.setOnErrorListene(new );
			}
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}

	private final IPlayService.Stub mBinder = new IPlayService.Stub() {

		@Override
		public void pause() throws RemoteException {
			Notification notification = new Notification();
			nm.notify(NOTIFY_ID, notification);
			mp.pause();
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
		public void like() throws RemoteException {
			Message message = handler.obtainMessage(Consts.MSG_LIKE_REQUST);
			handler.sendMessage(message);
		}

		//
		// @Override
		// public void updateJokeList() throws RemoteException {
		// Message message = handler.obtainMessage(Consts.MSG_JOKELIST_UPDATE);
		// handler.sendMessage(message);
		// }

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

		@Override
		public void updateJokeList(int page) throws RemoteException {
			Message message = handler.obtainMessage(Consts.MSG_JOKELIST_UPDATE);
			Bundle bundle = new Bundle();
			bundle.putInt("page", page);
			message.setData(bundle);
			handler.sendMessage(message);

		}
	};

	public void initJokeList(int page) {
		Log.i(TAG, "init the jokelist for playservice!");
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("classId", "1");
		parms.put("begin", page);
		parms.put("limit", Consts.PAGESIZE);
		wsEngine.doStart(Consts.METHODNAME_GETJOKELIST, parms);
	}

	public void initDivisionList() {
		Log.i(TAG, "init the divisionlist for playservice!");
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		wsEngine.doStart(Consts.METHODNAME_GETDIVISIONLIST, parms);
	}

	public void scoreIt() {
		Log.i(TAG, "make a score on the joke!");
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		int id = jokeList.get(currentPosition).getId();
		parms.put("jokeId", Integer.toString(id));
		wsEngine.doStart(Consts.METHODNAME_SCORE, parms);
	}

	public void onCreate() {
		super.onCreate();
		// initJokeList();
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	public void onDestroy() {
		super.onDestroy();
		if (mp.isPlaying())
			mp.stop();
		mp.release();
		nm.cancel(NOTIFY_ID);
	}

}
