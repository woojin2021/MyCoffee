<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<meta charset="utf-8">
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<style type="text/css">
.text-ellipsis {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	text-align: left;
	text-indent: 0.5em;
}
.text-ellipsis:hover {
	overflow: visible;
	height: 80px;
	white-space: normal; 
}
</style>
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
	function getProductList(item, category) {
		const tab = $(item);
		console.log(tab);
		// activate tab
		$(".nav-link").each(function(){ this.classList.remove("active"); });
		tab.find(".nav-link").addClass("active");
		
		$.get("/menu/product/list/" + category, function(result) {
			let buffer = '';
			//console.log(result);
			for (let i = 0; i < result.length; i++) {
				buffer += '<div class="col-md-3" style="min-width: 250px;">';
				//buffer += '<div class ="col mt-2">';
				buffer += '<a href="/menu/User_One_Drink?category=' + result[i].pcategory + '"><img src="/resources/img/' + result[i].imagefile + '" style="width:100%;" align="center"></a>';
				buffer += '<div>';
				buffer += '<h5 class="mt-2">' + result[i].pname + '</h5>';
				buffer += '<p class="text-ellipsis">' + result[i].description + '<p>';
				//buffer += '</div>';
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
	<%@ include file="../user/User_menu.jsp"%>
	
	<div class="container mt-5">
		<div class="card text-center">
			<div class="card-header">
				<ul class="nav nav-tabs card-header-tabs" id="categoryLiist">
					<c:forEach var="productType" items="${ptypeList}"
						varStatus="status">
						<li class="nav-item" onclick="getProductList(this, '${productType.code}');">
							<a class="nav-link ${status.index == 0 ? 'active' : ''}" href="#">${productType.disp}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="card-body">
				<!-- <div class="col-md-10"> -->
					<div class="row" id="productList">
						<!-- 텝(카테고리)를 클릭하면 갱신 -->
						<div class="col-sm-2" style="width:250px;height:350px;border:1px solid;margin-left:10px;margin-right:10px;" >
							<div class ="col" style="margin-top:10px;">
								<a href="/menu/User_One_Drink?category=x"><img src="/resources/img/empty.jpg"></a>
								<div class="overflow-hidden">
									<p style="line-height:1">【카테고리명】<p>
									<p style="line-height:1">【설명】<p>
								</div>
							</div>
						</div>
					</div>
				<!-- </div> -->
			</div>
		</div>
	</div>

</body>
</html>