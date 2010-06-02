package cn.edu.xmu.software.ijoke.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import cn.edu.xmu.software.ijoke.entity.CartoonFile;

/**
 * A data access object (DAO) providing persistence and search support for
 * CartoonFile entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.edu.xmu.software.ijoke.entity.CartoonFile
 * @author MyEclipse Persistence Tools
 */

public class CartoonFileDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CartoonFileDAO.class);
	// property constants
	public static final String FILE_ID = "fileId";
	public static final String FILE_NAME = "fileName";
	public static final String FILE_EXTENSION = "fileExtension";
	public static final String FILE_PATH = "filePath";
	public static final String FILE_LENGTH = "fileLength";

	public void save(CartoonFile transientInstance){
		log.debug("saving CartoonFile instance");
		try {
			

			Transaction tx= getSession().beginTransaction();
			getHibernateTemplate().save(transientInstance);
			tx.commit();
		
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CartoonFile persistentInstance) {
		log.debug("deleting CartoonFile instance");
		try {
			
			Transaction tx= getSession().beginTransaction();
			getHibernateTemplate().delete(persistentInstance);
			tx.commit();

			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CartoonFile findById(java.lang.Integer id) {
		log.debug("getting CartoonFile instance with id: " + id);
		try {
			CartoonFile instance = (CartoonFile) getSession().get(
					"cn.edu.xmu.software.ijoke.entity.CartoonFile", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CartoonFile instance) {
		log.debug("finding CartoonFile instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.edu.xmu.software.ijoke.entity.CartoonFile").add(
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
		log.debug("finding CartoonFile instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CartoonFile as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFileId(Object fileId) {
		return findByProperty(FILE_ID, fileId);
	}

	public List findByFileName(Object fileName) {
		return findByProperty(FILE_NAME, fileName);
	}

	public List findByFileExtension(Object fileExtension) {
		return findByProperty(FILE_EXTENSION, fileExtension);
	}

	public List findByFilePath(Object filePath) {
		return findByProperty(FILE_PATH, filePath);
	}

	public List findByFileLength(Object fileLength) {
		return findByProperty(FILE_LENGTH, fileLength);
	}

	public List findAll() {
		log.debug("finding all CartoonFile instances");
		try {
			String queryString = "from CartoonFile";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CartoonFile merge(CartoonFile detachedInstance) {
		log.debug("merging CartoonFile instance");
		try {
			CartoonFile result = (CartoonFile) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CartoonFile instance) {
		log.debug("attaching dirty CartoonFile instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CartoonFile instance) {
		log.debug("attaching clean CartoonFile instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}