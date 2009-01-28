package test.ccp.rtd.managers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ccp.rtd.entities.Doctor;
import ccp.rtd.entities.PrisonerData;
import ccp.rtd.managers.DoctorOpinionsPrisonerManager;
import ccp.rtd.managers.ReceivePrisonerManager;

import junit.framework.TestCase;

public class TestDoctorOpinionsPrisonerManager extends TestCase{
	
	private static final Log log = LogFactory.getLog(TestDoctorOpinionsPrisonerManager.class);
	private DoctorOpinionsPrisonerManager mng = null;
	private String cardID = "testDoctor1";
	
	public void testSavePrisonerData()
	{
		ReceivePrisonerManager receiveMng = new ReceivePrisonerManager();
		boolean isSave=receiveMng .saveReceivePrisoner(cardID , "courtFName", "courtLName", "LFName", "LLName","no");
		assertTrue(isSave);
	}
	
	public void testGetPrisonerData()
	{
		mng = new DoctorOpinionsPrisonerManager();
		PrisonerData prisonerData = mng.getPrisonerData(cardID);
		assertEquals(cardID,prisonerData.getPrisonerDataID());
	}
	
	public void testGetDoctors()
	{
		List<Doctor> doctors = new DoctorOpinionsPrisonerManager().getDoctors();
		System.out.println(doctors.size());
	}
	
	public void testSaveHealth()
	{
		mng = new DoctorOpinionsPrisonerManager();
		boolean isSave = mng.saveHealth(cardID, 1, 1,"OK","Opinion");
		assertTrue(isSave);
	}
}
