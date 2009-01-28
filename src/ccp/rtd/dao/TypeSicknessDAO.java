package ccp.rtd.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ccp.rtd.entities.TypeSickness;

public class TypeSicknessDAO extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(TypeSicknessDAO.class);
	
	public TypeSickness getTypeSickness(Integer typeSicknessID)
	{
		return (TypeSickness) getHibernateTemplate().get(TypeSickness.class,typeSicknessID);
	}
	
	public List<TypeSickness> getTypeSicknesses()
	{
		return getHibernateTemplate().find("FROM TypeSickness");
	}
	
	public boolean save(TypeSickness typeSickness)
	{
		log.debug("saving TypeSickness instance");
		try
		{
			getHibernateTemplate().saveOrUpdate(typeSickness);
			log.debug("save successful typeSicknessID set to :"+typeSickness.getTypeSicknessID());
			return true;
		}
		catch(RuntimeException re)
		{
			log.error("save failed ",re);
			return false;
		}
	}
}
