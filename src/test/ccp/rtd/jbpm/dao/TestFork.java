package test.ccp.rtd.jbpm.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;
import org.springframework.context.ApplicationContext;

import ccp.rtd.jbpm.dao.ProcessFormDAO;
import ccp.rtd.jbpm.dao.ProcessJbpmDAO;
import ccp.rtd.managers.InfoPrisonerManager;
import ccp.rtd.managers.ReceivePrisonerManager;
import ccp.rtd.managers.SpecialTypePrisonerManager;
import ccp.rtd.utilities.HqSpringContext;


public class TestFork extends TestCase {
	private static final Log log = LogFactory.getLog(TestFork.class);
	private ApplicationContext appContext = HqSpringContext.getApplicationContext();
	private ProcessJbpmDAO processJbpmDao = (ProcessJbpmDAO) appContext
			.getBean("processJbpmDAO");
	private ProcessInstance processInstance ;
	
	public void testCreateReceiveProfileWithNo()
	{
		this.processInstance = this.processJbpmDao.getNewProcessInstance();
		assertEquals("createReceiveProfile", this.processInstance.getRootToken().getNode().getName());
		
		// task createReceiveProfile
		Collection<TaskInstance> tasks = this.processJbpmDao.findTaskInstances(this.processInstance.getId());
		processJbpmDao.saveVariable(this.processInstance.getId(), "hasType", "no");
		String hasType = (String) this.processJbpmDao.fetchVariableByKey(this.processInstance.getId(),"hasType");
		assertEquals("no",hasType);

		if("createReceiveProfile".equals(processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName()))
	     {
	        	processJbpmDao.signalProcessInstance(this.processInstance.getId());
	     }
		
		assertEquals("prisonerPersonalInfo", processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName());
		if("prisonerPersonalInfo".equals(processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName()))
	     {
	        	processJbpmDao.signalProcessInstance(this.processInstance.getId());
	     }
		
		
	}
	
	public void testCreateReceiveProfileWithYes()
	{
		this.processInstance = this.processJbpmDao.getNewProcessInstance();
		assertEquals("createReceiveProfile", this.processInstance.getRootToken().getNode().getName());
		
		// task createReceiveProfile
		Collection<TaskInstance> tasks = this.processJbpmDao.findTaskInstances(this.processInstance.getId());
		processJbpmDao.saveVariable(this.processInstance.getId(), "hasType", "yes");
		String hasType = (String) this.processJbpmDao.fetchVariableByKey(this.processInstance.getId(),"hasType");
		assertEquals("yes",hasType);

		if("createReceiveProfile".equals(processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName()))
	     {
	        	processJbpmDao.signalProcessInstance(this.processInstance.getId());
	     }
		
		assertEquals("specialTypePrisoner", processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName());
		if("specialTypePrisoner".equals(processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName()))
	     {
	        	processJbpmDao.signalProcessInstance(this.processInstance.getId());
	     }
		if("prisonerPersonalInfo".equals(processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName()))
	     {
	        	processJbpmDao.signalProcessInstance(this.processInstance.getId());
	     }
		System.out.println("next p: "+processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName());
		if("fork".equals(processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName()))
	     {
			String forkNode = this.processJbpmDao.findProcessInstance(this.processInstance.getId()).findToken("/toRelativeInfo").getNode().getName();
	        assertEquals("prisonerRelativesInfo",forkNode);
			System.out.println("forkNode : "+forkNode);
	        forkNode = this.processJbpmDao.findProcessInstance(this.processInstance.getId()).findToken("/toUpdate").getNode().getName();
	       // assertEquals("prisonerSkillInfo",forkNode);
	        System.out.println("forkNode next : "+forkNode);
	        //processJbpmDao.signalProcessInstance(this.processInstance.getId());
	        /*Map children = processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getChildren();
	   		Collection kids = children.values();
	   		System.out.println(kids.size());*/
	     }
		
		System.out.println("current Node: "+processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName());
		processJbpmDao.signalProcessInstance(this.processInstance.getId());
		System.out.println("current Node: "+processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName());
		processJbpmDao.signalProcessInstance(this.processInstance.getId());
		System.out.println("current Node: "+processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName());
		processJbpmDao.signalProcessInstance(this.processInstance.getId());
		System.out.println("current Node: "+processJbpmDao.findProcessInstance(this.processInstance.getId()).getRootToken().getNode().getName());
	}
	
	public void testFork()
	{
		
	}
	
}
