package cn.edu.xmu.software.ijoke.service;
import cn.edu.xmu.software.ijoke.entity.IjokerAdmin;
public interface AdminLoginService {

	public IjokerAdmin adminLogin(String userName, String passWord);
	public String login(String userName ,String passWord);
}
