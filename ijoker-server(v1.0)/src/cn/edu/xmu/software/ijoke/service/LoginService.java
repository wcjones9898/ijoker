package cn.edu.xmu.software.ijoke.service;

import cn.edu.xmu.software.ijoke.entity.User;

public interface LoginService {

	public String loginService(String userName,String passWord);
	public String adminLoginService(String userName,String passWord);
}
