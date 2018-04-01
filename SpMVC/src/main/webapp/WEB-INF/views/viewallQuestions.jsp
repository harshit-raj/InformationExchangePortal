<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
		<title>Profile</title>
	</head>
	<body>
		<div class = "container-fluid">
			<h1>Navbar//////////////////////////////////////////////////</h1>
			<div class = "row">
				<div class = "col-2">
					<h5>User</h5>
					<table>
						<tr><td>${usr.userId}<td></tr>
						<tr><td>${usr.firstName} &nbsp ${usr.lastName}<td></tr>
						<tr><td>${usr.email}<td></tr>
						<tr><td><a href ="ques/addQuest">Ask Question</a></td></tr>
						<tr><td><a href ="ques/addQuest">View Questions</a></td></tr>
					</table>
				</div>
				<div class = "col-8">
					
					
						
						<h4>Questions Asked</h4>
						<c:if test="${!empty usr.question }">
						<table>
							
							<c:forEach items="${usr.question}" var ="quest" >
								<div>
									<h5>${quest.questionTitle}</h5>
									<p>${quest.questionDesc}</p>
									
									
									<a href="usrs/detailBind/${quest.questionId}">Make this the answer link</a>
									
									
								</div>
							</c:forEach>
						</table>
					</c:if>
					
				</div>
				<div class = "col-2"></div>
				</div>
			</div>
	</body>
</html>