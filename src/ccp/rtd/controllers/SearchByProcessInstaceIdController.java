package ccp.rtd.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jbpm.taskmgmt.exe.TaskInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ccp.rtd.jbpm.dao.ProcessJbpmDAO;
import ccp.rtd.managers.JbpmManager;

/**
 * Servlet implementation class for Servlet: SearchByProcessInstaceIdController
 *
 */
 public class SearchByProcessInstaceIdController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    
	public SearchByProcessInstaceIdController() {
		super();
	}   	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-- doGet -- ");
		try
		{
			
		}catch(Exception e)
		{
			System.out.println("ERROR : "+e.getMessage());
		}
		
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-- dopost -- ");
		try
		{
			if (request.getParameter("btnSubmit")!=null)
			{
				Long processId=Long.valueOf(request.getParameter("searchId"));
				System.out.println("ProcessId is : "+processId);
				Collection<TaskInstance> tasks = JbpmManager.findTaskInstances(processId);
				request.setAttribute("tasks",tasks);
				request.getRequestDispatcher("search.jsp").forward(request, response);
			}
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	
}