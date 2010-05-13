package cn.edu.xmu.software.ijoke.serviceimpl;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.UserDAO;
import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.factory.UserFactory;
public class RegisterServiceImpl {

	
	public void registerService(String userName, String passWord, String nickName)
	{
		User user = UserFactory.createUser(userName, passWord, nickName);
		UserDAO userDAO = new UserDAO();
		userDAO.insertUser(user);
	}
	@Test
	public void testRegister()
	{
		registerService("ijoker1","123","bill");
	}
}
