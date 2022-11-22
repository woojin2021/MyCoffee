<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(document).ready(function() {

		// 배달원 상태 init/click
		const onwork = $("#onwork");
		$.get("/driver/member/driverOrder/isstarted", function(result) {
			setstartDriver(result);
		});
		onwork.on("click", function(e) {
			let uri = "/driver/member/driverOrder/close";
			if ($(this).is(":checked")) {
				uri = "/driver/member/driverOrder/open";
			}
			$.get(uri, function(result) {
				setstartDriver(result);
			});
		});
		function setstartDriver(result) {
			onwork.prop("checked", result);
		};
		
		// 메뉴 click
		$("#userMenu").on("click", function(e) {
			//const menuDiv = $(this).next();
			const menuDiv = $($(this).data("target"));
			if (menuDiv.hasClass("show")) {
				menuDiv.removeClass("show");
			} else {
				menuDiv.addClass("show");
			}
		});
	});
</script>
<link rel="stylesheet" href="/resources/css/toggle-switch.css">
<style>
.navbar-toggler-copy {
	padding: 0.25rem 0.75rem;
	font-size: 1.25rem;
	line-height: 1;
	background-color: transparent;
	border: 1px solid rgba(255, 255, 255, 0.1);
	border-radius: 0.4rem;
}

.navbar-collapse-copy {
	-ms-flex-preferred-size: 100%;
	flex-basis: 100%;
	-ms-flex-positive: 1;
	flex-grow: 1;
	-ms-flex-align: center;
	align-items: center;
}
</style>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<!-- <div class="container-fluid"> -->
		<div class="container-fluid align-items-start">
			<a class="navbar-brand active">${driver.name} 님</a>
			
			<input id="onwork" class="switch-toggle" type="checkbox" checked>

			<div>
				<span id="newOrder" class="badge badge-pill badge-danger" onclick="location.href='/driver/member/driverOrder'">0</span>

				<button class="navbar-toggler-copy" type="button" id="userMenu" data-target="#navbarDriverMenu">
					<span class="navbar-toggler-icon"></span>
				</button>
				
				<!-- <div class="navbar-collapse-copy collapse" id="navbarDriverMenu" style="text-align: right;">
					<ul class="navbar-nav" style="">
						<li class="nav-item" active>
							<a class="nav-link" href="/driver/member/edit">회원정보</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/driver/logout.do">로그아웃</a>
						</li>
					</ul>
				</div> -->
			</div>
		</div>

	</nav>
				<div class="collapse pr-4" id="navbarDriverMenu" style="text-align: right;">
					<ul class="navbar-nav" style="">
						<li class="nav-item" active>
							<a class="nav-link" href="/driver/member/edit">회원정보</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/driver/logout.do">로그아웃</a>
						</li>
					</ul>
				</div>

</body>