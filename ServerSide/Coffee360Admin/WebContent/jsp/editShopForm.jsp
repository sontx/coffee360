<%@page import="com.dutproject.coffee360.model.bean.Tag"%>
<%@page import="java.util.List"%>
<%@page import="com.dutproject.coffee360admin.model.bean.ShopReport"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Coffee Shop Form</title>
</head>
<body>

	<center>
	
		<jsp:include page="navbar.jsp"></jsp:include>

		<h1>Edit Coffee Shop</h1>
		<%
			ShopReport coffeeShopReport = (ShopReport) request.getAttribute("coffeeShopReport");
			List<Tag> tags = (List<Tag>) request.getAttribute("tags");
			String str_tags = "";
			for (Tag tag : tags) {
				str_tags += tag.getName() + ";";
			}
		%>
		<form>
			<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="placeName" value="<%=coffeeShopReport.getPlaceName() %>"></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><input type="text" name="description" value="<%=coffeeShopReport.getDescription() %>"></td>
			</tr>
			<tr>
				<td>Tag</td>
				<td><input type="text" name="tags" value="<%=str_tags %>"></td>
			</tr>
			</table>
			<input type="submit" value="Save">
			<a href="<%=request.getContextPath() %>/CoffeeShopReport">Cancel</a>
		</form>
		
	</center>

</body>
</html>