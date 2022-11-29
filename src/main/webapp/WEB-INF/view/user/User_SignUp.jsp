<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 가입</title>
<script type="text/javascript">
function check()
{
    const form ="SignUp";

	if (!document.forms[form].elements["id"].value) 
	{
		alert("아이디를 입력하세요.");
		return false;
	}
	if (!document.forms[form].elements["password"].value) 
	{
		alert("비밀번호를 입력하세요.");
		return false;
	}
	if (document.forms[form].elements["password"].value != document.forms[form].elements["password2"].value) 
	{
		alert("비밀번호를 동일하게 입력하세요.");
		return false;
	}
	if (!document.forms[form].elements["uname"].value) 
	{
		alert("반드시 이름을 입력하세요.");
		return false;
	}
}
</script>
</head>
<body>
	<%@ include file="./User_menu.jsp" %>
	
	<div class="container mt-5" align="center" style="background-color: orange;">
		<div class="container row">
			<div class ="col-md" style="display: flex;align-items: center;">
				<img src="/resources/img/Coffee1.jpg" style="max-width:100%;">
			</div>
			<div class="col-md">
				<h3 class="form-signin-heading">Please sign in</h3>
				<form role="form" name="SignUp" class="form-horizontal" action="/user/signup" method="post" onsubmit="return check()">
					<div class="form-group row">
						<label class="col-md-4 text-left">*아이디 : </label>
						<div class="col-md-6"><input type="text" name="userid" id="userid" class="form-control" placeholder="아이디를 입력하시오." autofocus></div>
					</div>
					<div class ="form-group row">
						<label class="col-md-4 text-left">*비밀번호 : </label>
						<div class="col-md-6"><input name ="password" id="password" type="password" class="form-control" placeholder="비밀번호를 입력하시오."></div>
					</div>
					<div class ="form-group row">
						<label class="col-md-4 text-left">*비밀번호 확인 : </label>
						<div class="col-md-6"><input name ="password2" id="password2" type="password" class="form-control" placeholder="비밀번호를 다시 입력하시오."></div>
					</div>
					<div class ="form-group row">
						<label class="col-md-4 text-left">*성명 : </label>
						<div class="col-md-6"><input name ="name" id="name" type="text" class="form-control" placeholder="성명를 입력하시오."></div>
					</div>
					<div class ="form-group row">
						<label class="col-md-4 text-left">*전화번호 : </label>
						<div class="col-md-6"><input type="text" name ="mobile" class="form-control" placeholder="모바일 전화번호"></div>
					</div>
					<div class ="form-group row">
						<label class="col-md-4 text-left">*주소 : </label>
						<div class="col-md-6"><input type="text" name ="address" class="form-control" placeholder="배달 주소를 입력해주세요."></div>
					</div>
					<div class="form-group  row">
						<div class="col-sm-offset-2 col-sm-10 ">
							<input type="submit" class="btn btn-primary" value="회원 가입" > 
							<input type="reset" class="btn btn-danger" value="취소" onclick="reset()" >
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>