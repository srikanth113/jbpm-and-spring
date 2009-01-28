package ccp.rtd.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ccp.rtd.entities.PrisonerData;
import ccp.rtd.jbpm.dao.ProcessJbpmDAO;
import ccp.rtd.managers.InfoPrisonerManager;
import ccp.rtd.managers.JbpmManager;
import ccp.rtd.managers.SpecialTypePrisonerManager;

/**
 * Servlet implementation class for Servlet: PrisonerInfoController
 *
 */
 public class PrisonerInfoController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    
	public PrisonerInfoController() {
		super();
	}   	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
		
	}   
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("----- PrisonerInfoController ---------");
		HttpSession session = request.getSession();
		try
		{	
			// check if session == null ด้วยนะ 
			Long processInstanceID = (Long) session.getAttribute("pID");
			String cardID = (String)session.getAttribute("cardID");
			
			if(request.getParameter("bntSubmit")== null || request.getParameter("bntSubmit").equals(null))
			{
				System.out.println("bntSubmit == null");
				PrisonerData prisonerData = new InfoPrisonerManager().getPrisonerData(cardID);
				System.out.println("prisonerdata ID : "+prisonerData.getPrisonerDataID());
				request.setAttribute("prisonerData",prisonerData);
				request.getRequestDispatcher("rcp/prisonerInfo.jsp").forward(request, response);
			}
			else
			{
				System.out.println("################################################################################");
				System.out.println("### process processInstance Id : "+processInstanceID+" Task is :"+JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName());
				System.out.println("################################################################################");
				
				if("prisonerPersonalInfo".equals(JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName()))
			    {
					if(new InfoPrisonerManager().update(cardID,request.getParameter("chkSex")))
    	        	{
    	        		System.out.println("update PrisonalInfo successful");
    	        	}
    	        	else
    	        	{
    	        		request.getRequestDispatcher("rcp/rcpfailed.jsp").forward(request, response);
    	        	}
					
					JbpmManager.signalProcessInstance(processInstanceID);
			    }
				if("fork".equals(JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName()))
				{
					JbpmManager.signalProcessInstance(processInstanceID);
					request.getRequestDispatcher("PrisonerRelativesInfoController").forward(request, response);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR : "+e.getMessage());
		}
	}  
}