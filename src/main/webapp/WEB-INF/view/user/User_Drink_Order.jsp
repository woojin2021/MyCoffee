
<%@page import="java.util.List"%>
<%@page import="org.springframework.web.bind.annotation.ModelAttribute"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page import = "org.springframework.ui.Model" %>
<%@ page import ="com.mycoffee.domain.ProductJoinVO" %>
<!DOCTYPE html>
<html>
<head>
<%
	List<ProductJoinVO> product =(List)request.getAttribute("list");
	int tem= 0;
	int cap= 237;
%>
<style>
	th{width:150px;}
	td{text-align:left;}
</style>
<meta charset="utf-8">
</head>
<body>
<%@ include file="./User_menu.jsp" %>
<div class="card shadow-sm p-3 mb-5 bg-body rounded"" align="center">
		<div class="container row"  style="border:1px solid;width:100%;margin-right:20%;margin-left:20%;padding:0px;">
			<div class="col-md-12" style="border:1px solid;width:100%;margin:0px;padding:0px;background-color:orange;">
				<h3 class="form-signin-heading">상세 메뉴 구매</h3>
			</div>
			<div class ="col-md-8" style="width:350px;height:500px;border:1px solid;margin-top: 18px;border-right:none;border-left:none;">
			<img src="../resources/img/<c:out value="${product.imagefile}" />" style="width:250px; height:375px;margin-top:10px;margin-bottom:10px;">
			</div>
			<div class="col-md-4"  style="margin:0px;padding:0px;">
				<div class="card-body" style="margin:0px;padding:0px;">
					<form name="order" class="form-horizontal" method="post" action="/user/User_Order?product=<%= product.get(0) %>&category=<%=product.get(0).getPcategory()%>">
					<div class="table-responsive" style="width:500px;padding:0px;margin:0px;">
					<table class="table" id="dataTable" width="100%" cellspacing="0">
                        	<thead>
                           		<tr align ="center" valign="middle">
                            		<th style="background-color: silver;">메뉴 명</th>
                           				 <td colspan = "3" span><h5><strong><i><%= product.get(0).getPname() %></i></strong></h5></td>
                            	</tr>
                            	<tr align ="center" valign="middle">
                            		<th style="background-color: silver;padding-top: 0px;" >
                            			<h6 align ="center" valign="middle" >설명</h6>
                            		</th>
                            			<td colspan = "3" span><%=product.get(0).getDescription() %></td>
                            	</tr>
                                  <tr align ="center" valign="middle">
									<p><strong><td colspan = "4" span style="background-color: silver;color:white;">제품 영양 정보[함량(100g 기준)]</td></strong></p>
    						    </tr>
    						    <tr align ="center" valign="middle">
                                	<th style="background-color:silver;">칼로리(열량)</th>
                                		<td><%= product.get(0).getCalorie() %> kcal</td>
                                </tr>     
                                <tr align ="center" valign="middle">
                                	<th style="background-color: silver;">지방</th>
                                		<td style="width:100px;"> <%= product.get(0).getFat() %> g</td>
                                	<th  style="background-color: silver;">당</th>
                                		<td> <%= product.get(0).getSugars() %>g</td>
                                </tr> 
                                <tr align ="center" valign="middle">
                                	<th style="background-color: silver;">나트륨</th>
                                		<td><%= product.get(0).getSodium() %> g</td>
                                	<th  style="background-color: silver;">카페인</th>
                                		<td style="width: 150px;"><%= product.get(0).getCaffeine()%> g</td>
                                </tr>
                                <tr align ="center" valign="middle">
										<p><td colspan = "4" span style="background-color: silver;color:white">제품 구매시 선택 항목</td></p>
								</tr>    
                                <tr align ="center" valign="middle">
                                	<th style="background-color: silver;">냉/온</th>
                                			<% 
                                			int index=0;
                                			int hot=0;
                                			int cold=0;
                                			int ttmp=3;
                                			String s_hot = "hot";
                                			String s_cold="ice";
                                			while(product.get(index) !=null)
                                			{
                                				if(product.get(index).getTemperature()==0)
                                				{cold =1;}
                                				else if(product.get(index).getTemperature()==1)
                                				{hot=1;}
                                				if(product.size() == index+1)
                                				{index =0;break;}
                                				index++;
                                			}
                                			if(cold ==1 && hot ==1){
                                			%>
                                				<td>
                                					<select name="tem">
													<option value="0" selected>cold</option>
													<option value="1" >hot</option>
													</select>
                                				</td>
                                			<%
                                			}
                                			else if(cold ==1){
                                				%>
                                				<td>
                                					<select name="tem">
													<option value="0" selected>cold</option>
													</select>
                                				</td>
                                			<%	
                                			}
                                			else if(hot ==1){
                                			%>
                                				<td>
                                					<select name="tem">
													<option value="1"  selected>hot</option>
													</select>
                                				</td>
                                			<%	
                                			}
                                			%>
                                </tr>
                                <tr align ="center" valign="middle">
                                	<th style="background-color: silver;">용량</th>
                                	<%
                                		int a=0;
                                		int start=1;
                                		int ctmp=0;
                                		boolean  shot =false;//237
                                		boolean tall = false;//355
                                		boolean grande = false;//473
                                		boolean venti = false;//591
                                		while(product.get(a) != null)
                                		{
                                			if(product.get(a).getCapacity()==237)
                                			{shot = true;}
                                			else if(product.get(a).getCapacity()==355)
                                			{tall= true;}
                                			else if(product.get(a).getCapacity()==437)
                                			{grande = true;}
                                			else if(product.get(a).getCapacity()==591)
                                			{venti = true;}
                                			if(product.size() == a+1)
                                			{a=0;break;}
                                			a++;
                                		}
                                	%>
                                	<td>
                                		<select id="cap" name ="cap" onchange="change()">
                                			<%	
                                			if(shot == true)
                                			{
                                			%>
												<option value="237" >(Short)237 ml</option>
                                			<% 
                                			}
                                			if(tall == true)
                                			{
                                			%>
												<option value="355" <c:if test ="${cap eq '355'}">selected="selected"</c:if>>(Tall) 355 ml</option>
                            				<% 
                                			}
                                			if(grande==true)
                                			{
                                			%>
												<option value="437">(Grande) 437 ml</option>
                            				<% 
                                			}
                                			%>
                                			<% 
                                			if(venti==true)
                                			{
                                			%>
												<option value="591">(Venti) 591 ml</option>
                            				<% 
                                			}
                                			%>
                                		</select>
                                	</td>
                                </tr>                              		
                                 <tr align ="center" valign="middle">
                                	<th style="background-color: silver;">가격</th>
                                		<td colspan = "3" span><%= product.get(0).getPrice() %></td>
                                </tr>
                           </thead>
                    </table>
                    </div>
					
               	 	<div class="form-group  row">
						<div class="col-sm-offset-2 col-sm-10 ">
							<input type="submit" class="btn btn-outline-primary " value="장바구니에 담기" > 
							<input type="button" class="btn btn-outline-danger "  value="취소"  onclick="location.href='/user/User_Drink_Menu'">
						</div>
					</div>
				</form>
                </div>
			</div>
		</div>
	</div>
</body>
</html>