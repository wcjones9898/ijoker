package cn.edu.xmu.software.ijoke.dao;
// default package


import cn.edu.xmu.software.ijoke.entity.Joke;
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
    	List userList =  session.createQuery("from User as user where user.userId='"+ userId+"'").list();
    	session.close();
    	User user = null;
    	if(userList.size()>0)
    		user = (User) userList.get(0);
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
    
    public List<User> findByStatus(int status, int begin,int pageSize)
    {
		session =  HibernateSessionFactory.getSession();
		List users =session.createQuery("from User as user where user.status ="
				+ status ).setFirstResult(begin).setMaxResults(begin+pageSize).list();
		
		session.close();
		return users;
	
    }
    
	public void updateUserByUserId(String userId, int status)
	{
		User user = this.findByUserId(userId);
		user.setStatus(status);
		session =  HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		session.merge(user);
		tx.commit();  
		session.close();
	}
	public void updateUser(User user)
	{
		session =  HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		session.merge(user);
		tx.commit();  
		session.close();
	}
//    @Test 
//    public void testInsertUser()
//    {
//    	User user = new User();
//    	user.setNickName("asdf");
//    	user.setPassWord("adf");
//    	user.setUserId("adf");
//    	user.setUserName("adfad");
//    	 insertUser(user);
//    }
	 @Test
	 public void testFindByUserName()
	 {
		 System.out.println(findByUserName("ijoker").getNickName());
	 }
  }