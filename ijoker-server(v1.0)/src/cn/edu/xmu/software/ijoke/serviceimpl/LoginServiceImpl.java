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
			return null;
		else
			return user.getUserId();
	}

	public String adminLoginService(String userName, String passWord) {
		// TODO Auto-generated method stub
		return AppFactory.getAdminLoginService().login(userName, passWord);
		
	}
	@Test
	public void testAdminLoginService()
	{
		System.out.println(adminLoginService("admin","123"));
		System.out.println(adminLoginService("admin0","123"));
		System.out.println(adminLoginService("admin",""));
		
	}
}
