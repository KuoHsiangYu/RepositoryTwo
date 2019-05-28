<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GetWord2</title>
</head>
<body>
	<h1>GetWord2</h1>
	<%
		/* 接收數值資料 */
		int str = (Integer) request.getAttribute("cat");
		out.print(str);
		out.print("<br>");
	%>
</body>
</html>