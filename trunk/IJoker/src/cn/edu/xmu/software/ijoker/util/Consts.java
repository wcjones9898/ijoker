package cn.edu.xmu.software.ijoker.util;

public class Consts {

	public static final String ACTION_STOP_PLAY = "cn.edu.xmu.software.ijoker.action.STOP_PLAY";
	public static final String ACTION_ERROR_PLAY = "cn.edu.xmu.software.ijoker.action.ERROR_PLAY";
	public static final String ACTION_JOKELIST_READY = "cn.edu.xmu.software.ijoker.action.JOKELIST_READY";
	public static final String ACTION_DIVISIONLIST_READY = "cn.edu.xmu.software.ijoker.action.DIVISIONLIST_READY";
	public static final String ACTION_LIKE_READY = "cn.edu.xmu.software.ijoker.action.LIKE_READY";
	public static final String ACTION_LIKE_REQUEST = "cn.edu.xmu.software.ijoker.action.LIKE_QEQUEST";
	public static final String INTENT_PLAY_SERVICE = "cn.edu.xmu.software.ijoker.START_PLAY_SERVICE";
	public static final String METHODNAME_GETJOKELIST = "jokeListService";
	public static final String METHODNAME_GETDIVISIONLIST = "topicListService";
	public static final String METHODNAME_AUTHORIZATION = "loginService";
	public static final String METHODNAME_SCORE = "scoreService";
	public static final String METHODNAME_REGISTER = "registerService";
	public static final String METHODNAME_SEARCH = "searchService";
	public static final String METHODNAME_GETCARTOONLIST = "cartoonService";
	public static final String AUTHORIZATIONRETURN = "loginServiceReturn";
	public static final String DIVISIONLISTRETURN = "topicListServiceReturn";
	public static final String JOKELISTRETURN = "jokeListServiceReturn";
	public static final String CARTOONLISTRETURN = "cartoonServiceReturn";
	public static final String REGISTERSERVICERETURN = "registerServiceReturn";
	public static final String SEARCHSERVICERETURN = "searchServiceReturn";
	public static final String USER_NOTEXIST = "User not exist or password wrong!";
	public static final String USER_EXIST = "User exist or something wrong,try again!";
	public static final String NETWORK_FAILED = "Network connection failed!";
	public static final String SEARCH_NOJOKES = "No result,please try other words!";
	public static final String PASSOWORD_ERROR = "confirm password not equal with password!";
	public static final String SCORE_SUCCESS = "Score succeed!";
	public static final String NOJOKE = "No joke in this division!";
	public static final String UPLOAD_SUCCESSFUL = "upload file successful!";
	public static final String UPLOAD_ERROR = "error exist during uploading!";
	public static final String ERROR_CANTNOT_PLAY = "cant not play this joke,try others!";
	public static final String ERROR_TIMEUP = "record time limited to 5 minites!";
	public static final String MEDIA_CENTER_BASE_URL = "http://59.77.5.42:80";
	public static String SERVER_IP = "59.77.5.42";

	public static String SERVER_DOWNLOAD_URL = "http://" + SERVER_IP+"/jokes";
	public static String SERVICE_BASE_URL = "http://" + SERVER_IP
			+ ":8080/ijoker-server/services/";
	public static String SERVER_UPLOAD_URL = "http://" + SERVER_IP
			+ ":8080/ijoker-server/servlet/UploadService";
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
	public static final int STATUS_RECORD_PLAYING = 0x20;
	public static final int STATUS_RECORD_PAUSED = 0x21;
	public static final int STATUS_RECORD_STOPPED = 0x22;
	public static final int STATUS_INIT = 0x23;
	public static final int CMD_UPLOAD = 0x24;
	public static final int STATUS_LISTEN_START = 0x25;
	public static final int STATUS_LISTEN_STOP = 0x26;
	public static final int MSG_REGISTER_REQEST = 0x27;
	public static final int MSG_REGISTER_READY = 0x28;
	public static final int ERROR_USERNAME_EXIST = 0x29;
	public static final int FLAG_REGISTER_FAILURE = 0x30;
	public static final int FLAG_REGISTER_SUCCESS = 0x31;
	public static final int MSG_SEARCHJOKE_UPDATE = 0x32;
	public static final int MSG_SEARCHJOKE_READY = 0x33;

	public final static int SEARCHTYPE_SEARCH_JOKE = 0;
	public final static int SEARCHTYPE_SEARCH_JOKER = 1;
	public static final int CMD_CLEAR = 0x34;
	public static final int UPLOAD_SUCCESS = 0x35;
	public static final int CMD_PLAY = 0x36;
	public static final int CMD_STOP = 0x37;
	public static final int CMD_PAUSE = 0x38;
	public static final int ERROR_PLAY = 0x39;
	public static final int MSG_PREPARE_PLAY = 0x40;
	public static final int MSG_STOP_PLAY = 0x41;
	public static final int MSG_RECORD_TIMEUP = 0x42;
	
	public static final int MSG_CARTOONLIST_READY = 0x43;
	public static final int MSG_CARTOONLIST_UPDATE = 0x44;
	public static final int MSG_CARTOONPIC_DATA = 0x45;
	
	public static final int CARTOONGALLERY_UPDATEUI = 0x46;
	// user login session
	public final static String preferencesSetting = "IJoker";
	public final static String session = "session";
	public final static String autoLogin = "autoLogin";
	public final static String username = "username";
	public final static String password = "password";
	public final static String rememberPassword = "rememberPassword";
	public final static String userId = "userId";
}
