<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success Page</title>
<!-- buttonType = 1; 新增 -->
<!-- buttonType = 2; 修改 -->
<!-- buttonType = 3; 刪除 -->
<!-- buttonType = 4; 查詢 -->
</head>
<body bgcolor="#ffffce">
	<c:set value="#c4e1ff" var="color" />
	<%
		/*
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String requestName = request.getParameter("insert_name");
		System.out.println("requestName = " + requestName);
		request.setAttribute("showName", requestName);
		*/
	%>
	<div align="center">
		<!--<div>-->
		<!--<h1>Success Page</h1>-->
		<c:choose>
			<c:when test="${buttonType == 1}">
				<!--buttonType == 1<br>-->
			資料新增成功<br>
			以下是您新增的資料內容<br>
				<hr>
			</c:when>
			<c:when test="${buttonType == 2}">
				<!--buttonType == 2<br>-->
			資料修改成功<br>
			以下是修改後的資料內容<br>
			</c:when>
			<c:when test="${buttonType == 3}">
				<!--buttonType == 3<br>-->
			資料刪除成功<br>
			以下是您刪除的資料內容<br>
			※註：這裡顯示的是儲存在 JavaBean裡面的資料，實際上資料庫已無該筆資料紀錄。<br>
			</c:when>
			<c:when test="${buttonType == 4}">
				<!--buttonType == 4<br>-->
			資料查詢成功<br>
			以下是您查詢的資料內容<br>
			</c:when>
		</c:choose>
		<!--<hr>-->
		<br>
		<table border="1" bgcolor='${color}'>
			<tr>
				<td width="120" height="40">編號 PRIMARY KEY：</td>
				<td width="600" height="40">${memberBean.id}</td>
			</tr>
			<tr>
				<td width="120" height="40">日期：</td>
				<td width="600" height="40">${memberBean.date}</td>
			</tr>
			<tr>
				<td width="120" height="40">職類別：</td>
				<td width="600" height="40">${memberBean.job_category}</td>
			</tr>
			<tr>
				<td width="120" height="40">行業別：</td>
				<td width="600" height="40">${memberBean.industry}</td>
			</tr>
			<tr>
				<td width="120" height="40">受僱員工人數：</td>
				<td width="600" height="40">${memberBean.employees_number}</td>
			</tr>
			<tr>
				<td width="120" height="40">總薪資：</td>
				<td width="600" height="40">${memberBean.total_salary}</td>
			</tr>
			<tr>
				<td width="120" height="40">經常性薪資：</td>
				<td width="600" height="40">${memberBean.recurrent_salary}</td>
			</tr>
			<tr>
				<td width="120" height="40">非經常性薪資：</td>
				<td width="600" height="40">${memberBean.non_recurring_salary}</td>
			</tr>
		</table>
		<br>
		<a href="index.jsp">返回上一頁</a>
	</div>
	<br>
	<%--buttonType = ${buttonType}--%>
	<!--<br>-->
	<%--showName = ${showName}--%>
	<!--<br>-->
</body>
</html>