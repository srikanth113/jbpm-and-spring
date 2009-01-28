package ccp.rtd.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ccp.rtd.entities.TypeRelative;

public class TypeRelativeDAO extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(TypeRelativeDAO.class);
	
	public TypeRelative getTypeRelative(Integer typeRelativeID)
	{
		return (TypeRelative) getHibernateTemplate().get(TypeRelative.class,typeRelativeID);
	}
	
	public List<TypeRelative> getTypeRelatives()
	{
		return getHibernateTemplate().find("FROM TypeRelative");
	}
	
	public boolean save(TypeRelative typeRelative)
	{
		log.debug("saving TypeRelative instance");
		try
		{
			getHibernateTemplate().save(typeRelative);
			log.debug("save successful typeRelativeID set to : "+typeRelative.getTypeReletiveID());
			return true;
		}catch(RuntimeException re)
		{
			log.error("save failed",re);
			return false;
		}
	}
}
