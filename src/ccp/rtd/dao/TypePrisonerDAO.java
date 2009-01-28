package ccp.rtd.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ccp.rtd.entities.TypePrisoner;

public class TypePrisonerDAO extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(TypePrisonerDAO.class);
	
	public TypePrisoner getTypePrisoner(Integer typePrisonerID)
	{
		return (TypePrisoner) getHibernateTemplate().get(TypePrisoner.class,typePrisonerID);
	}
	
	public List<TypePrisoner> getTypePrisoners()
	{
		return getHibernateTemplate().find("FROM TypePrisoner");
	}
	
	public boolean save(TypePrisoner typePrisoner)
	{
		log.debug("saving TypePrisoner instance");
		try
		{
			getHibernateTemplate().save(typePrisoner);
			log.debug("save successful typePrisonerID set to : "+typePrisoner.getTypePrisonerID());
			return true;
		}catch(RuntimeException re)
		{
			log.error("save failed",re);
			return false;
		}
	}
	
	
}
