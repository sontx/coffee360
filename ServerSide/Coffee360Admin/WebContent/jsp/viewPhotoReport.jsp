<%@page import="com.dutproject.coffee360admin.model.bean.PhotoReport"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Image Report</title>
</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>

    <%
    PhotoReport report = (PhotoReport) request.getAttribute("report");
    %>
	<img alt="image report" src="#<%=report.getPhotoUrl() %>">
	<p><%=report.getUsername() %></p>
	<p><%=report.getStatus() %></p>
	<p>
		<a href="#<%=report.getReportId() %>">delete</a> |
		<a href="#<%=report.getReportId() %>">ignore</a>
	</p>

</body>
</html>