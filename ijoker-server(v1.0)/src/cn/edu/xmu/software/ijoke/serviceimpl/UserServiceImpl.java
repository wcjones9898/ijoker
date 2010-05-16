package cn.edu.xmu.software.ijoke.serviceimpl;

import java.util.List;

import org.junit.Test;

import cn.edu.xmu.software.ijoke.dao.UserDAO;
import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.service.UserService;
import cn.edu.xmu.software.ijoke.factory.AppFactory;
import cn.edu.xmu.software.ijoke.utils.Consts;
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

	public boolean verify(String userId, int status) {
		// TODO Auto-generated method stub
		if(userDAO.findByUserId(userId)==null)
			return false;
		userDAO.updateUserByUserId(userId, status);
		return true;
	}
//	@Test
//	public void testUserWithOutVerify()
//	{
//		List<User> userList = AppFactory.getUserService().getUserWithOutVerify(0,5);
//		for(int i=0; i<userList.size(); i++)
//			System.out.println(userList.get(i).getNickName());
//	}
	@Test
	public void testUserVerified()
	{
		List<User> userList = AppFactory.getUserService().getUserVerified(0,5);
		for(int i=0; i<userList.size(); i++)
			System.out.println(userList.get(i).getNickName());
	}
	@Test
	public void testVerify()
	{
		System.out.println(AppFactory.getUserService().verify("20100512205839088", 1));
	}

}
