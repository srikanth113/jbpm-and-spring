package ccp.rtd.managers;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.graph.exe.ProcessInstance;
import org.springframework.context.ApplicationContext;

import ccp.rtd.jbpm.dao.ProcessJbpmDAO;
import ccp.rtd.utilities.HqSpringContext;

public class JbpmManager {
	private static final Log log = LogFactory.getLog(JbpmManager.class);
	private static ApplicationContext getContext()
	{
		return  HqSpringContext.getApplicationContext();
	}
	
	private static ProcessJbpmDAO getProcessJbpmDao()
	{
		return (ProcessJbpmDAO) getContext().getBean("processJbpmDAO");
	} 

	public static ProcessInstance getNewProcessInstance()
	{
		return getProcessJbpmDao().getNewProcessInstance();
	}
	
	public static ProcessInstance findProcessInstance(final long processInstanceId)
	{
		return getProcessJbpmDao().findProcessInstance(processInstanceId);
	}
	
	public static void saveVariable(final Long processInstanceId, final String key,final String value)
	{
		getProcessJbpmDao().saveVariable(processInstanceId, key, value);
	}
	
	public static void signalProcessInstance(final long processInstanceId)
	{
		getProcessJbpmDao().signalProcessInstance(processInstanceId);
	}
	
	public static Collection findTaskInstances(final long processInstanceId)
	{
		return getProcessJbpmDao().findTaskInstances(processInstanceId);
	}
}
