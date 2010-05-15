package cn.edu.xmu.software.ijoker.service;

import java.util.HashMap;

import cn.edu.xmu.software.ijoker.engine.WSEngine;
import cn.edu.xmu.software.ijoker.util.Consts;
import android.os.Handler;
import android.util.Log;

public class JokeListService extends AbstractService {

	public JokeListService(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
	}

	public void getJokeList(int page) {
		Log.i(TAG, "get the jokelist for page: " + page);
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("classId", "1");
		parms.put("begin", page);
		parms.put("limit", Consts.PAGESIZE);
		wsEngine.doStart(Consts.METHODNAME_GETJOKELIST, parms);
	}
}
