package ccp.rtd.jbpm.Handler;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ccp.rtd.managers.ReceivePrisonerManager;

public class CreatePrisonerHandler implements ActionHandler{
	private static final Log log = LogFactory.getLog(CreatePrisonerHandler.class); 
	private ReceivePrisonerManager mng = null;
	
	
	
	public void execute (ExecutionContext executionContext)
	{
		
	}
}
