<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page import =  "com.mycoffee.domain.OrderVO" %>
<%@ page import =  "com.mycoffee.domain.OrderDetailVO" %>
<%@ page import =  "com.mycoffee.domain.ProductJoinVO" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>나의 주문 내역</title>
</head>
<body>
<%@ include file="./User_menu.jsp" %>
<div class="container">
	<div class="col-md-6"></div>
	<div class="col-md-12">
	<div class="card" background-color:#" align="center">
  		<div class="card-header"> 나의 주문 내역</div>
  		
  		<c:forEach var="orderData" items="${orderHistory}">
  		<div class="row" style="border-top:1px solid;background-color:rgb(20,150,150);padding:0px;margin:0px;">
					<div class="col-md-8" align="left"><label>주문 번호 : ${orderData.oid}</label></div>
					<div class="col-md-4" align="right">주문상태 : ${orderData.statusDisp}</div>
  		</div>
  		<div class="row" style="border-top:1px solid;background-color:rgb(20,150,150);padding:0px;margin:0px;">
					<div class="col-md-8" align="left"><label>회원 아이디 : ${orderData.userid}</label></div>
					<div class="col-md-4" align="right">주문 시작 시간 : ${orderData.registdate}</div>
  		</div>
  		
		<c:forEach var="detail" items="${orderData.details}">
  		<div class="card-body" style="border-top:1px solid" align="left">
						메뉴명 : ${detail.pname} 선택용량 : ${detail.capacityDisp} (${detail.capacity}ml) 냉/온 : ${detail.temperatureDisp}
						단일가격 : ${detail.price} (원) 갯수 : ${detail.pieces}
  		</div>
		</c:forEach>
		
		<div align="right" style="border-top: 1px solid; border-bottom:2px solid;">구매 총액 : ${orderData.totalprice}원 입니다.</div>
		
		</c:forEach>
		
  	</div>
</div>
</div>
</body>
</html>