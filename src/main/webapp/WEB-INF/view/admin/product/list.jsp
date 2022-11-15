<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<%@ include file="../refer.jsp"%>
<script type="text/javascript">
	
</script>
</head>
<body>

	<%@ include file="../header.jsp"%>

	<div class="d-flex flex-row-reverse p-3" style="position: sticky; top: 10px;">
		<button class="btn btn-success" onclick="location.href='/admin/product/register'">상품 추가</button>
	</div>
	
	<div class="container mt-4">
		<h4>Drink</h4>
		<hr>
		<div class="row">
			<c:forEach var="drink" items="${drinkList}">
				<div class="card col-md-2" style="width: 13rem;"
					onclick="location.href='/admin/product/${drink.pcategory}'">
					<img src="/resources/img/${drink.imagefile==null?'empty.jpg':drink.imagefile}" class="card-img-top">
					<div class="card-body">
						<h5 class="card-title">${drink.pname}</h5>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="container mt-4">
		<h4>Food</h4>
		<hr>
		<div class="row">
			<c:forEach var="food" items="${foodList}">
				<div class="card col-md-2" style="width: 13rem;"
					onclick="location.href='/admin/product/${food.pcategory}'">
					<img src="/resources/img/${food.imagefile==null?'empty.jpg':food.imagefile}" class="card-img-top">
					<div class="card-body">
						<h5 class="card-title">${food.pname}</h5>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>