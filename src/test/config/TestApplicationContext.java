package test.config;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class TestApplicationContext{
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf = (SessionFactory)ctx.getBean("sessionFactory");
		Session session = sf.openSession();
		String sql = "SELECT cardId FROM PrisonerData";
		SQLQuery query= session.createSQLQuery(sql);
		query.addScalar("cardId",Hibernate.STRING);
		List list = query.list();
		for(int i =0;i<list.size();i++)
		{
			String name = (String)list.get(i);
			System.out.println(name);
		}
		session.close();
		sf.close();
	}
}
