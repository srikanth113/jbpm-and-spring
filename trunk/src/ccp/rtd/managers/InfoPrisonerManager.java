package ccp.rtd.managers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import ccp.rtd.dao.PrisonerDataDAO;
import ccp.rtd.entities.PrisonerData;
import ccp.rtd.utilities.HqSpringContext;

public class InfoPrisonerManager {
	private static final Log log = LogFactory.getLog(InfoPrisonerManager.class);
	private ApplicationContext context = HqSpringContext.getApplicationContext();
	private PrisonerDataDAO prisonerDataDao = (PrisonerDataDAO) context.getBean("prisonerDataDAO");

	public boolean update(String cardId,String sex)
	{
		try
		{
			PrisonerData prisonerData = prisonerDataDao.getPrisonerData(cardId);
			System.out.println(prisonerData.getPrisonerDataID());
			prisonerData.setSex(sex);
			if(prisonerDataDao.update(prisonerData))
			{
				log.debug("update prisonerdata successful cardId is : "+cardId);
			}
			else
			{
				log.debug("could not update prisonerdata Info ");
				return false;
			}
			return true;
		}catch(Exception e)
		{
			log.error(e.getMessage());
			return false;
		}
	}
	
	public PrisonerData getPrisonerData(String cardID)
	{
		try
		{
			PrisonerData prisonerData = prisonerDataDao.getPrisonerData(cardID);
			log.debug("getPrisonerData by ID : "+prisonerData.getPrisonerDataID());
			return prisonerData;
		}catch(Exception e)
		{
			log.error(e.getMessage());
			return null;
		}
	}
}
