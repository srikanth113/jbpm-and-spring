package ccp.rtd.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ccp.rtd.entities.PrisonerHasType;

public class PrisonerHasTypeDAO  extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(PrisonerHasTypeDAO.class);
	
	public PrisonerHasType getPrisonerHasType(Integer prisonerHasTypeID) {
		return (PrisonerHasType) getHibernateTemplate().get(PrisonerHasType.class,prisonerHasTypeID);
	}

	
	public List<PrisonerHasType> getPrisonerHasTypes() {
		return getHibernateTemplate().find("FROM PrisonerHasType");
	}

	
	public boolean save(PrisonerHasType prisonerHasType) {
		log.debug("saveing PrisonerHasType instance");
		try
		{
			getHibernateTemplate().save(prisonerHasType);
			log.debug("save successful PrisonerHasTypeId set to: "+prisonerHasType.getPrisonerHasTypeID());
			return true;
		}catch(RuntimeException re)
		{
			log.error("save failed",re);
			return false;
		}
	}

	
	public boolean delete(PrisonerHasType prisonerHasType) {
		log.debug("deleting Prisoner instance");
		try
		{
			getHibernateTemplate().delete(prisonerHasType);
			log.debug("delete successful PrisonerHasTypeId is :"+prisonerHasType.getPrisonerHasTypeID());
			return true;
		}catch (RuntimeException re)
		{
			log.error("delete failed",re);
			return false;
		}
	}

}
