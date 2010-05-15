package cn.edu.xmu.software.ijoker.service;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.util.Consts;

public class PlayService extends Service {
	public final String TAG = PlayService.class.getName();
	private MediaPlayer mp = new MediaPlayer();
	private Joke playingJoke;

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	private void playJoke(Joke joke) {
		try {

			if (joke.getId().equals(playingJoke.getId())) {
				mp.start();
				Log.i(TAG, "media player go on play the joke!");
			} else {
				Log.i("play a new joke: ", joke.getLocation());
				mp.reset();
				mp.setDataSource(joke.getLocation());
				mp.prepare();
				mp.start();
				mp.setOnCompletionListener(new OnCompletionListener() {
					public void onCompletion(MediaPlayer arg0) {
						Intent intent = new Intent(Consts.ACTION_STOP_PLAY);
						sendBroadcast(intent);
					}
				});
				mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {

					@Override
					public boolean onError(MediaPlayer mp, int what, int extra) {
						Log.e(TAG, "MediaPlayer error!");
						return false;
					}
				});
			}
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}

	private final IPlayService.Stub mBinder = new IPlayService.Stub() {

		@Override
		public void pause() throws RemoteException {
			mp.pause();
		}

		@Override
		public void stop() throws RemoteException {
			mp.stop();
		}

		@Override
		public void play(Joke joke) throws RemoteException {
			playingJoke = joke;
			playJoke(joke);
		}

		@Override
		public Joke getJokePlaying() throws RemoteException {
			return playingJoke;
		}

		@Override
		public boolean isPlaying() throws RemoteException {
			Log.i(TAG, "the mediaplayer playing status: " + mp.isPlaying());
			return mp.isPlaying();
		}

	};

	public void onCreate() {
		super.onCreate();
	}

	public void onDestroy() {
		super.onDestroy();
		mp.stop();
		mp.release();
	}

}
