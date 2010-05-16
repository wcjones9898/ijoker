package cn.edu.xmu.software.ijoke.serviceimpl;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.factory.UserFactory;
import cn.edu.xmu.software.ijoke.service.LoginService;
import cn.edu.xmu.software.ijoke.utils.Consts;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
public class LoginServiceImpl implements LoginService {
	

	public String loginService(String userName, String passWord) {
	
		
		System.out.println("In LoginServiceImpl Test authorization userName="+userName+" passWord="+passWord);
		User user = UserFactory.createUser(userName, passWord);
		if (user==null||!(passWord.equals(user.getPassWord())))
			return Consts.LOGIN_FAIL;
		else
			return user.getUserId();
	}

	@Test
	public void testLoginService()
	{
		System.out.println(loginService("ijoker2","123"));
	}


}
