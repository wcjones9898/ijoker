package cn.edu.xmu.software.ijoker.service;

import android.os.Handler;

public class AbstractService {
	protected Handler handler;
	protected final static String TAG = AbstractService.class.getName();

	public AbstractService(Handler handler) {
		this.handler = handler;
	}
}
