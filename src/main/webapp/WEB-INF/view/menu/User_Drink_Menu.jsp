<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<link rel ="stylesheet" href ="../resources/css/bootstrap.min.css"/>
<meta charset="utf-8">
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
/* 	function linkActive()
	{
		let e = document.getElementById('navli');
		if(e.Name==='2')
		if(e.className==='nav-link active')
			{e.className='nav-link';}
		else if(e.className==='nav-link')
			{e.className='nav-link active';}
		https://kimvampa.tistory.com/252
	} */
	
	$(document).ready(function(){
		// 첫번째 카테고리의 상품을 표시
		$("#categoryLiist>li:first-of-type").click();
	});
		
	// 선택한 카테고리의 상품 목록 표시
	function getProductList(category) {
		$.get("/menu/product/list/" + category, function(result) {
			let buffer = '';
			//console.log(result);
			for (let i = 0; i < result.length; i++) {
				buffer += '<div class="col-sm-3" style="width:250px;height:350px;border:1px solid;margin-left:10px;margin-right:10px;" >';
				buffer += '<div class ="col" style="margin-top:10px;">';
				buffer += '<a href="/menu/User_One_Drink?category=' + result[i].pcategory + '"><img src="/resources/img/' + result[i].imagefile + '" style="width:200px; height:250px" align="center"></a>';
				buffer += '<div>';
				buffer += '<p style="line-height:1">' + result[i].pname + '<p>';
				buffer += '<p style="line-height:1">설명 : ' + result[i].description + '<p>';
				buffer += '</div>';
				buffer += '</div>';
				buffer += '</div>';
			}
			//console.log(buffer);
			$("#productList").html(buffer);
		}).fail(function(xhr, status, err) {
			console.log(xhr);
	    });
	}
</script>

<title>Drink Menu</title>
</head>
<body>
<%@ include file="../user/User_menu.jsp" %>
<div class="container">
<div class="card text-center">
  <div class="card-header" style="background-color:brown;">
    <ul class="nav nav-tabs card-header-tabs" id="categoryLiist">
      <!-- <li class="nav-item">
        <a class="nav-link active" name="1"id="navli" onclick="linkActive()"href="#" >Coffee</a>
      </li>
      <li class="nav-item">
        <a class="nav-link"  name="2" id="navli" onclick="linkActive()" href="#">Latte</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" name="3" id="navli" onclick="linkActive()" href="#">Ade</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" name="4" id="navli" onclick="linkActive()" href="#">Smoothi</a>
      </li>-->
  	<c:forEach var="productType" items="${ptypeList}" varStatus="status">
  	<li class="nav-item" onclick="getProductList('${productType.code}');">
  	  <a class="nav-link ${status.index == 0 ? 'active' : ''}" href="#" >${productType.disp}</a>
  	</li>
  	</c:forEach>
  	</ul>
  </div>
  <div class="card-body">
    <div class= "col-md-10">
		<div class = "row row-cols-3" id="productList">
			<!-- 텝(카테고리)를 클릭하면 갱신 -->
		</div>
	</div>
  </div>
</div>
</div>

<div class="container">
<div class="card text-center">
  <div class="card-header" style="background-color:brown;">
  <ul class="nav nav-tabs card-header-tabs">
  	<!-- <li class="nav-item">
  		<input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
  		<label class="btn btn-outline-primary" for="btnradio1">Coffee</label>
  	</li>
  	<li class="nav-item">
  		<input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off" onclick()="acyncMovePage(/user/User_Main_Home)">
  		<label class="btn btn-outline-primary" for="btnradio2">Latte</label>
 	</li>
  	<li class="nav-item">
  		<input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off">
  		<label class="btn btn-outline-primary" for="btnradio3">Ade</label>
  	</li>
  	<li class="nav-item">
  		<input type="radio" class="btn-check" name="btnradio" id="btnradio4" autocomplete="off">
  		<label class="btn btn-outline-primary" for="btnradio4">Smoothi</label>
  	</li> -->
  	<c:forEach var="productType" items="${ptypeList}">
  	<li class="nav-item" onclick="location.href='/menu/User_Drink_Menu?ptype=${productType.code}'">
  		<input type="radio" class="btn-check" name="btnradio" value="${productType.code}" ${productType.code == ptype ? "checked" : ""}>
  		<label class="btn btn-outline-primary" for="btnradio4">${productType.disp}</label>
  	</li>
  	</c:forEach>
  </ul>
  </div>
  <div class="card-body" id="content1">
    <div class= "col-md-10">
		<div class = "row row-cols-3">
			<c:forEach items="${list}" var="product">
				<div class="col-sm-4" style="width:250px;height:350px;border:1px solid;margin-left:10px;margin-right:10px;" >
					<div class ="col" style="margin-top:10px;">
						<a href="/menu/User_One_Drink?category=<c:out value="${product.pcategory}" />"><img src="../resources/img/<c:out value="${product.imagefile}"/>" style="width:200px; height:250px" align="center"></a>
						<div>
							<p style="line-height:1"><c:out value="${product.pcategory}" /><p>
							<p style="line-height:1">설명 : <c:out value="${product.description}"/><p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
  </div>
</div>
</div>
</body>
</html>