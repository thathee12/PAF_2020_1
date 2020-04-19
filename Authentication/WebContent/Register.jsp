<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<script>
	function validate() {
		var firstname = document.form.firstname.value;
		var lastname = document.form.lastname.value;
		var email = document.form.email.value;
		var mobile = document.form.mobile.value;
		var Address = document.form.Address.value;
		var NIC = document.form.NIC.value;
		var password = document.form.password.value;
		var conpassword = document.form.conpassword.value;

		if (firstname == null || firstname == "") {
			alert("Full Name can't be blank");
			return false;
		} else if (lastname == null || lastname == "") {
			alert("Last can't be blank");
			return false;
		} else if (email == null || email == "") {
			alert("Email can't be blank");
			return false;
		} else if (mobile == null || mobile == "") {
			alert("mobile number can't be blank");
			return false;
		} else if (Address == null || Address == "") {
			alert("address can't be blank");
			return false;
		} else if (NIC == null || NIC == "") {
			alert("NIC can't be blank");
			return false;
		} else if (password.length < 6) {
			alert("Password must be at least 6 characters long.");
			return false;
		} else if (password != conpassword) {
			alert("Confirm Password should match with the Password");
			return false;
		}
	}
</script>
<style>
body, html {
background-image:url("image/aaaaaaaaa.png");
	height: 10%;
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  
 
}
</style>
</head>
<body>
	<center>
		<h2>Register patient Details</h2>
	</center>
	<form name="form" action="RegisterServlet" method="post"
		onsubmit="return validate()">
		<table align="center">
			<tr>
				<td><label for="Firstname">Firstname</label></td>
				<td><input type="text" class="form-control" name="firstname" /></td>
			</tr>
			<tr>
				<td><label for="lastname">lastname</label></td>
				<td><input type="text" class="form-control" name="lastname" /></td>
			</tr>
			<tr>
				<td><label for="email">Email</label></td>
				<td><input type="email" class="form-control" name="email" /></td>
			</tr>
			<tr>
				<td><label for="mobile">Mobile NO</label></td>
				<td><input type="text" class="form-control" name="mobile" /></td>
			</tr>
			<tr>
				<td><label for="address">Address</label></td>
				<td><input type="textarea" class="form-control" name="Address" /></td>
			</tr>
			<tr>
				<td><label for="nic">NIC</label></td>
				<td><input type="tex" class="form-control" name="NIC" /></td>
			</tr>
			<tr>
				<td><label for="nic">Password</label></td>
				<td><input type="password" class="form-control" name="password" /></td>
			</tr>
			<tr>
				<td><label for="nic">Confirm Password</label></td>
				<td><input type="password"  class="form-control" name="conpassword" /></td>
			</tr>
			<tr>
				<td><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></td>
			</tr>
			<tr>
				<td></td>
				<td></br><input class="btn btn-success" type="submit" value="Register"></input></t><input
					class="btn btn-danger" type="reset" value="Reset"></input></td>
			</tr>
			</br>
			<tr>
			
				<td><label for="nic">If you have profile</label></td>
				<td><a href="login.jsp"><input class="btn btn-primary" type="submit" value="login"></a></td>
			</tr>
		</table>
	</form>


</body>
</html>