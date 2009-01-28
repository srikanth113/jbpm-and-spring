package test.ccp.rtd.managers;

import ccp.rtd.entities.PrisonerData;
import ccp.rtd.managers.ReceivePrisonerManager;
import junit.framework.TestCase;


public class TestReceivePrisonerManager extends TestCase{
	private ReceivePrisonerManager  mng = null;
	private String cardId = "test";
	
	public void testSave()
	{
		mng = new ReceivePrisonerManager();
		boolean isSave=mng.saveReceivePrisoner(cardId, "courtFName", "courtLName", "LFName", "LLName","no");
		assertTrue(isSave);
	}
	
	public void testGetPrisonerData()
	{
		PrisonerData prisonerData = new ReceivePrisonerManager().getPrisonerData(cardId);
		System.out.println(prisonerData.getPrisonerDataID());
		assertEquals(cardId,prisonerData.getPrisonerDataID());
	}
	
	
}
