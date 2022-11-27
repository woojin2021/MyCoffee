<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<!-- 부트스트랩 5 - 드롭다운 사용하기 위해 추가/나중에 헤더에 집합시키기 -->
<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
<style>
.navbar-nav li a {
	color: black;
}
.menu-bg-color-1 {
	background-color: rgb(243, 156, 18);
}
.menu-bg-color-2 {
	background-color: rgb(241, 196, 125);
}
</style>
<nav class="navbar navbar-expand-md navbar-light menu-bg-color-1">
	<!-- <div class="container-fluid"> -->
		<img src="/resources/img/mycoffeelogo.png" style="width: 250px; height: 70px; margin-top: 10px; margin-bottom: 10px;">
		
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav ml-auto">
				<c:choose>
					<c:when test="${empty sessionScope.sessionId}">
						<li class="nav-item"><a class="nav-link active" href="/user/User_Login">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/User_SignUp"><font style="color: black;">회원가입</font></a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><span class="nav-link"><c:out value="${sessionScope.sessionId.name}"/></span></li>
					</c:otherwise>
				</c:choose>
				<li class="nav-item"><a class="nav-link active" href="/menu/User_Drink_Menu">목록</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/user/User_Shopping_Basket">장바구니</a></li>
				<c:choose>
					<c:when test="${empty sessionScope.sessionId}"></c:when>
					<c:otherwise>
						<div class="dropdown">
							<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-toggle="dropdown"aria-expanded="false">마이 페이지</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
								<li><a class="dropdown-item" href="/user/User_Order_History">주문 내역 보기</a></li>
								<li><a class="dropdown-item" href="/user/User_Edit">회원 정보 수정</a></li>
								<li><a class="dropdown-item" href="/user/logout">로그아웃</a></li>
							</ul>
						</div>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	<!-- </div> -->
</nav>
<!-- <div class="container-fluid" style="background-color:black;height:10px;padding:0px;margin:0px;">
</div>
<div  class="jumbotron" style="width:100%;height:300px;background-color:orange;padding:0px;margin:0px;" align="center">
	<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel"style="width:70%;height:300px;background-color:orange;padding:0px;margin:0px;"align="center">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="../resources/img/가나슈 케이크.jpg" style="width:50%;height:300px;"class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="../resources/img/치즈 케이크.jpg"style="width:50%;height:300px;"class="d-block w-100" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="../resources/img/밀크 케이크.jpg"style="width:50%;height:300px;"class="d-block w-100" class="d-block w-100" alt="...">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
</div> -->