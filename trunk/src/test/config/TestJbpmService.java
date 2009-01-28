package test.config;

import junit.framework.TestCase;

import org.jbpm.graph.exe.ProcessInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springmodules.workflow.jbpm31.JbpmTemplate;

import ccp.rtd.jbpm.dao.JbpmService;


public class TestJbpmService extends TestCase{
	private static ApplicationContext context = new ClassPathXmlApplicationContext("jbpmContext.xml");
	public JbpmService jbpmService = (JbpmService) context.getBean("jbpmService");
	public void testFindProcessInstance()
	{
		ProcessInstance pi = jbpmService.getProcessInstance(5L);
		System.out.println(pi.getId());
		assertEquals(5L, pi.getId());
	}
}
