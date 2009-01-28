package ccp.rtd.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ccp.rtd.entities.PrisonerRelative;

public class PrisonerRelativeDAO extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(PrisonerRelativeDAO.class);
	
	public PrisonerRelative getPrisonerRelative(Integer prisonerRelativeID)
	{
		return (PrisonerRelative)getHibernateTemplate().get(PrisonerRelative.class,prisonerRelativeID);
	}
	
	public List<PrisonerRelative> getPrisonerRelative()
	{
		return getHibernateTemplate().find("FROM PrisonerRelative");
	}
	
	public boolean save(PrisonerRelative prisonerRelative)
	{
		log.debug("saving PrisonerRelative instance");
		try
		{
			getHibernateTemplate().save(prisonerRelative);
			log.debug("save successful PrisonerRelative set to : "+prisonerRelative.getPrisonerRelativeID());
			return true;
		}catch(RuntimeException re)
		{
			log.error("save failed ",re);
			return false;
		}
	}
}
