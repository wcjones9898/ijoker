package cn.edu.xmu.software.ijoker.service;

import java.util.HashMap;

import cn.edu.xmu.software.ijoker.engine.WSEngine;
import cn.edu.xmu.software.ijoker.util.Consts;
import android.os.Handler;
import android.util.Log;

public class ScoreService extends AbstractService {
	public ScoreService(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
	}

	public void scoreIt(String jokeId) {
		Log.i(TAG, "make a score on the joke!");
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("jokeId", jokeId);
		wsEngine.doStart(Consts.METHODNAME_SCORE, parms);
	}
}
