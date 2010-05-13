package cn.edu.xmu.software.ijoke.dao;

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
}
