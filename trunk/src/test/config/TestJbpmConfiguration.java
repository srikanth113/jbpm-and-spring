/**
 * 
 */
package test.config;

import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springmodules.workflow.jbpm31.JbpmTemplate;

import junit.framework.TestCase;


public class TestJbpmConfiguration extends AbstractTransactionalDataSourceSpringContextTests
{
	private static ApplicationContext context = new ClassPathXmlApplicationContext("jbpmContext.xml");
	public JbpmTemplate jbpmTemplate = (JbpmTemplate) context.getBean("jbpmTemplate");
	public void testSimplePersistence()
	{
		processInstanceIsCreatedWhenUserSubmitsWebappForm();
	}
	
	public void processInstanceIsCreatedWhenUserSubmitsWebappForm()
	{
		
		ProcessInstance pi = jbpmTemplate.getProcessDefinition().createProcessInstance();
		Token token = pi.getRootToken();
		assertEquals("start",token.getNode().getName());
		token.signal();
		assertEquals("createReceiveProfile",token.getNode().getName());
		token.signal();
		assertEquals("prisonerPersonalInfo",token.getNode().getName());
		token.signal();
		assertEquals("end",token.getNode().getName());
	}
	
	public void testFindPi ()
	{
		System.out.println("===============");
		jbpmTemplate.getHibernateTemplate().getSessionFactory().openSession();
		ProcessInstance pi = jbpmTemplate.findProcessInstance(1L);
		System.out.println(pi.getId());
		assertEquals(1,pi.getId());
		
	}

}
