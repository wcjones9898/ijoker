package cn.edu.xmu.software.ijoker.util;

import cn.edu.xmu.software.ijoker.R;

public class Consts {
	public static final int NOTIFY_ID = R.layout.player;
	public static final String ACTION_STOP_PLAY = "cn.edu.xmu.software.ijoker.action.STOP_PLAY";
	public static final String ACTION_JOKELIST_READY = "cn.edu.xmu.software.ijoker.action.JOKELIST_READY";
	public static final String INTENT_PLAY_SERVICE = "cn.edu.xmu.software.ijoker.START_PLAY_SERVICE";
	public static final String METHODNAME_GETJOKELIST = "getJokeList";
	public static final String METHODNAME_AUTHENTICATE = "authenticate";
	public static final String USER_NOTEXIST = "user not exist!";
	public static final int MSG_JOKELIST_UPDATE = 0x1;
	public static final int MSG_JOKELIST_READY = 0x2;
	public static final int MSG_LOGIN_REQEST = 0x3;
	public static final int MSG_LOGIN_READY = 0x4;
	public static final int GUI_STOP_NOTIFIER = 0X5;
	public static final int GUI_THREADING_NOTIFIER = 0x6;
	public static final int FLAG_LOGIN_FAILURE = 0x7;
	public static final int FLAG_LOGIN_SUCCESS = 0x8;
	public static final int ERROR_USERNAME_NOEXIST = 0x9;
	public static final int ERROR_NOERROR = 0x10;
}
