package cn.edu.xmu.software.ijoker.util;

import android.view.Menu;

public class MenuUtils {
	
	public static final int LISTEN_JOKE = 1;
	public static final int RECORD_JOKE = 2;
	public static final int FIND_JOKE = 3;
	public static final int LOGOUT = 4;
	public static final int EXIT = 5;
	
	
	public static boolean addCommonMenu(Menu menu){
		
		menu.add(0, LISTEN_JOKE, 0, "听笑话");
        menu.add(0, RECORD_JOKE, 0, "讲笑话");
        menu.add(0, FIND_JOKE, 0, "找笑话");
        menu.add(0, LOGOUT, 0, "注销用户");
        menu.add(0, EXIT, 0, "退出");
        
		return true;
	}

}
