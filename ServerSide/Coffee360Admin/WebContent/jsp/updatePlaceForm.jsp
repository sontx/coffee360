<%@page import="com.dutproject.coffee360.model.bean.Tag"%>
<%@page import="com.dutproject.coffee360admin.model.bean.PlaceDetails"%>
<%@page import="java.util.List"%>
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
			PlaceDetails placeDetails = (PlaceDetails) request.getAttribute("placeDetails");
			List<Tag> tags = placeDetails.getTags();
			String str_tags = "";
			if (tags != null) {
	            for (Tag tag : tags) {
	                str_tags += tag.getName() + Tag.DELIMITER;
	            }
			}
		%>
		<form action="<%=request.getContextPath() %>/UpdatePlace" method="post">
		    <input type="hidden" name="placeId" value="<%=placeDetails.getId() %>">
		    Name<input type="text" name="placeName" value="<%=placeDetails.getName() %>">
		    Description<input type="text" name="description" value="<%=placeDetails.getDescription() %>">
		    Tag<input type="text" name="tags" value="<%=str_tags %>">
			<input type="submit" value="Save">
			<a href="#" onclick="history.go(-1);">Cancel</a>
		</form>
		
	</center>

</body>
</html>