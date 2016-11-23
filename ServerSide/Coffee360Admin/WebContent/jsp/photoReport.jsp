<%@page import="com.dutproject.coffee360admin.model.bean.PhotoReportDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Image Report</title>
</head>
<body>
<center>
	<jsp:include page="navbar.jsp"></jsp:include>

    <% PhotoReportDetails detail = (PhotoReportDetails) request.getAttribute("detail"); %>
	<img height="128" width="128" alt="image report" src="#<%=detail.getPhotoUrl() %>">
	<p><%=detail.getCaption() %></p>
	<p>
		<a href="<%=request.getContextPath() %>/ChangePhotoReportState?state=delete&id=<%=detail.getReportId() %>">delete</a> |
		<a href="<%=request.getContextPath() %>/ChangePhotoReportState?state=ignore&id=<%=detail.getReportId() %>">ignore</a>
	</p>
</center>
</body>
</html>