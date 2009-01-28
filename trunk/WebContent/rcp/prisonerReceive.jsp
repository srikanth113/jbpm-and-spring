<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>รับตัวผู้ต้องขังใหม่</title>
</head>
<body>
	<form name="frmMain" action="PrisonerReceiveController" method="post">
		<div>
			<table>
				<tr>
					<td colspan="2" align="center"><b>รับตัวผู้ต้องขังใหม่</b></td>
				</tr>
				<tr>
					<td>รหัสประจำตัวประชาชน :</td>
					<td><input type="text" name="txtCardId"/></td>
				</tr>
				<tr>
					<td>ชื่อจริง(ตามหมายศาล) : </td>
					<td><input type="text" name="txtCourtFName"/></td>
					<td>นามสกุลจริง(ตามหมายศาล) : </td>
					<td><input type="text" name="txtCourtLName"/></td>
				</tr>
				<tr>
					<td>ชื่อจริง(ตามบัตรประชาชน) : </td>
					<td><input type="text" name="txtLicenseFName"/></td>
					<td>นามสกุลจริง(ตามบัตรประชาชน): </td>
					<td><input type="text" name="txtLicenseLName"/></td>
				</tr>
				<tr>
					<td>ผู้ต้องหาคนสำคัญ : </td>
					<td><input type="checkbox" name="chkPrisonerStaCase" id="chkPrisonerStaCase" value="yes"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="btnSubmit" value="submit"/></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>