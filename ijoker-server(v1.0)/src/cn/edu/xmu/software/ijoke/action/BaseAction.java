package cn.edu.xmu.software.ijoke.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class BaseAction extends ActionSupport{

	public static String TYPE_BANK="bank";
	public static String TYPE_COMPANY="company";	
	public static String LOGINED="logined";
	public static String UNLOGINED="unlogined";
	
	public BaseAction(){
	}	
	
	public boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	public String logout()
	{
		System.out.println("loging out ");		
		getSession().clear();
		return SUCCESS;
	}
	
	public Map getSession()
	{
		return ActionContext.getContext().getSession();
	}
	
	public String getUsertype(){		
		return (String) getSession().get("usertype");
	}
	
	public String getUserName() {
		return (String) getSession().get("username");
	}
	
	public String isLogined() {
		return (String) getSession().get("islogined");
	}

	
	
	

}
