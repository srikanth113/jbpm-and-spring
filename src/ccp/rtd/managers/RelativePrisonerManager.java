package ccp.rtd.managers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import ccp.rtd.dao.PrisonerDataDAO;
import ccp.rtd.dao.PrisonerRelativeDAO;
import ccp.rtd.dao.TypeRelativeDAO;
import ccp.rtd.entities.PrisonerData;
import ccp.rtd.entities.PrisonerRelative;
import ccp.rtd.entities.TypeRelative;
import ccp.rtd.jbpm.dao.ProcessJbpmDAO;
import ccp.rtd.utilities.HqSpringContext;


public class RelativePrisonerManager {
	private static final Log log = LogFactory.getLog(RelativePrisonerManager.class);
	private ApplicationContext context = HqSpringContext.getApplicationContext();
	private PrisonerRelativeDAO prisonerRelativeDao = (PrisonerRelativeDAO)context.getBean("prisonerRelativeDAO");
	private TypeRelativeDAO typeRelativeDao =(TypeRelativeDAO) context.getBean("typeRelativeDAO");
	private PrisonerDataDAO prisonerDataDao =(PrisonerDataDAO) context.getBean("prisonerDataDAO");
	private ProcessJbpmDAO processJbpmDao = (ProcessJbpmDAO) context.getBean("processJbpmDAO");
	
	public PrisonerData getPrisonerData(String cardID)
	{
		return prisonerDataDao.getPrisonerData(cardID);
	}
	
	public List<TypeRelative> getTypeRelatives()
	{
		return typeRelativeDao.getTypeRelatives();
	}
	
	public boolean savePrisonerRelative(String cardID,Integer typeRelativeID,String name,String surname)
	{
		try
		{
			PrisonerRelative prisonerRelative = new PrisonerRelative(name,surname,"",typeRelativeDao.getTypeRelative(typeRelativeID)
					,prisonerDataDao.getPrisonerData(cardID));
			if(prisonerRelativeDao.save(prisonerRelative))
			{
				log.debug("saving prisonerRelative");
				return true;
			}else
			{
				log.debug("save prisonerRelative failed");
				return false;
			}
		}
		catch(Exception e)
		{
			log.error("ERROR : ",e);
			return false;
		}
	}
	
	public void signalProcessInstance(final long processInstanceId)
	{
		processJbpmDao.signalProcessInstance(processInstanceId);
	}

}
