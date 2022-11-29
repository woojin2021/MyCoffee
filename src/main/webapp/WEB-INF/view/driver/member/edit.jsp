<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="/resources/css/bootstrap.css" rel="stylesheet" />
<link href="/resources/css/join.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<!-- Javascript -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

</script>
<title>회원정보수정</title>

</head>
<body>
	<%@include file="include/menu.jsp" %>

	<div class="table-success">
		<div class="container">
			<h3 class="display-4" align="center">회원 정보 수정</h3>
		</div>
	</div>

	<div class="container d-flex justify-content-center mt-5">
	<div class="col-md-8 login-canvas">
		<form name="newMember" class="form-horizontal" id="join_form" method="post"
		onsubmit="return checkForm()" >
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-2">아이디</div>
				<div class="col-sm-6">
					<input name="did" type="text" class="form-control" value='${driver.did}' readonly="readonly">
				</div>
			</div>
			
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-2">비밀번호</div>
				<div class="col-sm-6">
					<input name="password" type="text" class="form-control" placeholder="password">
				</div>
			</div>
			
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-2">이름</div>
				<div class="col-sm-6">
					<input name="name" type="text" class="form-control" value='${driver.name}' >
				</div>
			</div>
			
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-2">휴대폰</div>
				<div class="col-sm-6">
					<input name="mobile" type="text" class="form-control" value='${driver.mobile}' >
				</div>
			</div>
			
			<div class="form-group row mt-5">
				<div class="offset-sm-2 col-sm-8 d-flex justify-content-around">
					<input type="submit" class="btn btn-primary" value="회원 정보 수정" > 
					<input type="button" class="btn btn-secondary" value="돌아가기" onclick="location.href='./driverOrder'">
					<a href="driver/member/delete" class="btn btn-danger">회원탈퇴</a>
				</div>
			</div>
			
		</form>
	</div>
	</div>
</body>
</html>