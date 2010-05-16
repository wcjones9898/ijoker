package cn.edu.xmu.software.ijoke.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.xmu.software.ijoke.entity.Catalog;
import cn.edu.xmu.software.ijoke.factory.HibernateSessionFactory;

public class ClassItemDAO extends HibernateDaoSupport{
	private Session session;
	public ClassItemDAO()
	{
		
	}
	public Catalog findClassItemByClassId(String classId)
	{
		session =  HibernateSessionFactory.getSession();
		List classItemList = session.createQuery("from Catalog as classItem where classItem.classId ='" + classId +"'").list();
		session.close();
		
		Catalog classItem = null;
		if(classItemList.size()>0)
			classItem = (Catalog) classItemList.get(0);
		return classItem;
	}
	public ArrayList findAll()
	{
		session =  HibernateSessionFactory.getSession();
		System.out.println("In ClassItemDAO findAll()");
		ArrayList<Catalog> topicList = (ArrayList)session.createQuery("from Catalog").list();
		for(int i=0; i<topicList.size(); i++)
		{
			topicList.get(i).setJokeNum(session.createSQLQuery(
					"select jokeId from jl_classandfile where classId='"+
					topicList.get(i).getCatalogId()+"'").list().size());
		}
		session.close();
		return topicList;
	}
	public ArrayList findByLimit(int begin,int limit )
	{
		session =  HibernateSessionFactory.getSession();
		System.out.println("In Catalog findEveryTen()");
		
		ArrayList list = (ArrayList)session.createQuery(
				"from Catalog as catalog order by catalog.classId limit "+begin+","+limit).list();
		session.close();
		return list;
	}
	public void save(Catalog catalog) {
		
		try {
			getHibernateTemplate().save(catalog);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	public void delete(Catalog catalog) {
		
		try {
			getHibernateTemplate().delete(catalog);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	public void update(Catalog catalog) {
		
		session =  HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		session.merge(catalog);
		tx.commit();  
		session.close();
	}
}
