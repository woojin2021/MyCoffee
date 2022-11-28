<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
</head>
<body>

	<%@ include file="./User_menu.jsp" %>
	
	<div class="container mt-5" align="center">
		<div class="container row">
			<div class ="col-md">
				<img src="/resources/img/Coffee1.jpg" style="max-width:100%;">
			</div>
			<div class="col-md">
			<h3 class="form-signin-heading">Please sign in</h3>
			<c:if test="${!empty error}">
				<div class="alert alert-danger">아이디와 비밀번호를 확인해 주세요</div>
			</c:if>
			<form class="form-signin" style="max-width:400px;" role="form" action="/user/logincheck" method="post">
				<div class="form-group">
					<label for="inputUserName" class="sr-only">User Name</label> <input
						type="text" class="form-control" placeholder="ID" name="userid" id="userid"
						required autofocus>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="sr-only">Password</label> <input
						type="password" class="form-control" placeholder="Password" id="password"
						name="password" required>
				</div>
				<button class="btn btn btn-lg btn-success btn-block" type="submit" id="login">로그인</button>
			</form>
			</div>
		</div>
	</div>
</body>
</html>