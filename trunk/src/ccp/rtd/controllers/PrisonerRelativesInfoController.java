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
import ccp.rtd.managers.InfoPrisonerManager;
import ccp.rtd.managers.JbpmManager;
import ccp.rtd.managers.RelativePrisonerManager;

/**
 * Servlet implementation class for Servlet: PrisonerRelativesInfo
 *
 */
 public class PrisonerRelativesInfoController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    
	public PrisonerRelativesInfoController() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}  	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	} 
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---- PrisonerRelativeInfoController ----");
		HttpSession session = request.getSession();
		try
		{
			Long processInstanceID = (Long) session.getAttribute("pID");
			String cardID = (String)session.getAttribute("cardID");
			if(request.getParameter("bSubmit")== null || request.getParameter("bSubmit").equals(null))
			{
				PrisonerData prisonerData = new RelativePrisonerManager().getPrisonerData(cardID);
				request.setAttribute("prisonerData",prisonerData);
				request.setAttribute("typeRelatives",new RelativePrisonerManager().getTypeRelatives());
				request.getRequestDispatcher("rcp/prisonerRelativesInfo.jsp").forward(request, response);
			}
			else
			{
				System.out.println("################################################################################");
				System.out.println("### process processInstance Id : "+processInstanceID+" Task is :"+JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName());
				System.out.println("################################################################################");
				String forkNode = JbpmManager.findProcessInstance(processInstanceID).findToken("/toRelativeInfo").getNode().getName();
				System.out.println("ForkNode : "+forkNode);
				if(forkNode.equalsIgnoreCase("prisonerRelativesInfo"))
				{
					boolean isSaveRelative = new RelativePrisonerManager().savePrisonerRelative(cardID
							,Integer.parseInt(request.getParameter("reletiveType"))
							,request.getParameter("txtName")
							,request.getParameter("txtSurname"));
					if(isSaveRelative)
					{
						System.out.println("save successful");
					}
					else
					{
						request.getRequestDispatcher("rcp/rcpfailed.jsp").forward(request, response);
					}
					
					JbpmManager.signalProcessInstance(processInstanceID);
					
				}
				System.out.println("test node "+JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName());
				
				if("join".equalsIgnoreCase(JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName()))
				{
					System.out.println("you are join ja pi = "+processInstanceID);
					JbpmManager.signalProcessInstance(processInstanceID);
					System.out.println("test node2 "+JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName());
					if("prisonerDoctorOpinions".equals(JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName()))
					{
						System.out.println("next nodeName : "+JbpmManager.findProcessInstance(processInstanceID).getRootToken().getNode().getName());
						request.getRequestDispatcher("PrisonerDoctorOpinionsController").forward(request, response);
					}
					else
					{
						System.out.println("not prisonerDoctorOpinions");
						request.getRequestDispatcher("rcp/rcpfailed.jsp").forward(request, response);
					}
				}
				else
				{
					System.out.println("not join");
					request.getRequestDispatcher("rcp/rcpfailed.jsp").forward(request, response);
				}
			}
		}catch(Exception e)
		{
			System.out.println("ERROR : "+e.getMessage());
			request.getRequestDispatcher("rcp/rcpfailed.jsp").forward(request, response);
		}
	} 
}