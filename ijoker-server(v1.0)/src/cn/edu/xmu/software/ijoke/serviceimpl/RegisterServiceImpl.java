package cn.edu.xmu.software.ijoke.serviceimpl;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.UserDAO;
import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.factory.UserFactory;
import cn.edu.xmu.software.ijoke.service.RegisterService;
import cn.edu.xmu.software.ijoke.utils.Consts;
public class RegisterServiceImpl implements RegisterService{

	
	public String registerService(String userName, String passWord, String nickName)
	{
		User user = UserFactory.createUser(userName, passWord, nickName);
		try{
		UserDAO userDAO = new UserDAO();
		if(userDAO.findByUserName(userName)!=null)
			return Consts.REGISTER_USER_EXISTS;
		userDAO.insertUser(user);
		return user.getUserId();
		}catch(Exception e)
		{
			return Consts.REGISTER_FAIL;
		}
	}
	@Test
	public void testRegister()
	{
		System.out.println(registerService("ijoker4","123","bill"));
	}
}
