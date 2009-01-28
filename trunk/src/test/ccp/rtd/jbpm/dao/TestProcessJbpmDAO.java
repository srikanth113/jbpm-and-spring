package test.ccp.rtd.jbpm.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import ccp.rtd.jbpm.dao.ProcessFormDAO;
import ccp.rtd.jbpm.dao.ProcessJbpmDAO;
import ccp.rtd.utilities.HqSpringContext;

public class TestProcessJbpmDAO extends TestCase{
	private static final Log log = LogFactory.getLog(TestProcessFormDAO.class);
	private ApplicationContext jbpmContext = HqSpringContext.getApplicationContext();
	private ProcessJbpmDAO processJbpmDao = (ProcessJbpmDAO) jbpmContext
			.getBean("processJbpmDAO");
	
	private static final Long PROCESSINSTACE_ID = 1L;
	
	@Test
	public void testGetNewProcessInstance() {
		ProcessInstance pi = processJbpmDao.getNewProcessInstance();
		System.out.println("new ProcessInstance set processInstanceId is : "+pi.getId());
	}
	
	@Test
	public void testGetProcessInstances() {
		List<ProcessInstance> lst = new ArrayList<ProcessInstance>();
		lst=processJbpmDao.getProcessInstances();
		System.out.println(lst.size());
		Iterator ite = lst.iterator();
		while(ite.hasNext())
		{
			ProcessInstance pi = (ProcessInstance)ite.next();
			System.out.println("PI :"+pi.getId());
		}
	}

	@Test
	public void testSaveProcessInstance() {
		ProcessInstance pi = processJbpmDao.getNewProcessInstance();
		Long saveProcess = processJbpmDao.saveProcessInstance(pi);
		System.out.println("Test save ProcessInstance : "+saveProcess);
	}

	@Test
	public void testFindTaskInstances() {
		//processJbpmDao.signalProcessInstance(3L);
		ProcessInstance pi = processJbpmDao.getNewProcessInstance();
		System.out.println("findTaskInstance : "+processJbpmDao.findProcessInstance(pi.getId()).getRootToken().getNode().getName());
		Collection<TaskInstance> tasks = processJbpmDao.findTaskInstances(3L);
		if (tasks != null) 
		{
			 System.out.println("there are " + tasks.size() + " tasks...left");
			for (TaskInstance t : tasks) 
			{
				System.out.println("the tasks are:" + t.getName() + " id is:"
						+ t.getId());
				System.out.println("!!!!!!! the task name: "+t.getName()+" has ths task ended? "+t.hasEnded());
				/*if(!t.hasEnded())
				{
					t.start();
					System.out.println("t start with : "+t.getName());
				}*/
				
			}
		}
	}
	
	/*public void testFindTaskInstancesByProcessInstance()
	{
		ProcessInstance pi = processJbpmDao.findProcessInstance(56L);
		TaskMgmtInstance taskMgmt = pi.getTaskMgmtInstance();
		Collection<TaskInstance> tasks = taskMgmt.getTaskInstances();
		System.out.println("------- "+tasks.size());
	}*/

	@Test
	public void testFindProcessInstance() {
		/*ProcessInstance pi = processJbpmDao.findProcessInstance(1L);
		System.out.println("FindProcessInstance id is : "+pi.getId());
		System.out.println("previous "+pi.getRootToken().getNode().getName());
		
		processJbpmDao.signalProcessInstance(1L);
		// ต้อง  findbyid ใหม่ก่อนถึงเห็น
		System.out.println("Next "+pi.getRootToken().getNode().getName());*/
		ProcessInstance pi = processJbpmDao.findProcessInstance(21L);
		
		String forkNode = this.processJbpmDao.findProcessInstance(pi.getId()).findToken("/toRelativeInfo").getNode().getName();
        assertEquals("prisonerRelativesInfo",forkNode);
	}

	@Test
	public void testSignalToken() {
		System.out.println("----- testSignalToken -------");
		//processJbpmDao.signalProcessInstance(1L);
	}

	@Test
	public void testSaveVariableLongStringObject() {
		ProcessInstance pi = processJbpmDao.findProcessInstance(1L);
		processJbpmDao.saveVariable(pi.getId(), "key", "value");
		String key = (String)processJbpmDao.fetchVariableByKey(pi.getId(),"key");
		System.out.println("key : "+key);
	}

	public void testFetchVariableByKey()
	{
		System.out.println("--- testFetchVariable By Key ---");
		ProcessInstance pi = processJbpmDao.getNewProcessInstance();
		ContextInstance ctxI = pi.getContextInstance();
		ctxI.setVariable("test","testka");
		System.out.println(processJbpmDao.fetchVariableByKey(pi.getId(),"test"));
	}
	
	public void testShowNodeName()
	{
		System.out.println("--- testShowNodeName ---");
		String nodeName = processJbpmDao.findProcessInstance(1L).getRootToken().getNode().getName();
		System.out.println("nodeName : "+nodeName);
	}
	
	public void testTaskWithDecisionFalse()
	{
		
	}
}
