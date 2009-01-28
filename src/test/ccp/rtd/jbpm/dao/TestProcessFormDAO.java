package test.ccp.rtd.jbpm.dao;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;
import org.springframework.context.ApplicationContext;
import org.jbpm.taskmgmt.exe.TaskInstance;
import ccp.rtd.jbpm.dao.ProcessFormDAO;
import ccp.rtd.managers.InfoPrisonerManager;
import ccp.rtd.managers.ReceivePrisonerManager;
import ccp.rtd.managers.SpecialTypePrisonerManager;
import ccp.rtd.utilities.HqSpringContext;
import junit.framework.TestCase;

public class TestProcessFormDAO extends TestCase {
	private static final Log log = LogFactory.getLog(TestProcessFormDAO.class);
	private ApplicationContext appContext = HqSpringContext.getApplicationContext();
	private ProcessFormDAO processFormDao = (ProcessFormDAO) appContext
			.getBean("processFormDAO");
	
	private ReceivePrisonerManager mng = null;
	public static Long processInstanceId;
	String cardId = "123456";
	String isHasType ="yes";


	public void testProsessInstanceIsCreatedWnenUserSubmitWebappForm() {
		// create new processInstance
		
		ProcessInstance pi = processFormDao.getNewProcessIsntance();
		System.out.println("ProcessInstanceId : " + pi.getId());
		System.out.println("####################################################");
		System.out.println("### Start process processInstance Id : "+pi.getId());
		System.out.println("#####################################################");
		
		
		// do next process
		TaskMgmtInstance taskMgmt = pi.getTaskMgmtInstance();
		Collection<TaskInstance> tasks = taskMgmt.getTaskInstances();
		if (tasks != null) {
			 System.out.println("1there are " + tasks.size() + " tasks...left");
			for (TaskInstance t : tasks) {
				System.out.println("#####the tasks are:" + t.getName() + " id is:"
						+ t.getId());
				if(t.getName().equalsIgnoreCase("saveCreateReceivePrisoner"))
    			{
					t.start();
					// save receivePrisonerData to table
					mng = new ReceivePrisonerManager();
					boolean isSave = mng.saveReceivePrisoner(cardId,"courtFName",
							"courtLName", "LFName", "LLName", isHasType);
					assertTrue(isSave);

					// save variable
					processFormDao.saveVariable(pi.getId(), "cardId", cardId);
					processFormDao.saveVariable(pi.getId(), "hasType", isHasType);
					//ContextInstance ctxI = pi.getContextInstance();
			        //ctxI.setVariable("hasType",isHasType);
					System.out.println("about to end task:" + t.getName());
    				t.end();
    				break;
    			}
			}
		}

		long pid = pi.getId();
		System.out.println("ProcessInstanceId : " + pi.getId());
		System.out.println("the id of pi is:" + pi.getId()+" the node name is:"+pi.getRootToken().getNode().getName());
		processInstanceId = pid;
		
		//ViewHasTypePrisonerDecision
		System.out.println("###############################################");
		System.out.println("### ViewHasTypePrisonerDecision ");
		System.out.println("###############################################");
		
		taskMgmt = pi.getTaskMgmtInstance();
		ContextInstance ctxI = pi.getContextInstance();
		System.out.println("----------what ctx has is:" + ctxI.getVariables().toString());
		System.out.println("----------what ctx has is:" + processFormDao.fetchVariableByKey(pi.getId(), "isHasType"));
		tasks = taskMgmt.getTaskInstances();
		if (tasks != null) {
            System.out.println("there are " + tasks.size() + " tasks...left");
            for (TaskInstance t : tasks) {
				System.out.println("2the tasks are:" + t.getName() + " id is:"
						+ t.getId());
				
			}
		}
		
		cardId = (String) processFormDao.fetchVariableByKey(pi.getId(), "cardId");
        isHasType = (String) processFormDao.fetchVariableByKey(pi.getId(), "hasType");
        System.out.println("are we coming from the decision Handler->" + processFormDao.fetchVariableByKey(pi.getId(), "decided"));
        System.out.println("The name of the current token is---->" + pi.getRootToken().getNode().getName());
		
        if(tasks != null)
        {
        	for(TaskInstance t : tasks)
        	{
        		System.out.println("!!!!!!! the task name: "+t.getName()+" has ths task ended? "+t.hasEnded());
        		if(!t.hasEnded())
        		{
        			t.start();
        			System.out.println("t start with : "+t.getName());
        			if(t.getName().equalsIgnoreCase("savePrisonerInfo"))
        			{
        				this.isHasType="no";
        				InfoPrisonerManager infoMng = new InfoPrisonerManager();
        	        	if(infoMng.update(cardId,"male"))
        	        	{
        	        		log.debug("update PrisonalInfo successful");
        	        	}
        				System.out.println("t start with : "+t.getName());
        				t.end();
        				break;
        			}
        			else if(t.getName().equalsIgnoreCase("specialTypePrisoner"))
        			{
        				System.out.println("---- specialTypePrisoner ---");
        				//t.start();
        				this.isHasType="yes";
        				SpecialTypePrisonerManager speMng = new SpecialTypePrisonerManager();
        				if(speMng.savePrisonerHasType(cardId,2))
        				{
        					log.debug("---- OK ------");
        				}
        				t.end();
        				break;
        			}
        			else
        			{
        				t.end();
        			}
        			
        		}
        	}
        }
        String nodeName = pi.getRootToken().getNode().getName();
        System.out.println("node name :"+nodeName);
        if("prisonerPersonalInfo".equals(nodeName))
        {
        	System.out.println("is prisonerPersonalInfo so we going to asvance it till th end ...!");
        	processFormDao.advanceProcessIntance(pi.getId());
        }else if("specialTypePrisoner".equals(nodeName))
        {
        	System.out.println("is specialTypePrisoner so we going to asvance it till th end ...!");
        	processFormDao.advanceProcessIntance(pi.getId());
        }
        if(pi.hasEnded())
        {
        	log.debug("has the process ended ??"+ pi.hasEnded());
        }
       
	}
	
	
}
