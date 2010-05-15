package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.List;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.UserDAO;
import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.service.UserService;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
public class UserServiceImpl implements UserService{

	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public List<User> getUserVerified(int begin, int pageSize) {
		// TODO Auto-generated method stub
		
		return userDAO.findByStatus(1, begin, pageSize);
	}

	public List<User> getUserWithOutVerify(int begin, int pageSize) {
		// TODO Auto-generated method stub
		return userDAO.findByStatus(0, begin, pageSize);
	}

	public String verify(String userId, int status) {
		// TODO Auto-generated method stub
		userDAO.updateUserByUserId(userId, status);
		return "verify success";
	}
	@Test
	public void testVerify()
	{
		AppFactory.getUserService().verify("20100512205839082", 1);
	}

}
