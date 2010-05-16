package cn.edu.xmu.software.ijoke.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.Session;
import cn.edu.xmu.software.ijoke.entity.Catalog;
import cn.edu.xmu.software.ijoke.factory.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Catalog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.edu.xmu.software.ijoke.entity.Catalog
 * @author MyEclipse Persistence Tools
 */

public class CatalogDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CatalogDAO.class);
	// property constants
	public static final String CATALOG_NAME = "catalogName";
	public static final String PARENT_ID = "parentId";
	public static final String CATALOG_ID = "catalogId";
	public static final String CATALOG_LEVEL = "catalogLevel";
    private Session session;
	protected void initDao() {
		// do nothing
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
					topicList.get(i).getClassId()+"'").list().size());
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
	

	public static CatalogDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CatalogDAO) ctx.getBean("CatalogDAO");
	}
}