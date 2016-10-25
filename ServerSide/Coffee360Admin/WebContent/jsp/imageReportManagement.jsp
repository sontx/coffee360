<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Picture Report Management</title>
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

	<h1>Image Report</h1>

	<table>
		<tr>
			<th>Index</th>
			<th>Image</th>
			<th>Status</th>
			<th>Quantity of Report</th>
			<th>Action</th>
		</tr>
		<tr>
			<td>01</td>
			<td><a href="#"><img alt="image" src="#"></a></td>
			<td>Tuyen ban trai di choi Noel</td>
			<td>30</td>
			<td><a href="#">delete</a> <a href="#">ignore</a></td>
		</tr>
	</table>

	Pagination

</body>
</html>