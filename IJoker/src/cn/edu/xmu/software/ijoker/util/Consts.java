package cn.edu.xmu.software.ijoker.util;

public class Consts {

	public static final String ACTION_STOP_PLAY = "cn.edu.xmu.software.ijoker.action.STOP_PLAY";
	public static final String ACTION_JOKELIST_READY = "cn.edu.xmu.software.ijoker.action.JOKELIST_READY";
	public static final String INTENT_PLAY_SERVICE = "cn.edu.xmu.software.ijoker.START_PLAY_SERVICE";
	public static final String METHODNAME_GETJOKELIST = "getJokeListService";
	public static final String METHODNAME_AUTHORIZATION = "loginService";
	public static final String AUTHORIZATIONRETURN = "loginServiceReturn";
	public static final String JOKELISTRETURN = "jokeListServiceReturn";
	public static final String USER_NOTEXIST = "User not exist!";
	public static final String NETWORK_FAILED = "Network connection failed!";
	public static final String BASE_URL = "http://59.77.5.181:8080/";
	public static final String SERVICE_BASE_URL = "http://59.77.5.181:8080/ijoker-server/services/";
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

	// user login session
	public final static String preferencesSetting = "IJoker";
	public final static String session = "session";
	public final static String autoLogin = "autoLogin";
	public final static String username = "username";
	public final static String password = "password";
	public final static String rememberPassword = "rememberPassword";
}
