<%@page import="java.util.ArrayList"%>
<%@page import="com.dutproject.coffee360admin.model.bean.PlaceReportDetails"%>
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
	<%
    ArrayList<PlaceReportDetails> details =
    	    (ArrayList<PlaceReportDetails>)request.getAttribute("details");
    if (details == null || details.size() <= 0) {
   	%>
   	    <p>No data</p>
   	<% } else { %>
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
			int index = 1;
			for (PlaceReportDetails detail : details) {
			%>
			<tr>
				<td><%=index++ %></td>
				<td><%=detail.getPlaceName() %></td>
				<td><%=detail.getDescription() %></td>
				<td><%=detail.getQuantity() %></td>
				<td>
					<a href="<%=request.getContextPath() %>/UpdatePlaceForm?id=<%=detail.getReportId()%>">edit</a>
					<a href="<%=request.getContextPath()%>/DeleteCoffeeShopReport?id=<%=detail.getReportId()%>"
					   onclick="return confirm('Are you sure?')">delete</a>
					<a href="<%=request.getContextPath()%>/IgnoreCoffeeShopReport?id=<%=detail.getReportId()%>"
					   onclick="return confirm('Are you sure?')">ignore</a>
				</td>
			</tr>
			<% } %>
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
		<a href="<%=request.getContextPath() %>/PlaceReport?page=<%=previousPageNumber %>">&lt;Trước</a>
		<input id="page" value="<%=pageNumber %>" size="1" onkeydown="submitWhenEnter();">/<%=maxPageNumber %>
		<a href="<%=request.getContextPath() %>/PlaceReport?page=<%=nextPageNumber %>">Sau&gt;</a>
	<% } %>
	
	<script type="text/javascript">
	    function showNotification() {
	        
	    }
	    
	    function submitWhenEnter() {
	        if (event.keyCode == 13) {
	            var page = document.getElementById("page").value;
	            var url = "<%=request.getContextPath() %>/PlaceReport?page=" + page;
	            window.location = url;
	            return false;
	        }
	    }
	</script>

</center>
</body>
</html>