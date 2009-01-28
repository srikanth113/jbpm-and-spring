package test.config;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;

import junit.framework.TestCase;


public class TestForkJoin extends TestCase{
	public void testNodeFork()
	{
		ProcessDefinition processDefinition = ProcessDefinition.parseXmlString(
				  "<process-definition >" +
				  "		<start-state name='start'>" +
			      "    		<transition name='' to='fork1' /> " +
			      "  	</start-state>" +
			      "  	<fork name='fork1'>"+
			      "    		<transition name='fk1' to='node1' />"+
			      "    		<transition name='fk2' to='node2' />"+
			      "  	</fork>"+
			      "	 	<node name='node1'>"+
			      "    		<transition name='jn1' to='join1' /> "+
			      "	 	</node>"+
			      "		<node name='node2'>"+
			      "			<transition name='jn2' to='join1' />"+
			      "		</node>"+
			      "  	<state name='state2'>"+
			      "   		 <transition name='' to='end' /> "+
			      "  	</state>"+
			      "  	<state name='state1'>"+
			      "   		 <transition name='' to='state2' /> "+
			      "  	</state>"+
			      "  	<join name='join1'>"+
			      "    		 <transition name='' to='state2' />"+
			      " 	 </join>"+
			      "  	<end-state name='end' /> "+
			      "</process-definition>"
			    ); 
		
		
		 assertNotNull("Definition should not be null",processDefinition);
		 ProcessInstance pi = new ProcessInstance(processDefinition);
		 assertEquals("Instance is in start state",pi.getRootToken().getNode().getName(),"start");
		 pi.signal();
		 System.out.println(pi.getRootToken().getNode().getName());
		// assertEquals("instance is in wait state", pi.getRootToken().getNode().getName(),"state1");
		 System.out.println(pi.getRootToken().getNode().getName());
		 Map children = pi.getRootToken().getChildren();
		 Collection kids = children.values();
		 System.out.println(kids.size());
		 for(Iterator it = kids.iterator();it.hasNext();)
		 {
			 Token token = (Token)it.next();
			 
			 System.out.println("For kids :"+token.getFullName()+ "Node name:"+token.getNode().getName());
			//assertTrue("Fork child token "+token.getFullName()+" has ended ",token.hasEnded());
		 }
		 pi.signal();
		 assertEquals("Instance is in end state ",
				    pi.getRootToken().getNode().getName(),
				    "end");
		 assertTrue("Instance has ended ",pi.hasEnded());
		 
	}
	
	public void testForkTaskTest()
	{
		ProcessDefinition processDefinition = ProcessDefinition.parseXmlString(
				  "<process-definition >" +
				  "		<start-state name='start'>" +
			      "    		<transition name='' to='fork1' /> " +
			      "  	</start-state>" +
			      "  	<fork name='fork1'>"+
			      "    		<transition name='fk1' to='node1' />"+
			      "    		<transition name='fk2' to='node2' />"+
			      "  	</fork>"+
			      "	 	<state name='node1'>"+
			      "    		<transition name='jn1' to='join1' /> "+
			      "	 	</state>"+
			      "		<state name='node2'>"+
			      "			<transition name='jn2' to='join1' />"+
			      "		</state>"+
			      "  	<state name='state1'>"+
			      "   		 <transition name='' to='end' /> "+
			      "  	</state>"+
			      "  	<join name='join1'>"+
			      "    		 <transition name='' to='state1' />"+
			      " 	 </join>"+
			      "  	<end-state name='end' /> "+
			      "</process-definition>"
			    ); 
		 assertNotNull("Definition should not be null",processDefinition);
		 ProcessInstance pi = new ProcessInstance(processDefinition);
		 assertEquals("Instance is in start state",pi.getRootToken().getNode().getName(),"start");
		 pi.signal();
		 assertEquals("instance is in wait state", pi.getRootToken().getNode().getName(),"state1");
		 System.out.println(pi.getRootToken().getNode().getName());
		 Map children = pi.getRootToken().getChildren();
		 Collection kids = children.values();
		 System.out.println(kids.size());
		 for(Iterator it = kids.iterator();it.hasNext();)
		 {
			 Token token = (Token)it.next();
			 
			 System.out.println("For kids :"+token.getFullName()+ "Node name:"+token.getNode().getName());
			//assertTrue("Fork child token "+token.getFullName()+" has ended ",token.hasEnded());
		 }
		 pi.signal();
		 assertEquals("Instance is in end state ",
				    pi.getRootToken().getNode().getName(),
				    "end");
		 assertTrue("Instance has ended ",pi.hasEnded());
	}

}
