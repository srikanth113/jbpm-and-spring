package ccp.rtd.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ccp.rtd.entities.PrisonerData;
import ccp.rtd.jbpm.dao.ProcessJbpmDAO;
import ccp.rtd.managers.DoctorOpinionsPrisonerManager;
import ccp.rtd.managers.JbpmManager;

/**
 * Servlet implementation class for Servlet: PrisonerDoctorOpinionController
 *
 */
 public class PrisonerDoctorOpinionsController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    
	public PrisonerDoctorOpinionsController() {
		super();
	}   	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	} 
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("----- PrisonerDoctorOpinionsController -----");
		HttpSession session = request.getSession();
		try
		{
			Long processInstanceID = (Long) session.getAttribute("pID");
			String cardID = (String)session.getAttribute("cardID");
			
			if(request.getParameter("btnSubmit")== null || request.getParameter("btnSubmit").equals(null))
			{
				PrisonerData prisonerData = new DoctorOpinionsPrisonerManager().getPrisonerData(cardID);
				request.setAttribute("prisonerData",prisonerData);
				request.setAttribute("doctors",new DoctorOpinionsPrisonerManager().getDoctors());
				request.getRequestDispatcher("rcp/prisonerDoctorOpinions.jsp").forward(request, response);
			}
			else
			{
				System.out.println("################################################################################");
				System.out.println("### process processInstance Id : "+processInstanceID+" Task is :"+JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName());
				System.out.println("################################################################################");
				
				if("prisonerDoctorOpinions".equals(JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName()))
			     {
						boolean isSave = new DoctorOpinionsPrisonerManager().saveHealth(cardID
								, 1
								, Integer.parseInt(request.getParameter("doctor"))
								, request.getParameter("txtBodyHealthy")
								, request.getParameter("txtDoctorComment"));
						if(isSave)
						{
							request.getRequestDispatcher("rcp/rcpsuccess.jsp").forward(request, response);
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
			System.out.println(e.getMessage());
		}
	} 
}