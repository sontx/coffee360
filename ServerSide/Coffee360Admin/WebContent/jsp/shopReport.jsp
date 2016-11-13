<%@page import="java.util.ArrayList"%>
<%@page import="com.dutproject.coffee360admin.model.bean.ShopReport"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Coffee Shop Report Management</title>
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
<center>

	<jsp:include page="navbar.jsp"></jsp:include>
	
	<h1>Coffee Shop Report</h1>
	
	<!-- table of coffee shop reports -->
	<table>
		<!-- table headers -->
		<tr>
			<th>Index</th>
			<th>Coffee Shop Name</th>
			<th>Description</th>
			<th>Quantity of Report</th>
			<th>Action</th>
		</tr>
		
		<!-- table datas -->
		<%
			ArrayList<ShopReport> listCoffeeShopReports = (ArrayList<ShopReport>)request.getAttribute("listCoffeeShopReports");
			int index = 1;
			for (ShopReport report : listCoffeeShopReports) {
		%>
		<tr>
			<td><%=index++ %></td>
			<td><%=report.getPlaceName() %></td>
			<td><%=report.getDescription() %></td>
			<td><%=report.getQuantity() %></td>
			<td>
				<a href="<%=request.getContextPath() %>/EditCoffeeShopForm?id=<%=report.getId() %>">edit</a>
				<a href="<%=request.getContextPath() %>/DeleteCoffeeShopReport?id=<%=report.getId() %>"  onclick="return confirm('Are you sure?')">delete</a>
				<a href="<%=request.getContextPath() %>/IgnoreCoffeeShopReport?id=<%=report.getId() %>" onclick="return confirm('Are you sure?')">ignore</a>
			</td>
		</tr>
		<%	} %>
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
	<a href="<%=request.getContextPath() %>/CoffeeShopReport?page=<%=previousPageNumber %>">&lt;Trước</a>
	<input value="<%=pageNumber %>" size="1">/<%=maxPageNumber %>
	<a href="<%=request.getContextPath() %>/CoffeeShopReport?page=<%=nextPageNumber %>">Sau&gt;</a>

</center>
</body>
</html>