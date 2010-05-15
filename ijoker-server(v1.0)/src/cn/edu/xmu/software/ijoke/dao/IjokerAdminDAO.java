package cn.edu.xmu.software.ijoke.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.xmu.software.ijoke.entity.BaseHibernateDAO;
import cn.edu.xmu.software.ijoke.entity.IjokerAdmin;

/**
 * A data access object (DAO) providing persistence and search support for
 * IjokerAdmin entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.edu.xmu.software.ijoke.entity.IjokerAdmin
 * @author MyEclipse Persistence Tools
 */

public class IjokerAdminDAO extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(IjokerAdminDAO.class);
	// property constants
	public static final String ADMIN_NAME = "adminName";
	public static final String PASS_WORD = "passWord";
	public static final String AUTHORITY = "authority";

	public void save(IjokerAdmin transientInstance) {
		log.debug("saving IjokerAdmin instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IjokerAdmin persistentInstance) {
		log.debug("deleting IjokerAdmin instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IjokerAdmin findById(java.lang.Integer id) {
		log.debug("getting IjokerAdmin instance with id: " + id);
		try {
			IjokerAdmin instance = (IjokerAdmin) getSession().get(
					"cn.edu.xmu.software.ijoke.entity.IjokerAdmin", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IjokerAdmin instance) {
		log.debug("finding IjokerAdmin instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.edu.xmu.software.ijoke.entity.IjokerAdmin").add(
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
		log.debug("finding IjokerAdmin instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from IjokerAdmin as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAdminName(Object adminName) {
		return findByProperty(ADMIN_NAME, adminName);
	}

	public List findByPassWord(Object passWord) {
		return findByProperty(PASS_WORD, passWord);
	}

	public List findByAuthority(Object authority) {
		return findByProperty(AUTHORITY, authority);
	}

	public List findAll() {
		log.debug("finding all IjokerAdmin instances");
		try {
			String queryString = "from IjokerAdmin";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IjokerAdmin merge(IjokerAdmin detachedInstance) {
		log.debug("merging IjokerAdmin instance");
		try {
			IjokerAdmin result = (IjokerAdmin) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IjokerAdmin instance) {
		log.debug("attaching dirty IjokerAdmin instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IjokerAdmin instance) {
		log.debug("attaching clean IjokerAdmin instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}