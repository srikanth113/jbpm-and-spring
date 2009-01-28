package ccp.rtd.managers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.graph.exe.ProcessInstance;
import org.springframework.context.ApplicationContext;

import ccp.rtd.dao.PrisonerDAO;
import ccp.rtd.dao.PrisonerDataDAO;
import ccp.rtd.entities.Prisoner;
import ccp.rtd.entities.PrisonerData;
import ccp.rtd.jbpm.dao.ProcessJbpmDAO;
import ccp.rtd.utilities.HqSpringContext;

public class ReceivePrisonerManager {
	private static final Log log = LogFactory.getLog(ReceivePrisonerManager.class);
	private ApplicationContext context = HqSpringContext.getApplicationContext();
	private PrisonerDataDAO prisonerDataDao = (PrisonerDataDAO) context.getBean("prisonerDataDAO");
	private PrisonerDAO prisonerDao = (PrisonerDAO) context.getBean("prisonerDAO");
	
	public boolean saveReceivePrisoner(String cardId,String courtFName,String courtLName,String licenseFName,String licenseLName,String isHasType)
	{
		PrisonerData prisonerData = new PrisonerData(cardId,licenseFName,licenseLName);
		if(prisonerDataDao.save(prisonerData))
		{
			log.debug("save prisonerData sucessful");
			if(prisonerDao.save(new Prisoner(courtFName,courtLName,prisonerData)))
			{
				log.debug("save prisoner successful cardId : "+cardId);
			}
			else
			{
				log.debug("couldn't save prisoner table");
			}
			return true;
		}
		else
		{
			log.error("couldn't save prisonerData table");
			return false;
		}
	}
	
	public PrisonerData getPrisonerData (String cardId)
	{
		return prisonerDataDao.getPrisonerData(cardId);
	}
	
	
}
