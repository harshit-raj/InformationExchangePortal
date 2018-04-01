<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/views/includes.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
	integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
	integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
	crossorigin="anonymous"></script>
<title>ASQA</title>
</head>
<body>

	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">ASQA</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="/asqa/home">Home</a></li>
			<li><a href="/asqa/viewProfile">Profile</a></li>
			<li><a href="/asqa/ques">Questions</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li style="float: right;"><a href="/asqa/logout">Logout</a></li>
		</ul>
	</div>
	</nav>


	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2">
				<div class="table-responsive">
					<table class="table">
						<tr>
							<td>
								<center>
									<img class="img-circle" alt="Profile pic"
										src="/asqa/profilePic/${usr.userId}"
										style="width: 150px; height: 150px">
								</center>
							</td>
						</tr>
						<tr class="info">
							<td>${usr.firstName}&nbsp${usr.lastName}</td>
						</tr>
						<tr class="info">
							<td>${usr.email}</td>
						</tr>
						<tr class="info">
							<td><a href="/asqa/ques/addQuest">Ask Question</a></td>
						</tr>
						<tr class="info">
							<td><a href="/asqa/ques">View Question</a></td>
						</tr>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<tr class="success">
								<td><a href="/asqa/admin/viewUsers">View Users</a></td>
							</tr>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<tr class="warning">
								<td><a href="/asqa/moderate">Moderate</a></td>
							</tr>
						</sec:authorize>
					</table>
				</div>

			</div>
			<div class="col-sm-8">


				<ul class="nav nav-tabs">
					<li><a href="/asqa/viewProfile/${usr.userId}">Question Asked</a></li>
					<li class="active"><a href="#">Questions Answered</a></li>
				</ul>


				<c:if test="${!empty question }">
					<table>

						<c:forEach items="${question}" var="quest">
							<div class="well well-lg">
								<h5>
									<b>${quest.questionTitle}</b>
								</h5>
								<hr>
								<p>${quest.questionDesc}</p>
								<p style="color: #A9A9A9">by ${quest.user.firstName}
									${quest.user.lastName}</p>
								<hr>

								<a href="/asqa/ques/viewQuest/${quest.questionId}"><button
										type="button" class="btn btn-default">View</button></a> <a
									href="/asqa/ques/viewQuest/${quest.questionId}" style="float: right;"><button
										type="button" class="btn btn-danger">Delete</button></a>


							</div>
						</c:forEach>
					</table>
				</c:if>

			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
</body>
</html>