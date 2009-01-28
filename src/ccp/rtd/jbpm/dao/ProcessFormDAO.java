package ccp.rtd.jbpm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.JbpmContext;
import org.jbpm.JbpmException;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.springmodules.workflow.jbpm31.JbpmCallback;
import org.springmodules.workflow.jbpm31.JbpmTemplate;

public class ProcessFormDAO {
	private static final Log log = LogFactory.getLog(ProcessFormDAO.class);
	
	private JbpmTemplate jbpmTemplate;
	
	public ProcessFormDAO()
	{
		log.debug("ProcessFdromDAO");
	}

	public JbpmTemplate getJbpmTemplate() {
		return jbpmTemplate;
	}

	public void setJbpmTemplate(JbpmTemplate jbpmTemplate) {
		this.jbpmTemplate = jbpmTemplate;
	}
	
	public List<ProcessInstance> getProcessInstances()
	{
		return this.jbpmTemplate.findProcessInstances();
	}
	
	public ProcessInstance getNewProcessIsntance()
	{
		ProcessInstance pi = this.jbpmTemplate.getProcessDefinition().createProcessInstance();
		Token token = pi.getRootToken();
		token.signal();
		this.jbpmTemplate.saveProcessInstance(pi);
		return pi;
	}
	
	public Long saveProcessInstance(ProcessInstance pi)
	{
		return this.jbpmTemplate.saveProcessInstance(pi);
	}
	
	public String currentNode(final ProcessInstance pi) {
        String nodeName = (String) this.jbpmTemplate.execute(new JbpmCallback() {
            public Object doInJbpm(JbpmContext ctx) throws JbpmException {
                //pi.
                //pi.get
                return null;
                //ctx.getT
            }
            
        });
        return nodeName;
    }
	
	
	 public ProcessInstance findProcessInstance(Long instanceId) {
	        ProcessInstance pi = jbpmTemplate.findProcessInstance(instanceId);
	        return pi;
	                
	    }
	    
	    public void advanceProcessIntance(Long instanceId) {
	        ProcessInstance pi = jbpmTemplate.findProcessInstance(instanceId);
	        pi.signal();
	        jbpmTemplate.saveProcessInstance(pi);
	    }
	    
	    public void advanceProcessIntanceToTransition(Long instanceId, String transitionName) {
	        ProcessInstance pi = jbpmTemplate.findProcessInstance(instanceId);
	        
	        
	        pi.signal(transitionName);
	        jbpmTemplate.saveProcessInstance(pi);
	    }
	    
	    public void saveVariable(Long instanceId, String key, String value) {
	        ProcessInstance pi = jbpmTemplate.findProcessInstance(instanceId);
	        this.saveCtxVariable(pi, key, value);
	    }
	    
	    private void saveCtxVariable(ProcessInstance pi, String key, String value) {
	        ContextInstance ctxI = pi.getContextInstance();
	        ctxI.setVariable(key, value);
	        jbpmTemplate.saveProcessInstance(pi);
	    }
	    
	    public void saveVariable(ProcessInstance pi, String key, String value) {
	        this.saveCtxVariable(pi, key, value);
	    }
	    
	    public Object fetchVariableByKey(Long instanceId,String key) {
	        ProcessInstance pi = jbpmTemplate.findProcessInstance(instanceId);
	        ContextInstance ctxI = pi.getContextInstance();
	        return ctxI.getVariable(key);
	    }
}
