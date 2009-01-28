package ccp.rtd.jbpm.Handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.DecisionHandler;

public class EvaluateTypeDecisionHandler implements DecisionHandler{

	private static final Log log = LogFactory.getLog(EvaluateTypeDecisionHandler.class);
	public static final String hasType = "no";
	
	public String decide(ExecutionContext executionContext) throws Exception {
		System.out.println("###########################################");
		System.out.println("### EvaluateTypeDecisionHandler Class");
		System.out.println("###########################################");
		System.out.println("the ctx is null?"+executionContext);
		System.out.println("Variable is : "+executionContext.getVariable("hasType"));
		String transitionRoute = "no";
		transitionRoute = (String) executionContext.getVariable("hasType");
		System.out.println("the transition route we take is --- > "+ transitionRoute);
		if("no".equals(executionContext)) executionContext.setVariable("decided","hasn't specail Type Prisoner ");
		return transitionRoute;
	}
}
