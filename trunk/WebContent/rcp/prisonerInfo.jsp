<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ccp.rtd.entities.PrisonerData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>รายละเอียดประวัติทั่วไป</title>
</head>
<body>
	<% PrisonerData prisonerData =(PrisonerData) request.getAttribute("prisonerData"); %>
	<form name="" action="PrisonerInfoController" method="get">
		<div>
			<table>
				<tr>
					<td colspan="2" align="center"><b>รายละเอียดประวัติทั่วไป</b></td>
				</tr>
				<tr>
					<td>รหัส: </td>
					<td><%=prisonerData.getPrisonerDataID() %></td>
				</tr>
				
				<tr>
					<td>ชื่อหลัก : </td>
					<td><%=prisonerData.getRealFirstName() %>&nbsp;<%=prisonerData.getRealLastName() %></td>
				</tr>
				<tr>
					<td>เพศ : </td>
					<td>
						<input type="radio" name="chkSex" id="chkSex" value="female"/>หญิง 
						<input type="radio" name="chkSex"id="chkSex" value="male"/>ชาย
					</td>
				</tr>
				<tr>
					<td colspan="2">
						blah ๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆ
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="bntSubmit" value="submit"/></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>