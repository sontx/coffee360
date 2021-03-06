<%@page import="com.dutproject.coffee360admin.model.bean.PhotoReportDetails"%>
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
<center>
	<jsp:include page="navbar.jsp"></jsp:include>

	<h1>Photo Report</h1>

    <%
    ArrayList<PhotoReportDetails> details =
    	    (ArrayList<PhotoReportDetails>) request.getAttribute("details");
    if (null == details || 0 == details.size()) {
    %>
    	<p>No data</p>
    <% } else { %>
		<table>
			<tr>
				<th>Index</th>
				<th>Image</th>
				<th>Status</th>
				<th>Quantity of Report</th>
				<th>Action</th>
			</tr>
			<%
	        int index = 1;
	        for (PhotoReportDetails detail : details) {
	        %>
			<tr>
				<td><%=index++ %></td>
				<td>
				     <a href="<%=request.getContextPath() %>/ViewPhotoReport?id=<%=detail.getReportId() %>">
				         <img height="128" width="128" alt="image" src="<%=detail.getPhotoUrl() %>"></a>
				</td>
				<td><%=detail.getCaption() %></td>
				<td><%=detail.getQuantity() %></td>
				<td>
				 <a href="<%=request.getContextPath() %>/ChangePhotoReportState?state=delete&id=<%=detail.getReportId() %>" onclick="return confirm('Are you sure?')">Delete</a> |
				 <a href="<%=request.getContextPath() %>/ChangePhotoReportState?state=ignore&id=<%=detail.getReportId() %>" onclick="return confirm('Are you sure?')">Ignore</a>
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
	    <input id="page" value="<%=pageNumber %>" size="1" onkeydown="submitWhenEnter();" onkeypress='return filterChar();'> / <%=maxPageNumber %>
	    <a href="<%=request.getContextPath() %>/PhotoReport?page=<%=nextPageNumber %>">Sau&gt;</a>
    <% } %>
    
    <script type="text/javascript">
        function submitWhenEnter() {
            if (event.keyCode == 13) {
                var page = document.getElementById("page").value;
                var url = "<%=request.getContextPath() %>/PhotoReport?page=" + page;
                window.location = url;
                return false;
            }
        }
        
        function filterChar() {
            return event.charCode >= 48 && event.charCode <= 57;
        }
    </script>
    
</center>
</body>
</html>