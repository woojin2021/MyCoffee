<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

$(document).ready(function(){
	// 배달원 영업표시
    //const startDriver = $("#startDriver");
	const onwork = $("#onwork");
    $.get("/driver/member/driverOrder/isstarted", function(result) {
    	setstartDriver(result);
    });
    /* startDriver.on("click", function(e){
    	let uri = "/driver/member/driverOrder/close";
    	if(startDriver.data("status") == "off") {
    		uri = "/driver/member/driverOrder/open";
    	}
        $.get(uri, function(result) {
        	setstartDriver(result);
        });
    }); */
    onwork.on("click", function(e){
    	let uri = "/driver/member/driverOrder/close";
    	if($(this).is(":checked")) {
    		uri = "/driver/member/driverOrder/open";
    	}
        $.get(uri, function(result) {
        	setstartDriver(result);
        });
    });
    function setstartDriver(result) {
    	/* startDriver.data("status", result ? "on" : "off");
    	startDriver.attr("class", result ? "btn btn-outline-danger" : "btn btn-outline-dark");
    	startDriver.text(result ? "근무중" : "근무안함"); */
    	onwork.prop("checked", result);
    };
    $("#userMenu").on("click", function(e){
    	const menuDiv = $(this).next();
    	//console.log(menuDiv.hasClass("show"));
    	if (menuDiv.hasClass("show")) {
    		menuDiv.removeClass("show");
    	} else {
    		menuDiv.addClass("show");
    	}
    });
});
</script>
<link rel="stylesheet" href="/resources/css/toggle-switch.css">
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">

		<a class="navbar-brand active" >${driver.name} 님</a>
		
<!-- 		<!-- onwork 버튼 기능	-->
<!-- 	  	<input type="checkbox" id="toggle" hidden>  -->
<!-- 		<label for="toggle" class="toggleSwitch"> -->
<!-- 		  	<span class="toggleButton"></span> -->
<!-- 		</label> -->
			
			<!-- <button id="startDriver" type="button" class="btn btn-outline-dark" 
				data-status="off">근무중</button> -->
		<input id="onwork" class="switch-toggle" type="checkbox" checked>
		
		<div>
		<span id="newOrder" class="badge badge-pill badge-danger">0</span>
		<button class="navbar-toggler" type="button" id="userMenu">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		<div class="navbar-collapse collapse" id="navbarColor01" style="text-align: right;">
			<ul class="navbar-nav"  style="">
				<li class="nav-item" active>
					<a class="nav-link" href="/driver/member/edit">회원정보</a>
				</li>
	      	
				<li class="nav-item">
					<a class="nav-link" href="/driver/logout.do">로그아웃</a>
				</li>
			</ul>
		</div>
  		</div>

  		
	</nav>

</body>