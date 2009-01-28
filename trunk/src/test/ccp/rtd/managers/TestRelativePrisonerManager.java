package test.ccp.rtd.managers;

import java.util.List;

import ccp.rtd.entities.PrisonerData;
import ccp.rtd.managers.ReceivePrisonerManager;
import ccp.rtd.managers.RelativePrisonerManager;
import junit.framework.TestCase;

public class TestRelativePrisonerManager extends TestCase{
	private RelativePrisonerManager mng = null;
	static String prisonerDataID = "tesRelative4";
	
	public void testSaveReceivePrisoner()
	{
		ReceivePrisonerManager receiveMng = new ReceivePrisonerManager();
		boolean isSave=receiveMng .saveReceivePrisoner(prisonerDataID, "courtFName", "courtLName", "LFName", "LLName","no");
		assertTrue(isSave);
	}
	
	public void testGetPrisonerData()
	{
		mng = new RelativePrisonerManager();
		PrisonerData prisonerData = mng.getPrisonerData(prisonerDataID);
		assertEquals(prisonerDataID,prisonerData.getPrisonerDataID());
	}
	
	public void testGetTypeRelatives()
	{
		mng = new RelativePrisonerManager();
		List typeRelatives =mng.getTypeRelatives();
		System.out.println(typeRelatives.size());
	}
	
	public void testSavePrisonerRelative()
	{
		mng = new RelativePrisonerManager();
		boolean isSave = mng.savePrisonerRelative(prisonerDataID,1,"name","surname");
	}
}
