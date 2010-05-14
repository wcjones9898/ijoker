package cn.edu.xmu.software.ijoke.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.xmu.software.ijoke.entity.Catalog;

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

	protected void initDao() {
		// do nothing
	}

	public void save(Catalog transientInstance) {
		log.debug("saving Catalog instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Catalog persistentInstance) {
		log.debug("deleting Catalog instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Catalog findById(java.lang.Integer id) {
		log.debug("getting Catalog instance with id: " + id);
		try {
			Catalog instance = (Catalog) getHibernateTemplate().get(
					"cn.edu.xmu.software.ijoke.entity.Catalog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Catalog instance) {
		log.debug("finding Catalog instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Catalog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Catalog as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCatalogName(Object catalogName) {
		return findByProperty(CATALOG_NAME, catalogName);
	}

	public List findByParentId(Object parentId) {
		return findByProperty(PARENT_ID, parentId);
	}

	public List findByCatalogId(Object catalogId) {
		return findByProperty(CATALOG_ID, catalogId);
	}

	public List findByCatalogLevel(Object catalogLevel) {
		return findByProperty(CATALOG_LEVEL, catalogLevel);
	}

	public List findAll() {
		log.debug("finding all Catalog instances");
		try {
			String queryString = "from Catalog";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Catalog merge(Catalog detachedInstance) {
		log.debug("merging Catalog instance");
		try {
			Catalog result = (Catalog) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Catalog instance) {
		log.debug("attaching dirty Catalog instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Catalog instance) {
		log.debug("attaching clean Catalog instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CatalogDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CatalogDAO) ctx.getBean("CatalogDAO");
	}
}