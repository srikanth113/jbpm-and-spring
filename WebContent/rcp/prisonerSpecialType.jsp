<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ccp.rtd.entities.PrisonerData"%>
<%@ page import="ccp.rtd.entities.TypePrisoner" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ผู้ต้องขังคนสำคัญ</title>
	
</head>
<body>
	<% 
		PrisonerData prisonerData = (PrisonerData)request.getAttribute("prisonerData"); 
		List<TypePrisoner> typePrisoners = (List<TypePrisoner>)request.getAttribute("typePrisoners");
	%>
	<form action="PrisonerSpecialTypeController" method="post">
		<div>
			<table>
				<tr>
					<td colspan="2" align="center"><b>ผู้ต้องขังคนสำคัญ</b></td>
				</tr>
				<tr>
					<td>รหัส :</td>
					<td><%= prisonerData.getPrisonerDataID() %></td>
				</tr>
				<tr>
					<td>ชื่อหลัก : </td>
					<td><%=prisonerData.getRealFirstName() %></td>
				</tr>
				<tr>
					<td>ประเภท : </td>
				 	<td>
				 		<select name="selectCriminal">
				 			<%for(TypePrisoner typedata : typePrisoners)
				 			{	
				 			%>
				 				<option value="<%=typedata.getTypePrisonerID()%>"><%=typedata.getName() %></option>
				 			<%} %>
				 		</select>
				 	</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" name="bSubmit" value="submit"/></td>
				</tr>
			</table>
		</div>
	</form>
	
</body>
</html>