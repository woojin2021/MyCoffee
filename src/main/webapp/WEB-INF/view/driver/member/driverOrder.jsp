<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/css/bootstrap.css" rel="stylesheet" />
<link href="/resources/css/driverorder.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>배달 목록</title>
</head>
<script type="text/javascript">

// 주문 상세 표시/비표시
function cardHeaderAction(e) {
    const cardIcon = $(e).find("img");
    const cardBody = $(e).next();
    //console.log(cardIcon.attr("src"));
    if (cardBody.hasClass("show")) {
        cardBody.removeClass("show");
        cardIcon.attr("src", "/resources/img/togle2.png");
    } else {
        cardBody.addClass("show");
        cardIcon.attr("src", "/resources/img/togle.png");
    }
}

// 주문 접수/완료
function cardButtonAction(e) {
	const oid = $(e).data("oid");
	const status = $(e).data("status");
	console.log(oid);
	const form = $("#actionForm");
	$("#oid").val(oid);
	if (status == 3) {
		form.attr("action", "/driver/member/driverOrder/checkout");
	} else if (status == 4) {
		form.attr("action", "/driver/member/driverOrder/complete");
	} else {
		return;
	}
	form.submit();
}

// 새 주문 div
function getNewOrderCard(order) {
	let buffer = '';
    buffer += '<div class="card m-2 shadow card-order">';
    buffer += '    <div class="card-header">';
    buffer += '        <div class="d-flex justify-content-between">';
    buffer += '            <div class="d-flex align-items-center">';
    buffer += '                <img src="/resources/img/togle2.png">';
    buffer += '                <div class="ml-3">';
    buffer += '                    <h5>주문번호</h5>';
    buffer += '                    <p class="orderid">' + order.oid + '</p>';
    buffer += '                </div>';
    buffer += '            </div>';
    buffer += '            <p class="text-danger">' + order.status2 + '</p>';
    buffer += '        </div>';
    buffer += '    </div>';
    buffer += '    <div class="card-body collapse">';
    buffer += '        <div class="d-flex justify-content-between">';
    buffer += '            <h5>주문 내역</h5>';
    buffer += '            <button class="btn btn-primary" data-oid="' + order.oid + '" data-status="' + order.status + '">접수</button>';
    buffer += '        </div>';
    buffer += '        <p>' + order.orderdetail + '</p>';
    buffer += '        <h5>주소</h5>';
    buffer += '        <p>' + order.address + '</p>';
    buffer += '    </div>';
    buffer += '</div>';
    const newDiv = $(buffer);

	// event등록
    newDiv.find(".card-header").on("click", function (e) {
    	cardHeaderAction(this);
    });
	newDiv.find("div.card-body button").on("click", function (e) {
		cardButtonAction(this);
	});

	return newDiv;
}

/*
 * 신규 주문 추가(화면에 없는 경우에만 추가)
 * @datas 미배정 배달주문
 */
function addNewOrder(datas) {
	const container = $(".driver-orders");
	const orders = container.find(".card-header p.orderid");
	console.log(orders);
	datas.forEach(data => {
		console.log(data.oid);
		let isnotloaded = true;
		for(let i = 0; i < orders.length; i++) {
			if (data.oid == orders[i].innerText) {
				isnotloaded = false;
				break;
			}
		}
		if (isnotloaded) {
			const newDiv = getNewOrderCard(data);
			console.log(newDiv);
			container.prepend(newDiv);
		}
	});
	orders.each(function(i, obj) {
		console.log(obj);
	});
}

function ajaxError(xhr) {
	console.log(xhr);
	if (xhr.status == 406)
		clearInterval(timer);
}

$(document).ready(function () {

	// click event:주문 상세 표시/비표시
    $("div.card-header").on("click", function (e) {
    	cardHeaderAction(this);
    });
	
	// click event:주문 접수/주문 완료
	$("div.card-body button").on("click", function (e) {
		cardButtonAction(this);
	});

	let cnt = 1;
	const interval = 3000; //3s
	const timer = setInterval(function(){
 	    cnt++;
	    if(cnt > 3){
	        console.log("디버깅을 위해 3회실시후 종료 합니다.",cnt);
	        clearInterval(timer);
	    }
	    
	    // 미배정 주문 확인
	    $.get("/driver/member/driverOrder/unchecked", function(result) {
			console.log(result);
			$("#newOrder").text(result.length); // 미배정 주문 수 표시
			addNewOrder(result);                // 화면에 추가
	    }).fail(function(xhr, status, err) {
	    	ajaxError(xhr);
	    });
    
	}, interval);
	
});
</script>
<style>
    .driver-orders {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
    }

    .card-order {
        min-width: min(90%, 450px);
        /* max-width: 600px; */
    }
</style>
<body>
	<%@include file="include/menu.jsp" %>

	<div class="container mt-3 driver-orders">
		<form id="actionForm" method="post">
			<input type="hidden" id="oid" name="oid">
		</form>
		
		<c:set var="newOrder" value="0"/>
		<c:forEach items="${order}" var="order" varStatus="status">
			<c:if test="${order.status==3}"><c:set var="newOrder" value="${newOrder + 1}"/></c:if>
	
	        <div class="card m-2 shadow card-order">
	            <div class="card-header">
	                <div class="d-flex justify-content-between">
	                    <div class="d-flex align-items-center">
	                        <img src="/resources/img/togle2.png">
	                        <div class="ml-3">
	                            <h5>주문번호</h5>
	                            <p class="orderid">${order.oid}</p>
	                        </div>
	                    </div>
	                    <p class="${order.status==3?'text-danger':'text-primary'}">${order.status2}</p>
	                </div>
	            </div>
	            <div class="card-body collapse">
	                <div class="d-flex justify-content-between">
	                    <h5>주문 내역</h5>
	                    <c:choose>
	                    	<c:when test="${order.status==3}">
	                    		<button class="btn btn-primary" data-oid="${order.oid}" data-status="${order.status}">접수</button>
	                    	</c:when>
	                    	<c:when test="${order.status==4}">
	                    		<button class="btn btn-warning" data-oid="${order.oid}" data-status="${order.status}">완료</button>
	                    	</c:when>
	                    </c:choose>
	                </div>
	                <p>${order.orderdetail}</p>
	                <h5>주소</h5>
	                <p>${order.address}</p>
	            </div>
	        </div>
		</c:forEach>
	</div>

</body>
<script>
	$("#newOrder").text(${newOrder});
</script>	
</html>