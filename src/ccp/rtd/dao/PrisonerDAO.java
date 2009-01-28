package ccp.rtd.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ccp.rtd.entities.Prisoner;

public class PrisonerDAO extends HibernateDaoSupport {
	
	private static final Log log = LogFactory.getLog(PrisonerDAO.class);
	
	public Prisoner getPrisoner (Integer prisonerID)
	{
		return (Prisoner) getHibernateTemplate().get(Prisoner.class,prisonerID);
	}
	
	public List<Prisoner> getPrisoners() {
		return getHibernateTemplate().find("FROM Prisoner");
	}

	
	public boolean save(Prisoner prisoner) {
		log.debug("saving Prisoner instance");
		try
		{
			getHibernateTemplate().saveOrUpdate(prisoner);
			log.debug("save successful prisonerId set to: "+prisoner.getPrisonerID());
			return true;
		}catch(RuntimeException re)
		{
			log.error("save failed",re);
			return false;
		}
	}

	public boolean delete(Prisoner prisoner) {
		log.debug("deleting Prisoner instance");
		try
		{
			getHibernateTemplate().delete(prisoner);
			log.debug("delete successful prisonerId is :"+prisoner.getPrisonerID());
			return true;
		}catch (RuntimeException re)
		{
			log.error("delete failed",re);
			return false;
		}
	}
}

