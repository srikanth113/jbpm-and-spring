<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection,org.jbpm.taskmgmt.exe.TaskInstance" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%Collection<TaskInstance> tasks = (Collection)request.getAttribute("tasks"); %>
	<form name="frmMain" id="frmMain" method="post" action="SearchByProcessInstaceIdController">
		<div>
			<table>
				<tr>
					<td>
						Search Task by ProcessInstanceID :
					</td>
					<td>
						<input type="text" name="searchId" />
					</td>
					<td>
						<input type="submit" name="btnSubmit" value="ค้นหา"/>
					</td>
				</tr>
				<tr>
					<td> process is :</td>
					<%if (tasks != null) 
					{
						 	out.println("there are " + tasks.size() + " tasks...left");
							for (TaskInstance t : tasks) 
							{ 
					%>
					<td colspan="2"><%=t.getName()%></td>
					<% 		}
					}
					%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>