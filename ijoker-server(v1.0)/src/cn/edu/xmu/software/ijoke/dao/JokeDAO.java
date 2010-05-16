package cn.edu.xmu.software.ijoke.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.xmu.software.ijoke.entity.Joke;
import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.factory.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for Joke
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.edu.xmu.software.ijoke.entity.Joke
 * @author MyEclipse Persistence Tools
 */

public class JokeDAO extends HibernateDaoSupport {
	private Session session;

	public JokeDAO()
	{
		
	}
	public Joke findByJokeId(String jokeId)
	{
		session =  HibernateSessionFactory.getSession();
		Joke joke = null;
		List<Joke> jokeList = session.createQuery("from Joke as joke where joke.jokeId ='"
				+ jokeId +"'").list();
		if(jokeList.size()>0)
			joke = jokeList.get(0);
		session.close();
		return joke;
		
	}
	
	public List findByTitle(String title)
	{
		session =  HibernateSessionFactory.getSession();
		List jokes =session.createQuery("from Joke as joke where joke.title like '%"
				+ title +"%' order by joke.likeNum desc").list();
		session.close();
		return jokes;
		
	}
	
	public List findByAuthor(String author)
	{
		session =  HibernateSessionFactory.getSession();
		List jokes =session.createQuery("from Joke as joke where joke.authorName like '%"
				+ author +"%' order by joke.likeNum desc").list();
		session.close();
		return jokes;
		
	}
	public List findByAllInfo(String info)
	{
		session =  HibernateSessionFactory.getSession();
		List jokes =session.createQuery("from Joke as joke where joke.title like '%"
				+ info +"%'  OR joke.description like '%"
				+ info +"%'  order by joke.likeNum desc").list();
		session.close();
		return jokes;
		
	}
    public List<String> findByAllInfoToJokeId(String info)
    {

    	session =  HibernateSessionFactory.getSession();
		List jokes =session.createQuery("Select joke.jokeId from Joke as joke where joke.title like '%"
				+ info +"%'  OR joke.description like '%"
				+ info +"%'  order by joke.likeNum desc").list();
		session.close();
		return jokes;
    }
    public List<String> findByTitleToJokeId(String title)
    {
    	List<String> jokeIdList;
    	session =  HibernateSessionFactory.getSession();
    	jokeIdList =session.createQuery("Select joke.jokeId from Joke as joke where joke.title like '%"
				+ title +"%' order by joke.likeNum desc").list();
		session.close();
		
    	return jokeIdList;
    }
    
    public List<String> findByAuthorToJokeId(String author)
    {
		session =  HibernateSessionFactory.getSession();
		List jokes =session.createQuery("select joke.jokeId from Joke as joke where joke.authorName like '%"
				+ author +"%' order by joke.likeNum desc").list();
		session.close();
		return jokes;
    }
	public void updateJokeByJokeId(String jokeId)
	{
		Joke joke = this.findByJokeId(jokeId);
		joke.setLikeNum(joke.getLikeNum()+1);
		session =  HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		session.merge(joke);
		tx.commit();  
		session.close();
	}
	public void insertJoke(Joke joke)
	{
		session =  HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		//session.merge(joke);
		session.save(joke);
		tx.commit();  
		session.close();
	}
	public User findAuthorByJokeId(String jokeId)
	{
		session =  HibernateSessionFactory.getSession();
		Joke joke = findByJokeId(jokeId);
		User user = (User)session.createQuery("from User as user where user.userId='"
				+ joke.getUploaderId()+"'").list().get(0);
		session.close();
		return user;
	}
	
	
	public void updateJoke(Joke joke)
	{
		session =  HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		session.merge(joke);
		tx.commit();  
		session.close();
	}
	
	public void deleteJoke(String jokeId)
	{
		Joke joke = this.findByJokeId(jokeId);
		Transaction tx=session.beginTransaction();
		session =  HibernateSessionFactory.getSession();
		getHibernateTemplate().delete(joke);
		tx.commit();  
		session.close();
	}
	
	public ArrayList<Joke> findAllByLimit10(int begin)
	{
		session =  HibernateSessionFactory.getSession();
		ArrayList<Joke> jokeList = (ArrayList<Joke>) session.createQuery("from Joke")
		.setFirstResult(begin).setMaxResults(begin+10).list();
		session.close();
		return jokeList;
	}
    public int getJokesNum()
    {
		session =  HibernateSessionFactory.getSession();
		Long i = (Long)session.createQuery("select count(*) from Joke as joke").list().get(0);
		session.close();
		return i.intValue();
    }

    public ArrayList<Joke> findByStatus(String status, int begin,int pageSize)
    {
		session =  HibernateSessionFactory.getSession();
		List jokes =session.createQuery("from Joke as joke where joke.status ='"
				+ status +"'").setFirstResult(begin).setMaxResults(begin+pageSize).list();
		
		session.close();
		return (ArrayList<Joke>) jokes;
	
    }
	public static JokeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (JokeDAO) ctx.getBean("JokeDAO");
	}
	
	@Test
	public void testFindByTitleToJokeId()
	{
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
	    JokeDAO jokeDAO = (JokeDAO)app.getBean("JokeDAO");
	    List<String> idList = jokeDAO.findByAllInfoToJokeId("a");
	    for(int i=0;i<idList.size();i++)
	    	System.out.println(idList.get(i));
	}
//	@Test
//	public void testUpdateJoke()
//	{
//		Joke joke = this.findByJokeId("4");
//		joke.setLikeNum(joke.getLikeNum()+1);
//		updateJoke(joke);
//	}
	
	@Test
	public void testDelete()
	{
//		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
//	    JokeDAO jokeDAO = (JokeDAO)app.getBean("JokeDAO");
//	    jokeDAO.deleteJoke("20100513095238256");
	    
	}
//	@Test
//	public void testFindAll()
//	{
//		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
//	    JokeDAO jokeDAO = (JokeDAO)app.getBean("JokeDAO");
//	    ArrayList<Joke> jokeList = jokeDAO.findAllByLimit10(0);
//	    for(int i=0;i<jokeList.size();i++)
//	    	System.out.println(jokeList.get(i).getAuthorName());
//	}
//	@Test
//	public void testGetJokeNum()
//	{
//		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
//	    JokeDAO jokeDAO = (JokeDAO)app.getBean("JokeDAO");
//	    System.out.println(jokeDAO.getJokesNum());
//	}
	

}