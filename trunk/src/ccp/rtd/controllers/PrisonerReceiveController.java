package ccp.rtd.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import ccp.rtd.jbpm.dao.ProcessFormDAO;
import ccp.rtd.jbpm.dao.ProcessJbpmDAO;
import ccp.rtd.managers.JbpmManager;
import ccp.rtd.managers.ReceivePrisonerManager;
import ccp.rtd.utilities.HqSpringContext;
import javax.servlet.ServletConfig;
/**
 * Servlet implementation class for Servlet: PrisonerReceiveController
 *
 */
 public class PrisonerReceiveController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
   
	public PrisonerReceiveController() {
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
		try
		{	
			if(request.getParameter("btnSubmit")== null || request.getParameter("btnSubmit").equals(null))
			{
				request.getRequestDispatcher("rcp/prisonerReceive.jsp").forward(request, response);
			}
			else
			{
				ProcessInstance pi = JbpmManager.getNewProcessInstance();
				System.out.println("################################################################################");
				System.out.println("### Start process processInstance Id : "+pi.getId()+" Task is :"+ JbpmManager.findProcessInstance(pi.getId()).getRootToken().getNode().getName());
				System.out.println("################################################################################");
				String cardId = request.getParameter("txtCardId");
				String isHasType = request.getParameter("chkPrisonerStaCase");
				if(isHasType == null)
				{
					isHasType="no";
				}else
				{
					isHasType="yes";
				}
				
				if("createReceiveProfile".equals(JbpmManager.findProcessInstance(pi.getId()).getRootToken().getNode().getName()))
				{
					JbpmManager.saveVariable(pi.getId(), "cardId",cardId);
					JbpmManager.saveVariable(pi.getId(), "hasType", isHasType);
					boolean isSave = new ReceivePrisonerManager().saveReceivePrisoner(
									request.getParameter("txtCardId"),
									request.getParameter("txtCourtFName"),
									request.getParameter("txtCourtLName"),
									request.getParameter("txtLicenseFName"), 
									request.getParameter("txtLicenseLName"), 
									isHasType);
						
					JbpmManager.signalProcessInstance(pi.getId());
			    }
				session.setAttribute("pID",(Long)pi.getId());
				session.setAttribute("cardID",cardId);
				
				if(isHasType.equals("no"))
				{
					System.out.println("go to: PrisonerInfoController");
					request.getRequestDispatcher("PrisonerInfoController").forward(request, response);
				}else if(isHasType.equals("yes"))
				{
					System.out.println("go to: PrisonerSpecialTypeController");
					request.getRequestDispatcher("PrisonerSpecialTypeController").forward(request, response);
				}else
				{
					request.getRequestDispatcher("rcp/rcpfailed.jsp").forward(request, response);
				}
			}
		}catch(Exception ex)
		{
			System.out.println("ERROR : "+ex.getMessage());
		}
	}
}