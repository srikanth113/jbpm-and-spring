package ccp.rtd.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ccp.rtd.entities.Health;

public class HealthDAO extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(HealthDAO.class);
	
	public Health getHealth (Integer healthID)
	{
		return (Health)getHibernateTemplate().get(HealthDAO.class,healthID);
	}
	
	public List<Health> getHealths()
	{
		return getHibernateTemplate().find("FROM Health");
	}
	
	public boolean save(Health health)
	{
		log.debug("saving Health instance");
		try
		{
			getHibernateTemplate().saveOrUpdate(health);
			log.debug("save successful healthID set to : "+health.getHealthID());
			return true;
		}
		catch(RuntimeException re)
		{
			log.error("save failed ",re);
			return false;
		}
	}
}
