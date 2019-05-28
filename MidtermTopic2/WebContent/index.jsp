<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body bgcolor="#ffffce">
	<!--<h1>index</h1>-->
	<div align="center">
		<c:set value="#c4e1ff" var="color" />
		<!--<a href="testSQL1">測試SQL</a>-->
		<!-- 		<form name="insertMemberFormA" action="TestInsertForm2" method="POST"> -->
		<form name="insertMemberFormA" action="InsertForm" method="POST">
			<table border="1" width="50%">
				<thead>
					<tr bgcolor='${color}'>
						<th height="60" colspan="4" align="center">職業調查表單資料</th>
					</tr>
				</thead>
				<tbody>
					<!--1-->
					<tr bgcolor='${color}'>
						<td width="120" height="40">編號：</td>
						<td width="600" height="40" align="left"><input
							style="text-align: left" name="id_name" value="${param.id}"
							type="text" size="46" />
							<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.id}</div></td>
					</tr>
					<!--2-->
					<!--<font color='black' size="-1">&nbsp;&nbsp;格式為yyyy-MM-dd</font>-->
					<tr bgcolor='${color}'>
						<td width="120" height="40">日期：</td>
						<td width="600" height="40" align="left"><input
							style="text-align: left" name="date_name" value="${param.date}"
							type="text" size="46" /> <font>&nbsp;&nbsp;格式為yyyy-MM-dd</font>
							<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.date}</div></td>
					</tr>
					<!--3-->
					<tr bgcolor='${color}'>
						<td width="120" height="40">職業別：</td>
						<td width="600" height="40" align="left"><input
							style="text-align: left" name="job_category_name"
							value="${param.job_category}" type="text" size="46" />
							<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.job_category}</div></td>
					</tr>
					<!--4-->
					<tr bgcolor='${color}'>
						<td width="120" height="40">行業別：</td>
						<td width="600" height="40" align="left"><input
							name="industry_name" value="${param.industry}" type="text"
							size="46" />
							<div style="color: #FF0000; font-size: 100%; display: inline">
								${ErrorMsg.industry}</div></td>
					</tr>
					<!--5-->
					<tr bgcolor='${color}'>
						<td width="120" height="40">受雇員工人數：</td>
						<td width="600" height="40" align="left"><input
							name="employees_number_name" value="${param.employees_number}"
							type="text" size="46" />
							<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.employees_number}</div></td>
					</tr>
					<!--6-->
					<tr bgcolor='${color}'>
						<td width="120" height="40">總薪資：</td>
						<td width="600" height="40" align="left"><input
							name="total_salary_name" value="${param.total_salary}"
							type="text" size="46" />
							<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.total_salary}</div></td>
					</tr>
					<!--7-->
					<tr bgcolor='${color}'>
						<td width="120" height="40">經常性薪資：</td>
						<td width="600" height="40" align="left"><input
							name="recurrent_salary_name" value="${param.recurrent_salary}"
							type="text" size="46" />
							<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.recurrent_salary}</div></td>
					</tr>
					<!--8-->
					<tr bgcolor='${color}'>
						<td width="120" height="40">非經常性薪資：</td>
						<td width="600" height="40" align="left"><input
							name="non_recurring_salary_name"
							value="${param.non_recurring_salary}" type="text" size="46" />
							<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.non_recurring_salary}</div></td>
					</tr>
					<tr bgcolor='${color}'>
						<td colspan="2">
							<div align="center">
								<input type="submit" value="新增" name="insert_name" /> <input
									type="submit" value="修改" name="update_name" /> <input
									type="submit" value="刪除" name="delete_name" /> <input
									type="submit" value="查詢" name="select_name" />
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<div style="color: #FF0000; display: inline">${ErrorMsg.exception}</div>
		</form>
		<br>
		<form>
			<span id="show"></span>
		</form>
	</div>
	<script type="text/javascript">
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				document.getElementById("show").innerHTML = xhttp.responseText;
			}
		}
		xhttp.open("GET", "SelectName2", true);
		xhttp.send();
	</script>
</body>
</html>