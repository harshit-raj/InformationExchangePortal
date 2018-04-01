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


<title>Profile</title>
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
				<div class="well well-lg">

					<h4>
						<b>Question</b>
					</h4>
					<hr>
					<h4>${question.questionTitle}</h4>
					<h5>${question.questionDesc}</h5>
					
					<h6 style="color: #A9A9A9">Asked by 
						<a href="/asqa/viewProfile/${question.user.userId}">${question.user.firstName} ${question.user.lastName}</a>   </h6>
					<!-- make this link to profile -->
					<hr>
					<a href="#" style="float: right;"><button
										type="button" id = "${question.questionId}" onclick="reportQuestion(event)" class="btn btn-default">
										<span class="glyphicon glyphicon-flag" id = "${question.questionId}" onclick="reportQuestion(event)"></span>
									</button></a>

					<form:form modelAttribute="answer"
						action="${pageContext.request.contextPath}/ques/viewQuest"
						method="post">
						<fieldset>
							<legend>Give Answer</legend>
							<p>
								<form:label for="answerText" path="answerText"
									cssErrorClass="error">Answer text</form:label>
								<br />
								<form:textarea class="form-control input-lg" path="answerText" />
								<form:errors path="answerText" />
							</p>

							<p>
								<button class="btn btn-primary" type="submit">Submit</button>
							</p>

						</fieldset>
					</form:form>





					<c:if test="${empty question.answers}">

						<p class="font-weight-light">No answers Yet!!!. Be the first
							to answer</p>
					</c:if>

					<c:if test="${!empty question.answers}">
						<table>

							<c:forEach items="${question.answers}" var="ans">
								<div>
									${ans.answerText}
									</p>
									<h6 style="color: #A9A9A9">Answered by
										<a href="/asqa/viewProfile/${ans.user.userId}">${ans.user.firstName} ${ans.user.lastName}</a>   </h6>
									<!-- make this link to profile -->

									<hr>


									<!--  <a href="usrs/detailBind/${quest.questionId}">Make this the answer link</a>-->


								</div>

							</c:forEach>
						</table>
					</c:if>
				</div>
			</div>
			<div class="col-sm--2"></div>
		</div>
	</div>
	<script type = "text/javascript">
	function reportQuestion(e){
		var xmlhttp;
		if (window.XMLHttpRequest) {
		    // code for modern browsers
		    xmlhttp = new XMLHttpRequest();
		 } 
		else {
		    // code for old IE browsers
		    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readystate ==4 && xmlhttp.status ==200){
				//reload
				location.reload(true);
				
			}
		}
		var tid = $(e.target)[0].id;
		window.alert("The question has been flagged for moderation.");
		//window.alert(''+ tid);
		xmlhttp.open("POST",'/asqa/ques/report/'+tid ,true);
		xmlhttp.send();
		
	}

</script>
</body>
</html>