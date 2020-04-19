<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Login</h2>

	<form method="post" action="LoginController" name="LoginForm"
		onsubmit="return validate();">

		Username :<input type="text" name="txt_username"> Password :<input
			type="password" name="txt_password"> <input type="submit"
			name="btn_login" value="Login">

		<h3>
			Your don't have a account? <a href="Register.jsp">Register</a>
		</h3>

	</form>
</body>
</html>