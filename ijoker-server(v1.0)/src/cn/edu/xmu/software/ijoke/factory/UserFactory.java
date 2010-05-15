package cn.edu.xmu.software.ijoke.factory;

import java.sql.Timestamp;
import java.util.Date;

import cn.edu.xmu.software.ijoke.dao.UserDAO;
import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.exception.NoUserException;

public class UserFactory {

	public static  User createUser(String userName, String passWord)
	{
		UserDAO userDAO = new UserDAO();
		User user =  userDAO.findByUserName(userName);
		
		return user;
	}
	
	public static User createUser(String userName, String passWord,String nickName)
	{
		User user = new User();
		user.setUserName(userName);
		user.setNickName(nickName);
        user.setPassWord(passWord);
        user.setUserId(createUserId());
		return user;
	}
	private static String createUserId()
	{
		String userId = null;
		Timestamp date = new Timestamp((new Date().getTime()));
		userId = date.toString().replace(" ","");
		userId = userId.replace("-","");
		userId = userId.replace(":","");
		userId = userId.replace(".","");
	    return userId;
	}
}
