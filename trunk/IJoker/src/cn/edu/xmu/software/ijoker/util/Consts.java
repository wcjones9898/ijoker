package cn.edu.xmu.software.ijoker.util;

import cn.edu.xmu.software.ijoker.R;

public class Consts {
	public static final int NOTIFY_ID = R.layout.player;
	public static final String ACTION_STOP_PLAY = "cn.edu.xmu.software.ijoker.action.STOP_PLAY";
	public static final String ACTION_JOKELIST_READY = "cn.edu.xmu.software.ijoker.action.JOKELIST_READY";
	public static final String INTENT_PLAY_SERVICE = "cn.edu.xmu.software.ijoker.START_PLAY_SERVICE";
	public static final String METHODNAME_GETJOKELIST = "getJokeList";
	public static final String METHODNAME_AUTHENTICATE = "authenticate";
	public static final int MSG_JOKELIST_UPDATE = 1;
	public static final int MSG_JOKELIST_READY = 2;
	public static final int MSG_LOGIN_REQEST = 3;
	public static final int MSG_LOGIN_READY = 4;
}
