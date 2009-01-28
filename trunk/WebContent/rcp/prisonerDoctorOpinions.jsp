<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="ccp.rtd.entities.PrisonerData" %>
<%@ page import="ccp.rtd.entities.Doctor" %>
<%@ page import="java.util.List" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		PrisonerData prisonerData  = (PrisonerData) request.getAttribute("prisonerData"); 
		List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors");
	%>
	<form name="frmMain" action="PrisonerDoctorOpinionsController" method="POST">
		<div>
			<table>
				<tr>
					<td colspan="2" align="center"><b>ความเห็นแพทย์</b></td>
				</tr>
				<tr>
					<td>รหัส : </td>
					<td><%=prisonerData.getPrisonerDataID() %></td>
				</tr>
				<tr>
					<td>ชื่อหลัก : </td>
					<td><%=prisonerData.getRealFirstName() %></td>
				</tr>
				<tr>
					<td>หมอผู้ดูแล :</td>
					<td>
						<select name="doctor">
							<%for(Doctor doctor : doctors)
							{ 
							%>
								<option value="<%=doctor.getDoctorID() %>"><%=doctor.getName() %></option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>สุขภาพร่างกาย :
					</td>
					<td><input type="text" name="txtBodyHealthy" id="txtBodyHealthy"/></td>
				</tr>
				
				<tr>
					<td>ความเห็นของแพทย์ :</td>
					<td><input type="text" name="txtDoctorComment" id="txtDoctorComment"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" name="btnSubmit" value="Submit"/></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>