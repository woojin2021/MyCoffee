<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="../../resources/css/bootstrap.css" rel="stylesheet" />
<link href="../../resources/css/join.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<!-- Javascript -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

function check()
{
	let form = "newMember";
	
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
	
	if (!document.forms[form].elements["name"].value) 
	{
		alert("이름을 입력하세요.");
		return false;
	}
	
	if(!document.forms[form].elements["password"].value !=
		document.forms[form].elements["password_confirm"].value){
		
		alert("비밀번호가 다릅니다.");
		return false;
	}
}
</script>
<title>Sign-Up</title>

</head>
<body>


	<div class="table-success">
		<div class="container">
			<h3 class="display-3" align="center">Sign up</h3>
		</div>
	</div>

	<div class="container">
		<form role="form" name="newMember" class="form-horizontal" method="post" 
		onsubmit="return check()" >
			<div class="form-group  row">
				<div class="col-sm-2">아이디</div>
				<div class="col-sm-3">
					<input name="did" id="did" type="text" class="form-control" placeholder="ID" >
				</div>
			</div>
			
			<div class="form-group  row">
				<div class="col-sm-2">비밀번호</div>
				<div class="col-sm-3">
					<input name="password" id="password" type="text" class="form-control" placeholder="password" >
				</div>
			</div>
			
			<div class="form-group  row">
				<div class="col-sm-2">비밀번호 확인</div>
				<div class="col-sm-3">
					<input name="password_confirm" id="password_confirm" type="text" class="form-control" placeholder="password confirm" >
				</div>
			</div>
			
			<div class="form-group  row">
				<div class="col-sm-2">이름</div>
				<div class="col-sm-3">
					<input name="name" id="name" type="text" class="form-control" placeholder="name" >
				</div>
			</div>
			
			<div class="form-group  row">
				<div class="col-sm-2">휴대폰</div>
				<div class="col-sm-3">
					<input name="mobile" id="mobile" type="text" class="form-control" placeholder="phone" >
				</div>
			</div>
			
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 " align="center">
					<input type="submit" class="btn btn-primary" value="가입하기">
					<input type="reset" class="btn btn-primary disabled" 
						value="취소" onclick="reset()">
				</div>
			</div>
			
		</form>
		
	</div>

</body>
</html>