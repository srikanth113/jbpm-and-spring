package test.config;

import org.jbpm.graph.exe.ProcessInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springmodules.workflow.jbpm31.JbpmTemplate;
import org.hibernate.Hibernate;
public class TestFindProcessInstance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("jbpmContext.xml");
		JbpmTemplate jbpmTemplate = (JbpmTemplate)context.getBean("jbpmTemplate");
		ProcessInstance pi = jbpmTemplate.findProcessInstance(1L);
		System.out.println(pi.getId());
	}

}
