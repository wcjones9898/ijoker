package cn.edu.xmu.software.ijoker.service;

import java.util.HashMap;

import android.os.Handler;
import android.util.Log;
import cn.edu.xmu.software.ijoker.engine.WSEngine;
import cn.edu.xmu.software.ijoker.util.Consts;

public class SearchService {
	private static final String TAG = SearchService.class.getName();
	private Handler handler;

	public SearchService(Handler handler) {
		this.handler = handler;
	}

	public void search(String keyword, int searchType) {
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("info", keyword);
		parms.put("searchType", searchType);
		wsEngine.doStart(Consts.METHODNAME_SEARCH, parms);
		Log.i(TAG, "call webservice to search keyword: " + keyword
				+ " searchType: " + searchType);
	}
}
