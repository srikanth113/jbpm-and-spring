package ccp.rtd.utilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HqSpringContext {
	private static final Log log = LogFactory.getLog(HqSpringContext.class);
	private static ApplicationContext appContext;
	static String[] paths =new String [] {"applicationContext.xml","jbpmContext.xml"};
	public static ApplicationContext getApplicationContext()
	{
		if(appContext == null)
		{
			appContext = new ClassPathXmlApplicationContext(paths);
		}
		return appContext;
	}

}
