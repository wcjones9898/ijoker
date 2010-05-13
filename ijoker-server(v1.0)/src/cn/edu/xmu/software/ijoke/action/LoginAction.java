package cn.edu.xmu.software.ijoke.action;

import java.io.ByteArrayInputStream;

import cn.edu.xmu.software.ijoke.service.LoginService;
import cn.edu.xmu.software.ijoke.utils.RandomNumUtil;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class LoginAction extends BaseAction{
	private String username;
	private String password; 
	private String loginType;
	private ByteArrayInputStream inputStream;  
	
	private LoginService loginService;	
	
	public LoginAction(){
	}
	
	public String execute(){
		System.out.println("loging");
		//System.out.println(loginService.loginService(username, password));
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String generateRand() throws Exception {
		 RandomNumUtil rdnu=RandomNumUtil.Instance();   
	     this.setInputStream(rdnu.getImage());//取得带有随机字符串的图片   
	     ActionContext.getContext().getSession().put("random", rdnu.getString());//取得随机字符串放入HttpSession   
	     return SUCCESS;   
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getLoginType() {
		return loginType;
	}


	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	
	
}
