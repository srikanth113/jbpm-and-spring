<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ccp.rtd.entities.PrisonerData" %>
<%@ page import="ccp.rtd.entities.TypeRelative" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>รายละเอียดญาติ, เพื่อนสนิท</title>
</head>
<body>
	<%
		PrisonerData prisonerData = (PrisonerData) request.getAttribute("prisonerData"); 
		List<TypeRelative> typeRelatives = (List<TypeRelative>) request.getAttribute("typeRelatives");
	%>
	<form name="frmMain" action="PrisonerRelativesInfoController" method="POST">
		<div>
			<table>
				<tr>
					<td>รหัส : </td>
					<td><%=prisonerData.getPrisonerDataID() %></td>
				</tr>
				<tr>
					<td >ชื่อหลัก: </td>
					<td><%=prisonerData.getRealFirstName() %></td>
				</tr>
				<tr>
					<td colspan="4">ข้อมูลรายละเอียดญาติ , เพื่อนสนิท</td>
				</tr>
				<tr>
					<td>ชื่อ :</td>
					<td><input type="text" name="txtName"/></td>
					<td>นามสกุล :</td>
					<td><input type="text" name="txtSurname"/></td>
				</tr>
				<tr>
					<td>ความสัมพันธ์ :</td>
					<td colspan="3">
						<select name="reletiveType">
							<%for(TypeRelative typeRelative:typeRelatives){ 
							%>
								<option value="<%=typeRelative.getTypeReletiveID() %>"><%=typeRelative.getName() %></option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="submit" name="bSubmit" value="Submit"/>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>