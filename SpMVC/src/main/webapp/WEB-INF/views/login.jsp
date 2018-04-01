<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<center>
 			<h1>ASQA</h1>
 			<h6>Ask Questions and Answer</h6></br>
 		</center>
 		<div class="row">
 		
			<div class="col-4">
      			<!-- Col 1 -->
    		</div>
    		<div class="col-4">
      			<!-- Col 2 -->
      			<h3>Enter Credentials</h3>
      			
      			<form action ="j_spring_security_check" method="post">
					<div class="form-group">
						<label for="userName">Email</label>
						<input type="email" class="form-control" name="j_username" id="userName" placeholder="name@domain.com">
					</div>
					<div class="form-group">
						<label for="password">Password</label>
						<input type="password" name = "j_password" class="form-control" id="password" placeholder="XXXXXX">
					</div>
					<button type="submit" value = "Login" class="btn btn-primary">Login</button> 
					
				</form>	
				<font color="red">
					<span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
				</font>
				<br>
				<a href="register"><button class="btn btn-secondary">Register</button></a>
			</div>
			<div class="col-4">
				<!-- Col 3 -->
			</div>
		</div>
	</div>	
</body>
</html>