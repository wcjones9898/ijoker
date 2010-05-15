package cn.edu.xmu.software.ijoke.serviceimpl;

import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.factory.UserFactory;
import cn.edu.xmu.software.ijoke.service.LoginService;
import cn.edu.xmu.software.ijoke.utils.Consts;

public class LoginServiceImpl implements LoginService {
	

	public String loginService(String userName, String passWord) {
	
		
		System.out.println("In LoginServiceImpl Test authorization userName="+userName+" passWord="+passWord);
		User user = UserFactory.createUser(userName, passWord);
		if (user==null||!(passWord.equals(user.getPassWord())))
			return null;
		else
			return user.getUserId();
	}
}
