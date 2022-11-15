<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%
	String msg= request.getParameter("msg");// == null ? "0" : request.getParameter("msg");
%>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

     $(function() {

        if(msg == 1)
    	 	alert("123123");
        else
        	alert("333333");

     });

</script>
</head>
<body>

<%@ include file="./User_menu.jsp" %>

<div class="card" align="center">
		<div class="container row">
			<div class ="col-md-8">
				<img src="../resources/img/Coffee1.jpg" style="width:400px; height:400px">
			</div>
			<div class="col-md-4">
			<h3 class="form-signin-heading">Please sign in</h3>
			<%
				String error = request.getParameter("error");
				if (error != null) {
					out.println("<div class='alert alert-danger'>");
					out.println("아이디와 비밀번호를 확인해 주세요");
					out.println("</div>");
				}
			%> 
			<form class="form-signin" role="form" action="/user/logincheck" method="post">
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