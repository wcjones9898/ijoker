package cn.edu.xmu.software.ijoke.dao;
// default package


import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for User entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .User
  * @author MyEclipse Persistence Tools 
 */

public class UserDAO extends HibernateDaoSupport  {
   

	private Session session;
	public UserDAO()
	{
		System.out.println("建立UserDAO");
	}


	
    public void testConnect()
    {
		//ArrayList userList = (ArrayList) sessionFactory.getSession().createQuery("select passWord from User").list();
		//System.out.print(userList.get(0));
    }
    public User findByUserName(String userName)
    {
    	session =  HibernateSessionFactory.getSession();
    	User user = null;
    	try{
    	  user = (User)session.createQuery("from User as user where user.userName ='"+ userName+"'").list().get(0);
    	}catch(Exception e)
    	{
    		System.out.println("该用户不存在");
    	}
    	session.close();
    	return  user;
    }
    public User findByUserId(String userId)
    {
    	session =  HibernateSessionFactory.getSession();
    	System.out.println("from User as user where user.userId='"+ userId+"'");
    	User user =  (User)session.createQuery("from User as user where user.userId='"+ userId+"'").list().get(0);
    	session.close();
    	return  user;    
    }
    
    public void insertUser(User user)
    {
    	session =  HibernateSessionFactory.getSession();
    	Transaction tx=session.beginTransaction();
		session.save(user);
		tx.commit();  
		session.close();
    	
    }
    @Test 
    public void testInsertUser()
    {
    	User user = new User();
    	user.setNickName("asdf");
    	user.setPassWord("adf");
    	user.setUserId("adf");
    	user.setUserName("adfad");
    	 insertUser(user);
    }
  }