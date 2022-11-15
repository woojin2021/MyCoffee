<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page import =  "com.mycoffee.domain.OrderVO" %>
<%@ page import =  "com.mycoffee.domain.Order_detailVO" %>
<%@ page import =  "com.mycoffee.domain.ProductJoinVO" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%
	List<OrderVO> order2=null;
	List<Order_detailVO> od2=null;
	List<ProductJoinVO> product= null;
	if(request.getAttribute("order2") != null)
	{
		order2 = (List)request.getAttribute("order2");
		od2=(List)request.getAttribute("od2");
		product =(List)request.getAttribute("product");
	}
%>
</head>
<body>
<%@ include file="./User_menu.jsp" %>
<div class="container">
<div class="col-md-6"></div>
<div class="col-md-12">
	<div class="card" background-color:#" align="center">
  		<div class="card-header">
   			 나의 장바구니
  		</div>
  		<div class="row">
  		<%  if(order2 !=null)
			{ 
		%>
  		<div class="col-md-8"align="left"><label >주문 번호 : <%= order2.get(0).getOid() %></label></div>
  		<div class="col-md-4" align="right">주문상태 :  <%= order2.get(0).getStatus() %></div>
  		</div>
  		<div class="row" style="border-top:1px solid;padding:0px;margin:0px;">
  		<div class="col-md-8" align="left"><label>회원 아이디 :  <%= order2.get(0).getUserid() %></label></label></div>
  		<div class="col-md-4" align="right">주문 시작 시간 :  <%= order2.get(0).getRegistdate() %></div> 
  		</div>
  		
  		<%	
  			int index=0;
  			for(int i=0; i<product.size();i++)
  			{	
  		%>
  		<div class="card-body" style="border-top:1px solid" align="left">
  				메뉴명 : <%=product.get(i).getPname()%>
  				선택용량 : <%=product.get(i).getCapacity() %> (ml)
  				냉/온 : 
  				<% if(product.get(i).getTemperature()==0) 
  				{
  					%>
  						ICE
  					<%
  				}
  				else
  				{
  					%>
						HOT
					<%
  				}
  				%>
  				단일가격 : <%=product.get(i).getPrice() %> (원)
  				갯수 : 
  				<% 
  				for(int j=0; j<od2.size();j++)
  				{
  						if(product.get(i).getPid().equals(od2.get(j).getPid()))
  						{
  				%>
  						<%=od2.get(j).getPieces() %>
  				<% 
  							index=j;
  							break;
  						}
  				}
  				%>
  				<input type="button" class="btn btn-danger" value="  -  " onclick="location.href='/user/piecesChange?str=minus&category=<%=product.get(i).getPcategory()%>&tem=<%=product.get(i).getTemperature()%>&cap=<%=product.get(i).getCapacity()%>&pid=<%=od2.get(index).getPid()%>'">
  				<input type="button" class="btn btn-primary" value="  +  " onclick="location.href='/user/piecesChange?str=plus&category=<%=product.get(i).getPcategory()%>&tem=<%=product.get(i).getTemperature()%>&cap=<%=product.get(i).getCapacity()%>&pid=<%=od2.get(index).getPid()%>'">
  		</div>
  		<%
  				if(product.size() == i)
				{break;}
  			}
  		%>
  		<div align="right" style="border-top:1px solid">
  			구매 총액 : <%= order2.get(0).getTotalprice() %> 원 입니다.
  		</div>
  		<div class="row" style="border-top:1px solid;padding:0px;margin:0px;">
  			<input type="button" class="btn btn-primary" value="주문하기" onclick="location.href='/user/Last_Order'">
  			<input type="button" class="btn btn-danger" value="취소하기"  onclick="location.href='/user/Order_0_Cancel'">
  		</div>
  		<%} %>
	</div>
</body>
</html>