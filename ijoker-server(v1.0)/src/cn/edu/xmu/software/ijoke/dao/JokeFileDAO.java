package cn.edu.xmu.software.ijoke.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.xmu.software.ijoke.entity.Joke;
import cn.edu.xmu.software.ijoke.entity.JokeFile;
import cn.edu.xmu.software.ijoke.entity.User;
import cn.edu.xmu.software.ijoke.factory.HibernateSessionFactory;

public class JokeFileDAO extends HibernateDaoSupport{
	private Session session;
	public JokeFileDAO()
	{
		
	}
	public JokeFile findJokeFileByFileId(String fileId)
	{
		session =  HibernateSessionFactory.getSession();
		
		List<JokeFile> jokeFileList = session.createQuery("from JokeFile as jokeFile where jokeFile.fileId='"+ fileId+"'").list();
		session.close();
		JokeFile jokeFile = null;
		if(jokeFileList.size()>0)
			jokeFile = jokeFileList.get(0);
		return jokeFile;
	}
	public void insertJokeFile(JokeFile jokeFile)
	{
		session =  HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		//session.merge(joke);
		session.save(jokeFile);
		tx.commit(); 
		session.close();
	}
	public void deleteJokeFile(String jokeFileId)
	{
		JokeFile jokeFile = this.findJokeFileByFileId(jokeFileId);
		if(jokeFile!=null){

		session =  HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		getHibernateTemplate().delete(jokeFile);
		tx.commit();
		session.close();
		}
	}
	public static JokeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (JokeDAO) ctx.getBean("JokeDAO");
	}
}
