package cn.edu.xmu.software.ijoker.util;

public class Consts {

	public static final String ACTION_STOP_PLAY = "cn.edu.xmu.software.ijoker.action.STOP_PLAY";
	public static final String ACTION_JOKELIST_READY = "cn.edu.xmu.software.ijoker.action.JOKELIST_READY";
	public static final String ACTION_DIVISIONLIST_READY = "cn.edu.xmu.software.ijoker.action.DIVISIONLIST_READY";
	public static final String ACTION_LIKE_READY = "cn.edu.xmu.software.ijoker.action.LIKE_READY";
	public static final String ACTION_LIKE_REQUEST = "cn.edu.xmu.software.ijoker.action.LIKE_QEQUEST";
	public static final String INTENT_PLAY_SERVICE = "cn.edu.xmu.software.ijoker.START_PLAY_SERVICE";
	public static final String METHODNAME_GETJOKELIST = "jokeListService";
	public static final String METHODNAME_GETDIVISIONLIST = "topicListService";
	public static final String METHODNAME_AUTHORIZATION = "loginService";
	public static final String METHODNAME_SCORE = "scoreService";
	public static final String AUTHORIZATIONRETURN = "loginServiceReturn";
	public static final String DIVISIONLISTRETURN = "topicListServiceReturn";
	public static final String JOKELISTRETURN = "jokeListServiceReturn";
	public static final String USER_NOTEXIST = "User not exist!";
	public static final String NETWORK_FAILED = "Network connection failed!";
	public static final String SCORE_SUCCESS = "Score succeed!";
	public static final String MEDIA_CENTER_BASE_URL = "http://59.77.5.42:80";
	public static final String SERVICE_BASE_URL = "http://59.77.5.42:8080/ijoker-server/services/";
	public static final int PAGESIZE = 5;
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
	public static final int ERROR_CALLWEBSERVICE = 0x11;
	public static final int MSG_DIVISIONLIST_UPDATE = 0x12;
	public static final int MSG_DIVISIONLIST_READY = 0x13;
	public static final int FLAG_CALLER_ID_JOKELIST = 0x14;
	public static final int FLAG_CALLER_ID_DIVISIONLIST = 0x15;
	public static final int MSG_LIKE_REQUST = 0x16;
	public static final int MSG_LIKE_SUCCEED = 0x17;
	public static final int FLAG_CALLER_ID_LIKE = 0x18;
	public static final int ERROR_UPLOAD = 0x19;
	public static final int STATUS_PLAYING = 0x20;
	public static final int STATUS_PAUSED = 0x21;
	public static final int STATUS_STOPPED = 0x22;
	public static final int STATUS_INIT = 0x23;
	public static final int CMD_UPLOAD = 0x24;
	// user login session
	public final static String preferencesSetting = "IJoker";
	public final static String session = "session";
	public final static String autoLogin = "autoLogin";
	public final static String username = "username";
	public final static String password = "password";
	public final static String rememberPassword = "rememberPassword";
}
