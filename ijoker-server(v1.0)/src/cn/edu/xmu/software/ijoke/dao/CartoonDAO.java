package cn.edu.xmu.software.ijoke.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.xmu.software.ijoke.entity.Cartoon;
import cn.edu.xmu.software.ijoke.entity.Catalog;
import cn.edu.xmu.software.ijoke.factory.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Cartoon entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.edu.xmu.software.ijoke.entity.Cartoon
 * @author MyEclipse Persistence Tools
 */

public class CartoonDAO extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(CartoonDAO.class);
	// property constants
	public static final String CARTOON_ID = "cartoonId";
	public static final String FILE_ID = "fileId";
	public static final String CARTOON_TITLE = "cartoonTitle";
	public static final String UPLOAD_TIME = "uploadTime";
	public static final String AUTHOR_NAME = "authorName";
	public static final String UPLOADER_ID = "uploaderId";
	public static final String STATUS = "status";

	

	public void delete(Cartoon persistentInstance) {
		log.debug("deleting Cartoon instance");
		try {
			Transaction tx=getSession().beginTransaction();
			getHibernateTemplate().delete(persistentInstance);
			tx.commit();  
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Cartoon findById(java.lang.Integer id) {
		log.debug("getting Cartoon instance with id: " + id);
		try {
			Cartoon instance = (Cartoon) getSession().get(
					"cn.edu.xmu.software.ijoke.entity.Cartoon", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Cartoon instance) {
		log.debug("finding Cartoon instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.edu.xmu.software.ijoke.entity.Cartoon").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Cartoon instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Cartoon as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCartoonId(Object cartoonId) {
		return findByProperty(CARTOON_ID, cartoonId);
	}

	public List findByFileId(Object fileId) {
		return findByProperty(FILE_ID, fileId);
	}

	public List findByCartoonTitle(Object cartoonTitle) {
		return findByProperty(CARTOON_TITLE, cartoonTitle);
	}

	public List findByUploadTime(Object uploadTime) {
		return findByProperty(UPLOAD_TIME, uploadTime);
	}

	public List findByAuthorName(Object authorName) {
		return findByProperty(AUTHOR_NAME, authorName);
	}

	public List findByUploaderId(Object uploaderId) {
		return findByProperty(UPLOADER_ID, uploaderId);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all Cartoon instances");
		try {
			String queryString = "from Cartoon";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Cartoon merge(Cartoon detachedInstance) {
		log.debug("merging Cartoon instance");
		try {
			Cartoon result = (Cartoon) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Cartoon instance) {
		log.debug("attaching dirty Cartoon instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Cartoon instance) {
		log.debug("attaching clean Cartoon instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
    public List findByStatus(int status,int begin,int pageSize)
    {

		List cartoonList =   getSession().createQuery("from Cartoon as model where model.status="+status)
		.setFirstResult(begin).setMaxResults(begin+pageSize).list();
	    
		return cartoonList;
    	
    }
    public List findAll(int begin,int pageSize)
    {

		List cartoonList =   getSession().createQuery("from Cartoon ")
		.setFirstResult(begin).setMaxResults(begin+pageSize).list();
	    
		return cartoonList;
    	
    }
	public void save(Cartoon cartoon) {
		
		try {

			Transaction tx=getSession().beginTransaction();
			getHibernateTemplate().save(cartoon);
			tx.commit();  

			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	public void update(Cartoon cartoon) {
		
		try {

			Transaction tx=getSession().beginTransaction();
			getHibernateTemplate().update(cartoon);
			tx.commit();  

			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
}