package test.ccp.rtd.managers;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import ccp.rtd.entities.PrisonerData;
import ccp.rtd.managers.InfoPrisonerManager;

public class TestInfoPrisonerManager extends TestCase{
	private InfoPrisonerManager mng = null;
	
	public void testUpdate() 
	{
		mng = new InfoPrisonerManager();
		boolean isUpdate = mng.update("1111111111132","male");
		System.out.println(isUpdate);
		assertTrue(isUpdate);
	}
	
	public void testgetPrisonerData()
	{
		mng = new InfoPrisonerManager();
		PrisonerData prisonerData = mng.getPrisonerData("1111111111132");
		assertEquals("1111111111132",prisonerData.getPrisonerDataID());
	}
}
