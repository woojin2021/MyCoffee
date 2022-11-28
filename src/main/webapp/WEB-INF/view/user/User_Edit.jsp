<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보 수정</title>
<script type="text/javascript">
function check()
{
    let form ="SignUp";

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
			<div class ="col-md-6" style="margin-left:0px;">
				<img src="/resources/img/Coffee1.jpg" style="max-width:100%;">
			</div>
			<div class="col-md-6">
				<h3 class="form-signin-heading">Please sign in</h3>
				<form role="form" name = "SignUp" class="form-horizontal" action="/user/useredit" method="post" onsubmit="return check()">
					<div class="form-group row">
						<label class="col-md-4" align="left">*아이디 : </label>
						<div class="col-md-6"><input type="text" name="userid" id="userid" class="form-control" value='<c:out value="${sessionId.userid}"/>' readonly="readonly"></div>
					</div>
					<div class ="form-group row">
						<label class="col-md-4" align="left">*비밀번호 : </label>
						<div class="col-md-6"><input name ="password" id="password" type="password" class="form-control" value='<c:out value="${sessionId.password}"/>' readonly="readonly"></div>
					</div>
		
					<div class ="form-group row">
						<label class="col-md-4" align="left">*성명 : </label>
						<div class="col-md-6"><input name ="name" id="name" type="text" class="form-control" value='<c:out value="${sessionId.name}"/>' readonly="readonly"></div>
					</div>
					<div class ="form-group row">
						<label class="col-md-4" align="left">*전화번호 : </label>
						<div class="col-md-6"><input type="text" name ="mobile"class="form-control" value='<c:out value="${sessionId.mobile}"/>'></div>
					</div>
					<div class ="form-group row">
						<label class="col-md-4" align="left">*주소 : </label>
						<div class="col-md-6"><input type="text" name ="address"class="form-control" value='<c:out value="${sessionId.address}"/>'></div>
					</div>
					<div class="form-group  row">
						<div class="col-sm-offset-2 col-sm-10 ">
							<input type="submit" class="btn btn-primary " value="회원 정보 수정" > 
							<input type="button" class="btn btn-light "   value="취소"  onclick="location.href=/user/User_Main_Home'">
							<a href="user/userdelete" class="btn btn-danger">회원탈퇴</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>