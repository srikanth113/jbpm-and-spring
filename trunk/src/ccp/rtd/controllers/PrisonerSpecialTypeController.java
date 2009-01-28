package ccp.rtd.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jbpm.taskmgmt.exe.TaskInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ccp.rtd.entities.PrisonerData;
import ccp.rtd.jbpm.dao.ProcessJbpmDAO;
import ccp.rtd.managers.InfoPrisonerManager;
import ccp.rtd.managers.JbpmManager;
import ccp.rtd.managers.SpecialTypePrisonerManager;

/**
 * Servlet implementation class for Servlet: PrisonerSpecialTypeController
 *
 */
 public class PrisonerSpecialTypeController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    
	public PrisonerSpecialTypeController() {
		super();
	}   	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}  
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		System.out.println("----- PrisonerSpecialType -----");
		try
		{
			Long processInstanceID = (Long) session.getAttribute("pID");
			String cardID = (String)session.getAttribute("cardID");
			
			if(request.getParameter("bSubmit")== null || request.getParameter("bSubmit").equals(null))
			{
				PrisonerData prisonerData = new SpecialTypePrisonerManager().getPrisonerData(cardID);
				request.setAttribute("prisonerData",prisonerData);
				request.setAttribute("typePrisoners",new SpecialTypePrisonerManager().getTypePrisoners());
				request.getRequestDispatcher("rcp/prisonerSpecialType.jsp").forward(request, response);
			}
			else
			{
				System.out.println("################################################################################");
				System.out.println("### process processInstance Id : "+processInstanceID+" Task is :"+JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName());
				System.out.println("################################################################################");
				
				if("specialTypePrisoner".equals(JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName()))
			     {
					if(new SpecialTypePrisonerManager().savePrisonerHasType(cardID,Integer.parseInt(request.getParameter("selectCriminal"))))
    	        	{
    	        		System.out.println("update PrisonaSpecialType successful");
    	        		JbpmManager.signalProcessInstance(processInstanceID);
    					request.getRequestDispatcher("PrisonerInfoController").forward(request, response);
    	        	}
    	        	else
    	        	{
    	        		request.getRequestDispatcher("rcp/rcpfailed.jsp").forward(request, response);
    	        	}
			     }
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR : "+e.getMessage());
		}
	}  
}