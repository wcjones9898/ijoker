package cn.edu.xmu.software.ijoker.service;

import java.util.HashMap;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import cn.edu.xmu.software.ijoker.engine.WSEngine;
import cn.edu.xmu.software.ijoker.util.Consts;

public class JokeListService extends AbstractService {

	public JokeListService(Handler handler, Context context) {
		super(handler, context);
		// TODO Auto-generated constructor stub
	}

	public void getJokeList(int page, String classId) {
		Log.i(TAG, "get the jokelist for page: " + page);
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("catalogId", classId);
		parms.put("begin", page);
		parms.put("limit", Consts.PAGESIZE);
		parms.put(Consts.MEDIA_URL, this.getMediaCenterURL());
		parms.put(Consts.SERVER_URL, this.getServiceBaseURL());
		wsEngine.doStart(Consts.METHODNAME_GETJOKELIST, parms);
	}
}
