<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="../../resources/css/bootstrap.css" rel="stylesheet" />
<link href="../../resources/css/driverorder.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>배달 목록</title>
</head>
<script type="text/javascript">
    $(document).ready(function () {

        $("div.card-header").on("click", function (e) {
            const cardIcon = $(this).find("img");
            const cardBody = $(this).next();
            console.log(cardIcon.attr("src"));
            if (cardBody.hasClass("show")) {
                cardBody.removeClass("show");
                cardIcon.attr("src", "/resources/img/togle2.png");
            } else {
                cardBody.addClass("show");
                cardIcon.attr("src", "/resources/img/togle.png");
            }
        });

    });
</script>
<style>
    .driver-orders {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
    }

    .card-order {
        min-width: 450px;
        max-width: 600px;
    }
</style>
<body>
	<%@include file="include/menu.jsp" %>   

	<div class="container mt-3 driver-orders">
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
                            <p>${order.oid}</p>
                        </div>
                    </div>
                    <p class="${order.status==3?'text-danger':'text-primary'}">${order.status2}</p>
                </div>
            </div>
            <div class="card-body collapse">
                <div class="d-flex justify-content-between">
                    <h5>주문 내역</h5>
                    <c:if test="${order.status<5}">
                    <button class="btn ${order.status==3?'btn-primary':'btn-warning'}">${order.status==3?'접수':'완료'}</button>
                    </c:if>
                </div>
                <p>${order.orderdetail}</p>
                <h5>주소</h5>
                <p>${order.address}</p>
            </div>
        </div>
	</c:forEach>
	</div>

	<c:forEach items="${order}" var="order" varStatus="status">
		<table>
			<div class="faq-content" id="${status.count}">
	<!-- 		<div class="faq-content"> -->
			
				<button class="question" id="que${status.count+1}">
				
	<%-- 		  		<span id="que${status.count+1}-toggle"> --%>
	<!-- 		  			<img src="../../resources/img/togle2.png">주문번호 -->
	<!-- 		  		</span> -->
			  		
			  		<span>- 주문번호</span><p/>
			  		<br>${order.oid}
			  		
	 				<!-- tbl_codes (배달대기 3, 배달중 4, 배달완료 5 /tbl_order (status -->
	 				<!-- 확인/완료 버튼 클릭 시 tbl_order status변경 -->
	 				
			  		<div class="btn btn-secondary" id="del${status.count+1}">
			  			${order.status2}
			  		</div>
			  		
				</button>
				
				<div class="answer" id="ans${status.count+1}">
				
					<p><span>주문내역</span>
					<p><c:out value="${order.orderdetail}" />
					<p><span>고객주소</span>
					<p><c:out value="${order.address}" />
					
					<!-- 배달대기 ->확인 / 배달완료 -> 완료 -->
					<p>
					<c:if test="${order.status<  5}">
					<button type="button" class="btn-secondary" id="conf${status.count+1}">
						${order.status == 3 ? "확인" : "완료" }
					</button>
					</c:if>
				</div>
				
			</div>
		
		</table>
	</c:forEach>
	
</body>
<script>
const cnt = ${newOrder};
const newOrder = $("#newOrder");
console.log(cnt);
console.log(newOrder.text(cnt));

const items = document.querySelectorAll('.question');

function openCloseAnswer() {
  const answerId = this.id.replace('que', 'ans', 'del' , 'conf');

  if(document.getElementById(answerId).style.display === 'block') {
    document.getElementById(answerId).style.display = 'none';
    document.getElementById(this.id + '-toggle').textContent = '+';
  } else {
    document.getElementById(answerId).style.display = 'block';
    document.getElementById(this.id + '-toggle').textContent = '-';
  }
}

items.forEach(item => item.addEventListener('click', openCloseAnswer));

</script>	
</html>