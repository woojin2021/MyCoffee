<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="../../resources/css/bootstrap.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>
	
	<div class="jumbotron">
		
		<div class="jumbotron">
			<div class="container">
				<h1 class="display-3" align="center">Welcome <br> MyCoffee Rider</h1>
			</div>
		</div>
		
		<div class="container" align="center">
			<div class="col-md-4 col-md-offset-4">
				<form id="login_form" action="/driver/login" method="post">
					<div class="form-group">
						<label for="inputID" class="sr-only">아이디</label> 
						<input	type="text" class="form-control" name="did" placeholder="ID" required autofocus>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="sr-only">비밀번호</label> 
						<input 	type="password" class="form-control" name="password" placeholder="Password" required>
					</div>
					<div class="login_button_wrap">
						<input type = "submit" class="login_button" value="로그인">
						<a href="member/join">신규 등록</a>
					</div>
				</form>
			</div>
		</div>

	</div>

</body>
</html>