package test.ccp.rtd.managers;

import java.util.List;

import ccp.rtd.entities.PrisonerData;
import ccp.rtd.managers.ReceivePrisonerManager;
import ccp.rtd.managers.SpecialTypePrisonerManager;
import junit.framework.TestCase;


public class TestSpecialTypePrisonerManager extends TestCase {
	private SpecialTypePrisonerManager mng = null;
	
	public void testSaveReceivePrisoner()
	{
		ReceivePrisonerManager receiveMng = new ReceivePrisonerManager();
		boolean isSave=receiveMng .saveReceivePrisoner("4", "courtFName", "courtLName", "LFName", "LLName","no");
		assertTrue(isSave);
	}
	
	public void testGetPrisonerData()
	{
		mng = new SpecialTypePrisonerManager();
		PrisonerData prisonerData = mng.getPrisonerData("4");
		assertEquals("4", prisonerData.getPrisonerDataID());
	}
	
	public void testGetTypePrisoners()
	{
		mng = new SpecialTypePrisonerManager();
		List typePrisoners = mng.getTypePrisoners();
		System.out.println(typePrisoners.size());
	}
	
	public void testSavePrisonerHasType()
	{
		mng = new SpecialTypePrisonerManager();
		boolean isSave = mng.savePrisonerHasType("4",2);
		assertTrue(isSave);
	}
	
}
