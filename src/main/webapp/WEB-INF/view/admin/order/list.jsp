<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 목록</title>
<%@ include file="../refer.jsp"%>
</head>
<script type="text/javascript">
let orderInfo = null;

$(document).ready(function(){

	// 주문정보
    getOrderData();
	var interval = 10000; // 10s
	var timer = setInterval('getOrderData()', interval);
	
	// 주문정보 검색
	function getOrderData() {
		$.get("/admin/order/list/data", function(result) {
			resetOrderList(result);
		}).fail(function(xhr, status, err) {
			console.log(xhr);
			if (xhr.status == 406)
				clearInterval(timer);
	    });
	}
});

// 주문정보 표시
function resetOrderList(result) {
	if (isOrderChanged(result) == false)
		return;
	
	let buffer = "";

	for (let i = 0; i < result.length; i++) {
		buffer += '<tr>';
		buffer += '<td>' + result[i].oid + '</td>';
		buffer += '<td>' + result[i].ordertime + '</td>';
		buffer += '<td>' + result[i].statusdisp + '</td>';
		buffer += '<td>' + result[i].orderdetail + '</td>';
		buffer += '<td>' + result[i].totalprice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원</td>';
		buffer += '<td>' + result[i].userinfo + '</td>';
		buffer += '<td><div class="d-flex justify-content-around">';
		let status = result[i].status;
		if (status == 1) { // 주문 완료
			buffer += '<button class="btn btn-danger" data-oper="cancel" data-oid="' + result[i].oid + '">취소</button>';
			buffer += '<button class="btn btn-success" data-oper="accept" data-oid="' + result[i].oid + '">접수</button>';
			buffer += '<button class="btn btn-secondary" data-oper="deliver" data-oid="' + result[i].oid + '" disabled>배달</button>';
		} else if (status == 2) { // 주문 접수
			buffer += '<button class="btn btn-danger" data-oper="cancel" data-oid="' + result[i].oid + '">취소</button>';
			buffer += '<button class="btn btn-secondary" data-oper="accept" data-oid="' + result[i].oid + '" disabled>접수</button>';
			buffer += '<button class="btn btn-success" data-oper="deliver" data-oid="' + result[i].oid + '">배달</button>';
		} else if (status == 3 || status == 4) { // 배달 의뢰, 배달 접수
			buffer += '<button class="btn btn-danger" data-oper="cancel" data-oid="' + result[i].oid + '">취소</button>';
			buffer += '<button class="btn btn-secondary" data-oper="accept" data-oid="' + result[i].oid + '" disabled>접수</button>';
			buffer += '<button class="btn btn-secondary" data-oper="deliver" data-oid="' + result[i].oid + '" disabled>배달</button>';
		} else { // 배달 완료, 배달 취소
			buffer += '<button class="btn btn-secondary" data-oper="cancel" data-oid="' + result[i].oid + '" disabled>취소</button>';
			buffer += '<button class="btn btn-secondary" data-oper="accept" data-oid="' + result[i].oid + '" disabled>접수</button>';
			buffer += '<button class="btn btn-secondary" data-oper="deliver" data-oid="' + result[i].oid + '" disabled>배달</button>';
		}
		buffer += '</div></td>';
		buffer += '</tr>';
	}
	
	$("#orderBody").html(buffer);

	const buttons = $("button[data-oper]");
	$("button[data-oper]").on("click", function(e){
		const oper = $(this).data("oper");
		const oid = $(this).data("oid");
		console.log(oper + ":" + oid)
		$.get("/admin/order/list/" + oper + "/" + oid, function(result) {
			resetOrderList(result);
		});
	});
}

// 주문정보 변경확인
function isOrderChanged(result) {
	let check = false;

	if (orderInfo == null) {
		check = true;
	} else if (orderInfo.length != result.length) {
		check = true;
	} else {
		for (let i = 0; i < result.length; i++) {
			if (orderInfo[i].oid != result[i].oid || orderInfo[i].status != result[i].status) {
				check = true;
				break;
			}
		}
	}
	if (check) 
		orderInfo = result;
	return check;
}

</script>
<body>

	<%@ include file="../header.jsp"%>

	<div class="m-5">
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">주문 목록</h6>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="dataTable2" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th>주문번호</th>
								<th>주문시각</th>
								<th>주문상태</th>
								<th>주문내역</th>
								<th>금액</th>
								<th>주문자 정보</th>
								<th style="width: 230px">처리</th>
							</tr>
						</thead>
						<tbody id="orderBody">
							<c:forEach var="order" items="${orderList}">
								<tr>
									<td>${order.oid}</td>
									<td>${order.ordertime}</td>
									<td>${order.statusdisp}</td>
									<td>${order.orderdetail}</td>
									<td>${order.totalprice}</td>
									<td>${order.userinfo}</td>
									<td>
										<button class="btn btn-secondary" data-status="${order.status}">취소</button>
										<button class="btn btn-secondary" data-status="${order.status}">접수</button>
										<button class="btn btn-secondary" data-status="${order.status}">배달</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>