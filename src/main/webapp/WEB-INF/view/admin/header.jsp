<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%
java.util.Date nowDate = new java.util.Date();
java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy년 MM월 dd일");
%>

<script type="text/javascript">

$(document).ready(function(){
	// 카페 영업표시 ON/OFF
	const cafeopened = $("#cafeopened");
    $.get("/admin/cafe/isopened", function(result) {
    	setCafeOpened(result);
    });
	cafeopened.on("click", function(e){
    	//console.log($(this).is(":checked"));
    	let uri = "/admin/cafe/close";
		if($(this).is(":checked")) {
    		uri = "/admin/cafe/open";
		}
        $.get(uri, function(result) {
        	setCafeOpened(result);
        });
	});
    function setCafeOpened(result) {
    	cafeopened.prop("checked", result); 
    	//console.log($(this).is(":checked"));
    }
	
	let cnt = 0;       
	const interval = 3000; //3s
	const timer = setInterval(function(){
 	    cnt++;
	    if(cnt == 2){
	        console.log("디버깅을 위해 1회실시후 종료 합니다.",cnt);
	        clearInterval(timer);
	    }
	    
	    // 대기중 주문수
	    $.get("/admin/order/waiting", function(result) {
			$("#waitingOrder").text(result);
	    }).fail(function(xhr, status, err) {
	    	ajaxError(xhr);
	    });
	    
	    // 운행가능 배달원수
	    $.get("/admin/driver/free", function(result) {
	    	$("#freeDriver").text(result);
	    }).fail(function(xhr, status, err) {
	    	ajaxError(xhr);
	    });
	    
	}, interval);
	// 주문 목록
	$("#btnOrderList").on("click", function(e){
		location.href = "/admin/order/list";
	});
	// 로그 아웃
	$("#btnLogout").on("click", function(e){
		if (cafeopened.is(":checked")) {
			const msg = "아직 카페가 영업중입니다.\n케페를 종료하고 로그아웃하시겠습니까?";
			if (confirm(msg)) {
				cafeopened.click();
			} else {
				return;
			}
		}
		location.href = "/admin/logout";
	});

	function ajaxError(xhr) {
		console.log(xhr);
		if (xhr.status == 406)
			clearInterval(timer);
	}
	
});

</script>

<nav class="navbar navbar-expand-lg"
	style="background-color: rgb(166, 64, 64);">
	<div class="container-fluid">

		<div class="d-flex align-items-baseline">
			<div>
				<h1>MyCoffee</h1>
			</div>
			<div class="p-2">
				<h4><%=sf.format(nowDate)%></h4>
			</div>
		</div>

		<div>
			<a href="/admin/product/list" class="btn btn-primary position-relative me-5">
				상품목록
			</a>
			<!-- <button data-oper="productlist" class="btn btn-primary">상품목록</button> -->
			<button type="button" class="btn btn-primary position-relative me-5" id="btnOrderList">
				주문목록 
				<span id="waitingOrder" class="position-absolute top-50 start-100 translate-middle badge rounded-pill bg-danger">
					0 </span>
			</button>
			<button type="button" class="btn btn-primary position-relative">
				배달원
				<span id="freeDriver" class="position-absolute top-50 start-100 translate-middle badge rounded-pill bg-danger">
					0 </span>
			</button>
		</div>

		<!-- <div class="form-check form-switch">
			<input id="cafeOpened" class="form-check-input" type="checkbox" role="switch" data-size="lg">
			<h5>
				<label id="cafeOpenedDisp" class="form-check-label" for="cafeOpened">영업중</label>
			</h5>
		</div> -->
		<div class="d-flex align-items-center">
			<button id="btnLogout" type="button" class="btn btn-warning mr-3">로그아웃</button>
			<input id="cafeopened" class="switch-toggle" type="checkbox" checked>
		</div>
	</div>
</nav>
