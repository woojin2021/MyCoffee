<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<title>나의 장바구니</title>
</head>
<body>
	<%@ include file="./User_menu.jsp"%>
	
	<div class="container">
		<div class="col-md-12">
			<div class="card" align="center">
				<div class="card-header">나의 장바구니</div>
				<c:if test="${!empty orderData}">
				
				<div class="row">
					<div class="col-md-8" align="left"><label>주문 번호 : ${orderData.oid}</label></div>
					<div class="col-md-4" align="right">주문상태 : ${orderData.status}</div>
				</div>

				<div class="row"
					style="border-top: 1px solid; padding: 0px; margin: 0px;">
					<div class="col-md-8" align="left"><label>회원 아이디 : ${orderData.userid}</label></div>
					<div class="col-md-4" align="right">주문 시작 시간 : ${orderData.registdate}</div>
				</div>

				<c:forEach var="detail" items="${orderData.details}">
					<div class="card-body" style="border-top: 1px solid" align="left">
						메뉴명 : ${detail.pname} 
						선택용량 : ${detail.capacityDisp} (${detail.capacity}ml) 
						냉/온 : ${detail.temperatureDisp}
						단일가격 : ${detail.price} (원) 
						갯수 : ${detail.pieces}
						<input type="button" class="btn btn-danger" value="  -  "
							onclick="location.href='/user/piecesChange?oper=minus&pid=${detail.pid}'">
						<input type="button" class="btn btn-primary" value="  +  "
							onclick="location.href='/user/piecesChange?oper=plus&pid=${detail.pid}'">
					</div>
				</c:forEach>
				<div align="right" style="border-top: 1px solid">구매 총액 : ${orderData.totalprice}원 입니다.</div>
				<div class="row" style="border-top: 1px solid; padding: 0px; margin: 0px;">
					<input type="button" class="btn btn-primary" value="주문하기" onclick="location.href='/user/Last_Order'">
					<input type="button" class="btn btn-danger" value="취소하기" onclick="location.href='/user/Order_0_Cancel'">
				</div>

		  		</c:if>
			</div>
		</div>
	</div>
</body>
</html>