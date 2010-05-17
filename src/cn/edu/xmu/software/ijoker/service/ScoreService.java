package cn.edu.xmu.software.ijoker.service;

import java.util.HashMap;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import cn.edu.xmu.software.ijoker.engine.WSEngine;
import cn.edu.xmu.software.ijoker.util.Consts;

public class ScoreService extends AbstractService {
	public ScoreService(Handler handler, Context context) {
		super(handler, context);
		// TODO Auto-generated constructor stub
	}

	public void scoreIt(String jokeId) {
		Log.i(TAG, "make a score on the joke!");
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("jokeId", jokeId);
		parms.put(Consts.SERVER_URL, this.getServiceBaseURL());
		wsEngine.doStart(Consts.METHODNAME_SCORE, parms);
	}
}
