package cn.edu.xmu.software.ijoke.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.xmu.software.ijoke.entity.ClassItem;
import cn.edu.xmu.software.ijoke.entity.JokeFile;
import cn.edu.xmu.software.ijoke.factory.HibernateSessionFactory;

public class ClassItemDAO extends HibernateDaoSupport{
	private Session session;
	public ClassItemDAO()
	{
		
	}
	public ClassItem findClassItemDAOByClassId(String classId)
	{
		session =  HibernateSessionFactory.getSession();
		ClassItem classItem = (ClassItem)session.createQuery("from ClassItem as classItem where classItem.classId ='" + classId +"'").list().get(0);
		session.close();
		return classItem;
	}
	public ArrayList findAll()
	{
		session =  HibernateSessionFactory.getSession();
		System.out.println("In ClassItemDAO findAll()");
		ArrayList<ClassItem> topicList = (ArrayList)session.createQuery("from ClassItem").list();
		for(int i=0; i<topicList.size(); i++)
		{
			topicList.get(i).setJokeNum(session.createSQLQuery(
					"select jokeId from jl_classandfile where classId='"+
					topicList.get(i).getClassId()+"'").list().size());
		}
		session.close();
		return topicList;
	}
	public ArrayList findByLimit(int begin,int limit )
	{
		session =  HibernateSessionFactory.getSession();
		System.out.println("In ClassItemDAO findEveryTen()");
		
		ArrayList list = (ArrayList)session.createQuery(
				"from ClassItem as classItem order by classItem.classId limit "+begin+","+limit).list();
		session.close();
		return list;
	}
}
