
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
</style>
<script>
let selectedTemperature = -1;
let selectedCapacity = -1;
const details = JSON.parse('${product.details}');

$(document).ready(function(){

	console.log(details);
	const option1 = $("#option1");
	const option2 = $("#option2");
	const capacities = new Array();
	let hot = 0;
	let cold = 0;
	details.forEach(data => {
		if (data.temperature == 0)
			cold++;
		if (data.temperature == 1)
			hot++;
		let chk = false;
		capacities.forEach(c => {
			chk = chk | (c.capacity == data.capacity);
		});
		if (chk == false) {
			const cap = new Object();
			cap.capacity = data.capacity;
			cap.capacityDisp = data.capacityDisp;
			capacities.push(cap);
		}
	});
	if (hot > 0) {
		option1.append($('<span class="btn btn-outline-danger mr-3" data-tmpr="1">HOT</span>'))
	}
	if (cold > 0) {
		option1.append($('<span class="btn btn-outline-primary" data-tmpr="0">ICE</span>'))
	}
	console.log(capacities);
	capacities.forEach(c => {
		option2.append($('<span class="btn btn-sm mr-1 btn-outline-secondary" data-capt="' + c.capacity + '">' + c.capacityDisp + '</span>'))
	});
	
	$("[data-tmpr]").on("click", function(e){
		const temperature = this.getAttribute("data-tmpr");
		if (selectedTemperature == temperature)
			return;
		selectedTemperature = temperature;
		selectedCapacity = -1;
		const tmprs = $("[data-tmpr]");
		for(let i = 0; i < tmprs.length; i++) {
			let cls = tmprs[i].getAttribute("class");
			if (tmprs[i] == this) {
				cls = cls.replace(/btn-outline-/i, 'btn-');
			} else if (cls.indexOf("btn-outline-") < 0){
				cls = cls.replace(/btn-/i, 'btn-outline-');
			}
			tmprs[i].setAttribute("class", cls);
		}
		$("[data-capt]").each(function() {
			const opt2 = $(this);
			const capt = opt2.data("capt");
			opt2.toggleClass();
			opt2.addClass(["btn", "btn-outline-secondary", "btn-sm", "mr-1"]);
			opt2.prop("disabled", true);
			console.log();
			details.forEach(d => {
				if (d.temperature == selectedTemperature && d.capacity == capt) {
					opt2.removeClass("btn-outline-secondary");
					opt2.addClass("btn-outline-info");
					opt2.prop("disabled", false);
				};
			});
		});
	});
	
	$("[data-capt]").on("click", function(e){
		if (this["disabled"]){return;}
		const capacity = this.getAttribute("data-capt");
		if (selectedCapacity == capacity)
			return;
		selectedCapacity = capacity;
		const capts = $("[data-capt]");
		for(let i = 0; i < capts.length; i++) {
			let capt = $(capts[i]);
			if (capt.prop("disabled")) {continue;}
			let cls = capts[i].getAttribute("class");
			if (capts[i] == this) {
				cls = cls.replace(/btn-outline-info/i, 'btn-info');
			} else {
				cls = cls.replace(/btn-info/i, 'btn-outline-info');
			}
			capts[i].setAttribute("class", cls);
		}
	});
	
});

function checkInput() {
	if (selectedTemperature < 0 || selectedCapacity < 0) {
		alert("상품을 선택해 주세요!");
		return false;
	}
	details.forEach(d => {
		if (d.temperature == selectedTemperature && d.capacity == selectedCapacity) {
			$("#pid").val(d.pid);
		}
	});
}
</script>	
</head>
<body>
	<%@ include file="../user/User_menu.jsp"%>
	<div class="card shadow-sm p-3 mb-5 bg-body rounded" " align="center">
		<div class="container row">
			<div class="offset-md-2 col-md-8" style="border: 1px solid; background-color: orange;">
				<h3 class="form-signin-heading">상세 메뉴 구매</h3>
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
						<td colspan="2" class="text-right">Tall(톨) / 355ml 기준</td>
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
					<tr></tr>
					<tr>
						<td>선택1</td>
						<td colspan="3" id="option1"></td>
					</tr>
					<tr>
						<td>선택2</td>
						<td colspan="3" id="option2"></td>
					</tr>
				</table>
			</div>
		</div>

		<div class="container row">
			<div class="d-flex justify-content-center">
				<form action="/user/InsertOrder" method="post">
					<input type="hidden" id="pid" name="pid">
					<input type="submit" class="btn btn-outline-primary " value="장바구니에 담기" onclick="return checkInput();">
					<input type="button" class="btn btn-outline-danger " value="취소" onclick="location.href='/menu/User_Drink_Menu'">
				</form>
			</div>
		</div>

	</div>
</body>
</html>