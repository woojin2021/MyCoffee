
<%@page import="java.util.List"%>
<%@page import="org.springframework.web.bind.annotation.ModelAttribute"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%@ page import="org.springframework.ui.Model"%>
<%@ page import="com.mycoffee.domain.ProductJoinVO"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.js" 
	integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" 
	crossorigin="anonymous"></script>
<style>
th {
	width: 150px;
}

td {
	text-align: left;
}

.td-divide {
	border-left: 1px dotted rgb(222, 226, 230);
}
</style>
<script>
$(document).ready(function(){
	if (${product.ptype == 1}) {return;}

	const details = JSON.parse('${product.details}');
	console.log(details);
	const options = $("#options");
	let hot = 0;
	let cold = 0;
	details.forEach(data => {
		if (data.temperature == 0)
			cold++;
		if (data.temperature == 1)
			hot++;
	});
	if (hot > 0)
		options.append($('<span class="btn btn-danger mr-3">HOT</span>'))

	if (cold > 0)
		options.append($('<span class="btn btn-primary">ICE</span>'))

});
</script>
</head>
<body>
	<%@ include file="../user/User_menu.jsp"%>
	<div class="container shadow-sm p-3 mb-5 bg-body rounded" align="center">
	
		<div class="container row">
			<div class="offset-md-2 col-md-8" style="border:1px solid;background-color:orange;">
				<h3 class="form-signin-heading">상세 메뉴 설명</h3>
			</div>
		</div>
		
		<div class="container row">
			<div class="col-md-5 py-5">
				<img style="width:100%; height:100%;" src="/resources/img/${product.imagefile}">
			</div>
			<div class="col-md-6" style="margin: 0px; padding: 0px;">
				<table class="table" id="dataTable">
					<tr>
						<td colspan="4" style="border-top-color: #FFF">
							<h5><strong><i>${product.pname}</i></strong></h5>
						</td>
					</tr>
					<tr>
						<td colspan="4">${product.description}</td>
					</tr>
					<tr>
						<td colspan="2">제품 영양 정보</td>
						<td colspan="2" class="text-right">${product.ptype == 0 ? 'Tall(톨) / 355ml 기준':'100g 기준' }</td>
					</tr>
					<tr style="border-bottom-color: #FFF">
						<td>칼로리(kcal)</td>
						<td class="text-right">${product.calorie}</td>
						<td class="td-divide">나트륨(mg)</td>
						<td class="text-right">${product.sodium}</td>
					</tr>
					<tr style="border-bottom-color: #FFF">
						<td>포화지방(g)</td>
						<td class="text-right">${product.fat}</td>
						<td class="td-divide">당류(g)</td>
						<td class="text-right">${product.sugars}</td>
					</tr>
					<tr style="border-bottom-color: #FFF">
						<td>단백질(g)</td>
						<td class="text-right">${product.protein}</td>
						<td class="td-divide">커페인(mg)</td>
						<td class="text-right">${product.caffeine}</td>
					</tr>
					<tr style="border-bottom-color: #FFF">
						<td colspan="4" id="options" style="text-align: center; padding-top: 20px;">
							<%-- <c:if test="${product.plist.stream().filter(p-> p.temperature == 0).count() > 0}">
								<span class="btn btn-danger">HOT</span>
							</c:if>
							<c:if test="${product.plist.stream().filter(p-> p.temperature == 1).count() > 0}">
								<span class="btn btn-primary">ICE</span>
							</c:if> --%>
						</td>
					</tr>
				</table>
			</div>
		</div>

		<div class="container row">
			<div class="d-flex justify-content-center">
				<input type="button" class="btn btn-outline-primary" value="상세 주문"
					onclick="location.href='/menu/User_Drink_Order?category=${product.pcategory}'">
				<input type="button" class="btn btn-outline-warning ml-5" value="목록"
					onclick="location.href='/menu/User_Drink_Menu'">
			</div>
		</div>
	</div>

</body>
</html>