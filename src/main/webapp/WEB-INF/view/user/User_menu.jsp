<%@ page contentType ="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel ="stylesheet" href ="../resources/css/bootstrap.min.css"/>
<!-- 부트스트랩 5 - 드롭다운 사용하기 위해 추가/나중에 헤더에 집합시키기 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<%
	HttpSession sessionId = request.getSession(false);//있으면 받고 없으면 별도 생성하지 않기.
	System.out.println(sessionId);
%>

<nav class="navbar navbar-expand-lg" style="background-color:white;">
  <div class="container-fluid">
   <img src="../resources/img/mycoffeelogo.png" style="width:250px; height:70px;margin-top:10px;margin-bottom:10px;">
    <div class="collapse navbar-collapse" id="navbarColor01">
      <ul class="navbar-nav ml-auto">
     <c:choose>
      	<c:when test="${empty sessionId}">
        <li class="nav-item">
          <a class="nav-link active" href="./User_Login"><span><font style="vertical-align: inherit;"><font style="vertical-align: inherit; color:black;">로그인</font></font></span></a>
        </li>
        <li class="nav-item">
         		 <a class="nav-link" href="./User_SignUp"><font style="vertical-align: inherit;"><font style="vertical-align: inherit; color:black;">회원가입</font></font></a>
       	</li>
       </c:when>
        <c:otherwise>
			<li class="nav-item" >
       			<input class="form-control" name="name" value='<c:out value="${sessionId.name}"/>' readonly="readonly">
       		</li>
         	<li class="nav-item">
          	<a class="nav-link active" href="/user/logout"><font style="vertical-align: inherit;"><font style="vertical-align: inherit; color:black;">로그아웃</font></font></span></a>
        	</li>
        </c:otherwise>
        </c:choose> 
        	<li class="nav-item">
          		<a class="nav-link active" href="/user/User_Drink_Menu"><font style="vertical-align: inherit;"><font style="vertical-align: inherit; color:black;">목록</font></font></a>
        	</li>
 		 	<li class="nav-item">
          		<a class="nav-link" href="/user/CheckSession?str=User_Shopping_Basket">
          		<font style="vertical-align: inherit;"><font style="vertical-align: inherit; color:black;">장바구니</font></font></a>
        	</li>
		<c:choose>
      		<c:when test="${empty sessionId}"></c:when>
        	<c:otherwise>
        		<div class="dropdown">
  					<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false"> 마이 페이지</button>
  					<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
    					<li><a class="dropdown-item" href="/user/CheckSession?str=User_Order_History">주문 내역 보기</a></li>
    					<li><a class="dropdown-item" href="/user/CheckSession?str=User_Edit">회원 정보 수정</a></li>
  					</ul>
				</div>
		  </c:otherwise>
        </c:choose> 
      </ul>
    </div>
  </div>
</nav>
<div class="container-fluid" style="background-color:black;height:10px;padding:0px;margin:0px;">
</div>
<div  class="jumbotron" style="width:100%;height:300px;background-color:orange;padding:0px;margin:0px;" align="center">
	<!-- <img src="../resources/img/My-Coffee.jpg" style="width:75%; height:150px;padding-top:0px;margin-top:0px;margin-bottom:0px;" > -->
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
</div>