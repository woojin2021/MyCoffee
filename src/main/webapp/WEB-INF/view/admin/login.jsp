<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="refer.jsp"%>
<style type="text/css">
.login-canvas {
	background-color: rgb(250, 250, 250);
	border-radius: 3px;
	box-shadow: rgb(0 0 0 / 23%) 3px 3px 8px 0px;
}
.login-button {
	color: rgb(74, 190, 202);
}
</style>
</head>
<body>

		<div class="container d-flex justify-content-center mt-5" >
			<div class="col-md-4 login-canvas">
				<form class="form-signin pt-3 pb-2" action="/admin/login" method="post">
					<div class="form-group">
						<label class="">아이디</label> 
						<input type="text" class="form-control" placeholder="ID" name='uid' required autofocus>
					</div>
					<div class="form-group">
						<label class="">비밀번호</label> 
						<input type="password" class="form-control" placeholder="Password" name='pwd' required>
					</div>
					<span class="text-danger"><c:out value="${result}"/></span> 
					<hr>
					<div class="d-flex justify-content-end">
						<button class="btn login-button" type="submit">로그인</button>
					</div>
				</form>
			</div>
		</div>
	
</body>
</html>