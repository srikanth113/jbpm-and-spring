package ccp.rtd.managers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import ccp.rtd.dao.DoctorDAO;
import ccp.rtd.dao.HealthDAO;
import ccp.rtd.dao.PrisonerDataDAO;
import ccp.rtd.dao.TypeSicknessDAO;
import ccp.rtd.entities.Doctor;
import ccp.rtd.entities.Health;
import ccp.rtd.entities.PrisonerData;

import java.util.List;
import ccp.rtd.utilities.HqSpringContext;

public class DoctorOpinionsPrisonerManager {
	private static final Log log = LogFactory.getLog(DoctorOpinionsPrisonerManager.class);
	
	private ApplicationContext context = HqSpringContext.getApplicationContext();
	private DoctorDAO doctorDao = (DoctorDAO)context.getBean("doctorDAO");
	private PrisonerDataDAO prisonerDataDao = (PrisonerDataDAO)context.getBean("prisonerDataDAO");
	private HealthDAO healthDao = (HealthDAO) context.getBean("healthDAO");
	private TypeSicknessDAO typeSicknessDao = (TypeSicknessDAO) context.getBean("typeSicknessDAO");
	
	public PrisonerData getPrisonerData(String cardID)
	{
		return prisonerDataDao.getPrisonerData(cardID);
	}
	
	public List<Doctor> getDoctors()
	{
		return doctorDao.getDoctors();
	}
	
	public boolean saveHealth(String cardID,Integer typeSicknessID,Integer doctorID,String healthDesc,String opinion)
	{
		try
		{
			Health health = new Health(healthDesc,opinion,doctorDao.getDoctor(doctorID),prisonerDataDao.getPrisonerData(cardID),typeSicknessDao.getTypeSickness(typeSicknessID));
			if(healthDao.save(health))
			{
				log.debug("saving Health");
				return true;
			}
			else
			{
				log.debug("save Health failed");
				return false;
			}
		}catch(Exception e)
		{
			log.error(e.getMessage());
			return false;
		}
	}
}
