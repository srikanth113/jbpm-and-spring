package ccp.rtd.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ccp.rtd.entities.Doctor;

public class DoctorDAO extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(DoctorDAO.class);
	
	public Doctor getDoctor(Integer doctorID)
	{
		return (Doctor) getHibernateTemplate().get(Doctor.class,doctorID);
	}
	
	public List<Doctor> getDoctors()
	{
		return getHibernateTemplate().find("FROM Doctor");
	}
	
	public boolean save(Doctor doctor)
	{
		log.debug("saving Doctor instance");
		try
		{
			getHibernateTemplate().saveOrUpdate(doctor);
			log.debug("save successful DoctorID set to : "+doctor.getDoctorID());
			return true;
		}catch(RuntimeException re)
		{
			log.error("save failed ",re);
			return false;
		}
	}
}
