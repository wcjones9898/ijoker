package cn.edu.xmu.software.ijoke.action;

import java.util.Map;

import cn.edu.xmu.software.ijoke.utils.Messages;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	
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
		
	public String getUserName() {
		return (String) getSession().get("username");
	}
	
	public String isLogined() {
		return (String) getSession().get("islogined");
	}
	
	public void showOperationMessage(boolean result){
		clearErrorsAndMessages();
		if (result==true)
			addActionMessage(Messages.OPERATION_SUCCESS);
		else 
			addActionMessage(Messages.OPERATION_FAIL);
		
	}

	
	
	

}
