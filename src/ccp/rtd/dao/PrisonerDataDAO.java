package ccp.rtd.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ccp.rtd.entities.PrisonerData;

public class PrisonerDataDAO extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(PrisonerDataDAO.class);

	
	public PrisonerData getPrisonerData(String cardID) {
		return (PrisonerData) getHibernateTemplate().get(PrisonerData.class,cardID);
	}

	
	public List<PrisonerData> getPrisonerDatas() {
		return getHibernateTemplate().find("FROM PrisonerData");
	}

	
	public boolean save(PrisonerData prisonerData) {
		log.debug("saving PrisonerData instance");
		try
		{
			getHibernateTemplate().save(prisonerData);
			log.debug("save successful cardID set to :"+prisonerData.getPrisonerDataID());
			return true;
		}catch(RuntimeException re)
		{
			log.error("save failed",re);
			return false;
		}
	}
	
	public boolean update(PrisonerData prisonerData)
	{
		log.debug("updating PrisonerData instance");
		try
		{
			getHibernateTemplate().update(prisonerData);
			log.debug("update successful cardId set is :"+prisonerData.getPrisonerDataID());
			return true;
		}catch(Exception ex)
		{
			log.error("update failed",ex);
			return false;
		}
	}
	
	public boolean delete(PrisonerData prisonerData)
	{
		log.debug("deleting PrisonerData instance");
		try
		{
			getHibernateTemplate().delete(prisonerData);
			log.debug("delete successful cardID is : "+prisonerData.getPrisonerDataID());
			return true;
		}catch(RuntimeException re)
		{
			log.error("delete failed",re);
			return false;
		}
	}
}
