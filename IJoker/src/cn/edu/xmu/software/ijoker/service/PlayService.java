package cn.edu.xmu.software.ijoker.service;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.util.Consts;

public class PlayService extends Service {
	public final String TAG = PlayService.class.getName();
	private MediaPlayer mp = null;
	private Joke playingJoke;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Consts.CMD_PAUSE:
				mp.pause();
				break;
			case Consts.CMD_PLAY:
				Joke newJoke = msg.getData().getParcelable("data");
				playJoke(newJoke);
				break;
			case Consts.CMD_STOP:
				if (mp != null) {
					mp.stop();
					mp.release();
					mp = null;
				}
				break;
			default:
			}

		}

	};

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	private void playJoke(Joke newJoke) {
		try {

			Log.i("play a new joke: ", newJoke.getLocation());
			playingJoke = newJoke;
			if (mp == null)
				mp = new MediaPlayer();
			else
				mp.reset();
			mp.setDataSource(playingJoke.getLocation());
			mp.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer mp) {
					mp.start();
				}

			});
			mp.prepare();
			mp.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer arg0) {
					if (mp != null) {
						mp.release();
						mp = null;
					}
					Intent intent = new Intent(Consts.ACTION_STOP_PLAY);
					sendBroadcast(intent);
				}
			});
			mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {

				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					Log.e(TAG, "MediaPlayer error!");
					if (mp != null) {
						mp.release();
						mp = null;
					}
					Intent intent = new Intent(Consts.ACTION_STOP_PLAY);
					sendBroadcast(intent);
					return false;
				}
			});
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			if (mp != null) {
				mp.stop();
				mp.release();
				mp = null;
			}
			Intent intent = new Intent(Consts.ACTION_ERROR_PLAY);
			sendBroadcast(intent);
		}
	}

	private final IPlayService.Stub mBinder = new IPlayService.Stub() {

		@Override
		public void pause() throws RemoteException {
			Message message = handler.obtainMessage(Consts.CMD_PAUSE);
			handler.sendMessage(message);
		}

		@Override
		public void stop() throws RemoteException {
			Message message = handler.obtainMessage(Consts.CMD_STOP);
			handler.sendMessage(message);

		}

		@Override
		public void play(Joke joke) throws RemoteException {
			Message message = handler.obtainMessage(Consts.CMD_PLAY);
			Bundle b = new Bundle();
			b.putParcelable("data", joke);
			message.setData(b);
			handler.sendMessage(message);

		}

		@Override
		public Joke getJokePlaying() throws RemoteException {
			return playingJoke;
		}

		@Override
		public boolean isPlaying() throws RemoteException {
			if (mp != null) {
				Log.i(TAG, "the mediaplayer playing status: " + mp.isPlaying());
				return mp.isPlaying();
			} else
				return false;
		}

	};

	public void onCreate() {
		super.onCreate();
	}

	public void onDestroy() {
		super.onDestroy();
		if (mp != null) {
			mp.stop();
			mp.release();
			mp = null;
		}
	}

}
