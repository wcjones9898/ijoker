package cn.edu.xmu.software.ijoke.action;

import java.io.ByteArrayInputStream;

import cn.edu.xmu.software.ijoke.service.LoginService;
import cn.edu.xmu.software.ijoke.utils.Consts;
import cn.edu.xmu.software.ijoke.utils.Messages;
import cn.edu.xmu.software.ijoke.utils.RandomNumUtil;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class LoginAction extends BaseAction{
	private String username;
	private String password; 
	private String loginType;	
	private String verifyStr;
	private ByteArrayInputStream inputStream;  
	
	private LoginService loginService;	
	
	public LoginAction(){		
	}
	
	public String execute(){
		System.out.println("loging:"+username+" "+password+" "+verifyStr+" "+RandomNumUtil.Instance().getString()+(verifyStr.equals(RandomNumUtil.Instance().getString())));
		if (!verifyStr.equals(RandomNumUtil.Instance().getString())){
			this.clearErrorsAndMessages();
			this.addActionMessage(Messages.VERIFY_CODE_ERROR); 
			
			return INPUT;
		}
		String loginResult=loginService.loginService(username, password);
		if (loginResult==Consts.LOGIN_SUCCESS){
			getSession().put("username", username);			
			getSession().put("islogined", LOGINED);			
		} else {
			this.clearErrorsAndMessages();
			this.addActionMessage(Messages.LOGIN_FAIL);			
		}
		
		return loginResult;
	}
	
	@SuppressWarnings("unchecked")
	public String generateRand() throws Exception {
		 RandomNumUtil rdnu=RandomNumUtil.Instance();
	     this.setInputStream(rdnu.getImage());//取得带有随机字符串的图片   
	     System.out.println("rdnu.getString()="+rdnu.getString());	     
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

	public String getVerifyStr() {
		return verifyStr;
	}

	public void setVerifyStr(String verifyStr) {
		this.verifyStr = verifyStr;
	}
	
	
	
	
	
}
