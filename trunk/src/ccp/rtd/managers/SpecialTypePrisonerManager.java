package ccp.rtd.managers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import java.util.List;
import ccp.rtd.dao.PrisonerDataDAO;
import ccp.rtd.dao.PrisonerHasTypeDAO;
import ccp.rtd.dao.TypePrisonerDAO;
import ccp.rtd.entities.PrisonerData;
import ccp.rtd.entities.PrisonerHasType;
import ccp.rtd.entities.TypePrisoner;
import ccp.rtd.utilities.HqSpringContext;

public class SpecialTypePrisonerManager {
	private static final Log log = LogFactory.getLog(SpecialTypePrisonerManager.class);
	private ApplicationContext context = HqSpringContext.getApplicationContext();
	private PrisonerDataDAO prisonerDataDAO = (PrisonerDataDAO) context.getBean("prisonerDataDAO");
	private TypePrisonerDAO typePrisonerDAO = (TypePrisonerDAO) context.getBean("typePrisonerDAO");
	private PrisonerHasTypeDAO prisonerHasTypeDAO=(PrisonerHasTypeDAO) context.getBean("prisonerHasTypeDAO");
	
	
	public PrisonerData getPrisonerData(String cardId)
	{
		return prisonerDataDAO.getPrisonerData(cardId);
	}
	
	public List<TypePrisoner> getTypePrisoners()
	{
		return typePrisonerDAO.getTypePrisoners();
	}
	
	public boolean savePrisonerHasType(String cardId,Integer typePrisonerId)
	{
		try
		{
			PrisonerHasType prisonerHasType = new PrisonerHasType(prisonerDataDAO.getPrisonerData(cardId),
					 typePrisonerDAO.getTypePrisoner(typePrisonerId));
			if(prisonerHasTypeDAO.save(prisonerHasType))
			{
				log.debug("saving prisonerHasType");
				return true;
			}else
			{
				log.debug("save prisonerHasType failed");
				return false;
			}
			
		}catch(Exception e)
		{
			log.error(e.getMessage());
			return false;
		}
	}
	
	
}
