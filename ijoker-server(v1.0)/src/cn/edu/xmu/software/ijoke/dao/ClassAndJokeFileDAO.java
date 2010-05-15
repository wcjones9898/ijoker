package cn.edu.xmu.software.ijoke.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.xmu.software.ijoke.entity.ClassAndJokeFile;
import cn.edu.xmu.software.ijoke.factory.HibernateSessionFactory;

public class ClassAndJokeFileDAO extends HibernateDaoSupport{
	private Session session;
	public ClassAndJokeFileDAO()
	{
	
		System.out.println("In ClassAndJokeFileDAO");

	}
	public List<ClassAndJokeFile> findClassItemDAOByClassId(String classId)
	{
		session =  HibernateSessionFactory.getSession();
		List<ClassAndJokeFile> classAndJokeFileList = (List<ClassAndJokeFile>)session.createQuery(
				"from ClassAndJokeFile as classAndJokeFile where classAndJokeFile.classId ='"
				+ classId +"'").list();
		session.close();
		return classAndJokeFileList;
		
	}
	public List<ClassAndJokeFile> findClassAndJokeByLimit(String classId,int begin,int limit)
	{
		session =  HibernateSessionFactory.getSession();
		System.out.println("SELECT  * from jl_classandfile where classId="
				+classId+" limit "+begin+","+limit);
		List<ClassAndJokeFile> classAndJokeFileList = (List<ClassAndJokeFile>)session.createQuery("from ClassAndJokeFile as classAndJokeFile where classAndJokeFile.classId ='"
				+ classId +"'").setFirstResult(begin).setMaxResults(limit+begin).list();
		session.close();
		return classAndJokeFileList;

	}
	public void insertClassAndJokeFile(ClassAndJokeFile classAndJokeFile)
	{
		session =  HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		//session.merge(joke);
		session.save(classAndJokeFile);
		tx.commit(); 
		session.close();
	}
	
	public void updateClassAndJoke(ClassAndJokeFile classAndJokeFile)
	{
		session =  HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		//session.merge(joke);
		session.merge(classAndJokeFile);
		tx.commit(); 
		session.close();
	}
	
	public ClassAndJokeFile findClassAndJoke(String classId, String jokeId)
	{
		session =  HibernateSessionFactory.getSession();
		ClassAndJokeFile classAndJokeFile = null;
		List classAndJokeList = session.createQuery(
				"from ClassAndJokeFile as classAndJokeFile where " +
				"classAndJokeFile.classId='"+classId+"'"
				+"and classAndJokeFile.jokeId='"+jokeId+"'").list();
		if(classAndJokeList.size()!=0)
			classAndJokeFile = (ClassAndJokeFile) classAndJokeList.get(0);
		
		session.close();
		return classAndJokeFile;
	}
	
	public void deleteClassAndJoke(String classId, String jokeId)
	{
		ClassAndJokeFile classAndJoke =this.findClassAndJoke(classId, jokeId);
		session =  HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();

		session.delete(classAndJoke);
		tx.commit();
		session.close();
		
	
	}
	
	public void addClassAndJoke(String classId, String jokeId)
	{
		ClassAndJokeFile classAndJoke =this.findClassAndJoke(classId, jokeId);
		if(classAndJoke==null)
		{
			classAndJoke = new ClassAndJokeFile();
			classAndJoke.setClassId(classId);
			classAndJoke.setJokeId(jokeId);
		    session =  HibernateSessionFactory.getSession();
		    Transaction tx=session.beginTransaction();
		    session.save(classAndJoke);
		    tx.commit();
		    session.close();
		}
		
	
	}
}
