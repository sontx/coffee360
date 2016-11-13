<%@page import="com.dutproject.coffee360admin.model.bean.PhotoReport"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo Report Management</title>
<style>
table {
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>

	<h1>Photo Report</h1>

	<table>
		<tr>
			<th>Index</th>
			<th>Image</th>
			<th>Status</th>
			<th>Quantity of Report</th>
			<th>Action</th>
		</tr>
		<%
            ArrayList<PhotoReport> listPhotoReports = (ArrayList<PhotoReport>)request.getAttribute("listPhotoReports");
            int index = 1;
            for (PhotoReport report : listPhotoReports) {
        %>
		<tr>
			<td><%=index++ %></td>
			<td>
			     <a href="<%=request.getContextPath() %>/ViewPhotoReport?id=<%=report.getReportId() %>">
			         <img alt="image" src="<%=report.getPhotoUrl() %>"></a>
			</td>
			<td><%=report.getStatus() %></td>
			<td><%=report.getQuantity() %></td>
			<td>
			 <a href="<%=request.getContextPath() %>/DeletePhotoReport?id=<%=report.getReportId() %>">Delete</a> |
			 <a href="<%=request.getContextPath() %>/IgnorePhotoReport?id=<%=report.getReportId() %>">Ignore</a>
			</td>
		</tr>
		<%  } %>
	</table>

	<!-- pagination -->
    <%
        int pageNumber = (Integer) request.getAttribute("pageNumber");
        int maxPageNumber = (Integer) request.getAttribute("maxPageNumber");
        int previousPageNumber = pageNumber - 1;
        if (previousPageNumber <= 0) {
            previousPageNumber = 1;
        }
        int nextPageNumber = pageNumber + 1;
        if (nextPageNumber > maxPageNumber) {
            nextPageNumber = maxPageNumber;
        }
    %>
    <a href="<%=request.getContextPath() %>/PhotoReport?page=<%=previousPageNumber %>">&lt;Trước</a>
    <input value="<%=pageNumber %>" size="1"> / <%=maxPageNumber %>
    <a href="<%=request.getContextPath() %>/PhotoReport?page=<%=nextPageNumber %>">Sau&gt;</a>

</body>
</html>