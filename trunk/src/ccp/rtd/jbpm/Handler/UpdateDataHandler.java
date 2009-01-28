package ccp.rtd.jbpm.Handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

public class UpdateDataHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(UpdateDataHandler.class);

	public void execute(ExecutionContext executionContext) throws Exception {
		System.out.println("###############################################");
		System.out.println("### updating the prisoner data "+ executionContext);
		System.out.println("###############################################");
		executionContext.leaveNode();
	}
}
