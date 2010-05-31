package cn.edu.xmu.software.ijoker.service;

import java.io.File;
import java.util.HashMap;

import android.os.Handler;
import android.util.Log;
import cn.edu.xmu.software.ijoker.engine.WSEngine;
import cn.edu.xmu.software.ijoker.exception.CallWebServiceException;
import cn.edu.xmu.software.ijoker.util.Consts;
import cn.edu.xmu.software.ijoker.ws.AbstractWSMethod;
import cn.edu.xmu.software.ijoker.ws.CallWSMethodFactory;

public class CartoonManageService extends AbstractService{


	public CartoonManageService(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
	}

	public void getCartoonList(String jokeId) {
		Log.i(TAG, "get the cartoon for jokeId: " + jokeId);
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("jokeId", jokeId);
		wsEngine.doStart(Consts.METHODNAME_GETCARTOONLIST, parms);
	}
	public void getCartoonList(Integer cartoonId) {
		Log.i(TAG, "get the cartoon for cartoonId: " + cartoonId);
		WSEngine wsEngine = new WSEngine(handler);
		HashMap<String, Object> parms = new HashMap<String, Object>();
		parms.put("cartoonId", cartoonId);
//		try {
//			AbstractWSMethod abstractWSMethod=CallWSMethodFactory.CreateWSMethod(Consts.METHODNAME_GETCARTOONLIST,
//					handler, parms);
//			try {
//				abstractWSMethod.invokeWSMethod();
//			} catch (CallWebServiceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		wsEngine.doStart(Consts.METHODNAME_GETCARTOONLIST, parms);
		//wsEngine.start();
	}
}
