<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/css/bootstrap.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.1.js" 
	integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" 
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>배달원 현황</title>
</head>
<script type="text/javascript">
	
	$(document).ready(function(){

		const itemForm = $("#itemForm");
		const modal = $("#registerModal");
		
		// tab click
		$(".nav-item").on("click", "a", function(e){
			//console.log(this);
			const tab = $(this);
			// activate tab
			const type = tab.data("type");
			$(".nav-link").each(function(){ this.classList.remove("active"); });
			tab.addClass("active");
			// filter data
			$("#tbody-drivers tr").each(function(){
				const tr = $(this);
				const status = tr.data("status");
				//console.log(status);
				tr.removeClass("hide-row")
				if (type == "request") {
					if (status != 1) {
						tr.addClass("hide-row");
					}
				} else if (type == "waiting") {
					if (status != 3) {
						tr.addClass("hide-row");
					}
				} else if (type == "delivery") {
					if (status != 4) {
						tr.addClass("hide-row");
					}
				}
			});
		});
		
		// 등록승인 click
		$(".showItem").on("click", "a", function(e){
			var did = $(this).data("did");
			console.log(did);
			getDriver(did, function(driver) {
				console.log(driver);
				itemForm.find("[name='did']").val(driver.did);
				itemForm.find("[name='name']").val(driver.name);
				itemForm.find("[name='mobile']").val(driver.mobile);
				itemForm.find("[name='reason']").val(driver.reason);
				
				modal.modal("show");
			});
		});
		// 승인 click
		$("#btnApprove").on("click", function(e) {
			itemForm.attr("action", "/driver/manager/join/approve");
			itemForm.submit();
		});
		// 거부 click
		$("#btnReject").on("click", function(e) {
			itemForm.attr("action", "/driver/manager/join/reject");
			itemForm.submit();
		});

		
	});
	
	function getDriver2(did, callback, error){
// 		callback({"did":"xxx", "name":"yyy", "mobile":"zzz"});
		$.get("/driver/member/list/test/did=" + did,
			function(result){
				if(callback){callback(result);}
			}
		).fail(
			function(xhr, status, err){
				console.log(xhr);
				if (error){error(err);}
			}		
		);
	}

	function getDriver(did, callback, error){
		var driver = {"did": did};
		console.log("did:" + driver.did);
		$.ajax({
			url: '/driver/member/list/data',
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {if(callback){callback(data);}},
			data: JSON.stringify(driver),
			fail: function(xhr, status, err) {if(error){error(err);}}
		});
	}
		
		
</script>
<style type="text/css">
	.modal-body, .modal-footer {
		background-color: rgb(200, 200, 200);
	}
	.hide-row {
		display: none;
	}
</style>
<body>
	<%@include file="include/listMenu.jsp" %>
	
	<div class="m-5">
		<div class="card shadow mb-4">
			<div class="card-header">
				<ul class="nav nav-tabs card-header-tabs">
					<li class="nav-item">
						<a class="nav-link active" href="#" data-type="all">전체</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#" data-type="request">등록신청</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#" data-type="waiting">대기중</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" data-type="delivery">배달중</a>
					</li>
				</ul>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>이름</th>
								<th>목적지</th>
								<th>미처리/완료/할당</th>
								<th>상태</th>
							</tr>
						</thead>
			
						<tbody id="tbody-drivers">
							<c:forEach items="${list}" var="board">
								<tr data-status="${board.status}">
									<td>${board.name}</td>
									<td>${board.address}</td>
									<td>${board.reserved} / ${board.completed} / ${board.total}</td>
									<td class="showItem">
										<c:choose>
											<c:when test="${board.permitted == 0}">
												<a href="#" data-did="${board.did}">${board.statusDisp}</a>
											</c:when>
											<c:otherwise>${board.statusDisp}</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="registerModal" tabindex="-1" 
		aria-labelledby="registerModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
			<div class="modal-body">
			
				<form id="itemForm" action="#" method="POST">
					<div class="form-group row">
						<label class="col-sm-3">이메일주소<br>(아이디)</label>
						<div class="col-sm-5">
							<input type="text" name="did" class="form-control" readonly="readonly" value="${driver.did}">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-3">이름</label>
						<div class="col-sm-5">
							<input type="text" name="name" class="form-control" readonly="readonly" value="${driver.name}">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-3">휴대폰</label>
						<div class="col-sm-5">
							<input type="text" name="mobile" class="form-control" readonly="readonly" value="${driver.mobile}">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-3">승인 거부 사유</label>
						<div class="col-sm-5">
							<input type="text" name="reason" class="form-control" value="${driver.reason}">
						</div>
					</div>
				</form>
			
			</div>
			
			<div class="modal-footer">
				<button type="button" id="btnApprove" class="btn btn-success">승인</button>
				<button type="button" id="btnReject" class="btn btn-danger">거부</button>
			</div>
			</div>
		</div>
	</div>
</body>
</html>