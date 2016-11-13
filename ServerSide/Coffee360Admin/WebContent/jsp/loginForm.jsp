<%@page import="java.io.FileInputStream"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../resource/message.js"></script>
<title>Admin Login</title>

<style>
input, button {
	display: block;
}
</style>

</head>
<body onload="onPageLoaded();">

<script>
function onPageLoaded() {
	changeFormTexts();
	configForm();
}
</script>

<center>
	<h1><span id="header"></span></h1>

	<form method="post" action="<%=request.getContextPath() %>/Home" onsubmit="return validateForm()">
		<input type="text" name="username" id="username" placeholder=USERNAME_INPUT_PLACEHOLDER>
		<br>
		<input type="password" name="password" id="password">
		<br>
		<input type="submit" id="submit"/>
	</form>
</center>

<script>/* change form texts */
function changeFormTexts() {
	setHeaderText();
	setPlaceHolderForInputs();
	setValueForLoginButton();
}

function setHeaderText() {
	document.getElementById("header").innerHTML = HEADER;
}

function setPlaceHolderForInputs() {
	document.getElementById("username").placeholder = USERNAME_INPUT_PLACEHOLDER;
	document.getElementById("password").placeholder = PASSWORD_INPUT_PLACEHOLDER;
}

function setValueForLoginButton() {
	document.getElementById("submit").value = LOGIN_BUTTON_VALUE;
}
</script>

<script>/* config form */
function configForm() {
	document.getElementById("password").maxLength = 10;
}
</script>

<script>/* validate form */
function validateForm() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    
    /* validate for username input */
    if (username == null || username.length <= 0) {
    	alert(BLANK_USERNAME_ALERT);
    	return false;
    } else if (!isLetter(username.charAt(0))) {
		alert(USERNAME_START_WITH_NON_LETTER_ALERT);
		return false;
	} else if (!username.match(/^[\w]+$/)) {
		alert(USERNAME_CONTAIN_SPECIAL_CHARACTER);
		return false;
	}
    
    /* validate for password input */
	if (password == null || password.length <= 0) {
    	alert(BLANK_PASSWORD_ALERT);
    	return false;
    }
	
}

function isLetter(character) {
	var upperOfCharacter = character.toUpperCase();
	var lowerOfCharacter = character.toLowerCase();
	return upperOfCharacter != lowerOfCharacter;
}
</script>

</body>
</html>