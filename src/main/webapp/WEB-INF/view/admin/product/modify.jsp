<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보</title>
<%@ include file="../refer.jsp"%>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
	crossorigin="anonymous"></script>
<script src="/resources/js/product.js"></script>
<script type="text/javascript">
	// 처리결과 표시
	$(function() {
		const result = '<c:out value="${result}"/>';
		if (result !== '') {
			alert(result);
		}
	});

	// radioSelect(val): 지정하 값(val)과 일치하는 option을 선책
	$.fn.radioSelect = function(val) {
		this.each(function() {
			let $this = $(this);
			if ($this.val() == val) {
				$this.attr('checked', true);
			} else {
				$this.removeAttr('checked');
			}
			$this.prop("checked", ($this.val() == val));
		});
		return this;
	};
	
	$(document).ready(function(){
		// 상품추가 버튼 초기화
		const btnNewProduct = $("#btnNewProduct");
		const pType = ${category.ptype};
		const pCount = ${fn:length(productList)};
		if (pType == 1 && pCount > 0) {
			btnNewProduct.attr("disabled",true);
			btnNewProduct.attr("class", "btn btn-secondary");
		}
		btnNewProduct.on("click", function(e){
			initItemForm();
		});

		// -------------------------------- 상세 상품 modal start -------------------------------- //
		const modal = $("#registerModal");
		const itemForm = $("#itemForm");
		const btnAddItem = $("#btnAddItem");
		const btnModifyItem = $("#btnModifyItem");
		const btnRemoveItem = $("#btnRemoveItem");
		function initItemForm() {
			itemForm.find("[name='pid']").val("${category.pcategory}");
			itemForm.find("[name='pid']").removeAttr("readonly");
			itemForm.find("[name='temperature']").radioSelect(${temperList[0].code});
			itemForm.find("[name='capacity']").val(${capacityList[0].code});
			itemForm.find("[name='price']").val("");
			itemForm.find("[name='onsale']").radioSelect(${onsaleList[0].code});
			itemForm.find("error").remove();
			
			btnAddItem.show();
			btnModifyItem.hide();
			btnRemoveItem.hide();
		}
		// 상품 정모 modal 초기화
		initItemForm();
		if (pType == 1) {
			$("#rowTemperature").hide();
			$("#rowCapacity").hide();
		}
		
		// 상품 추가
		btnAddItem.on("click", function(e){
			if (itemForm.find("[name='price']").isValidNum(0)) {
				itemForm.attr("action", "/admin/product/detail/add");
				itemForm.submit();
			}
		});
		
		// 상품 수정
		btnModifyItem.on("click", function(e){
			if (itemForm.find("[name='price']").isValidNum(0)) {
				itemForm.attr("action", "/admin/product/detail/modify");
				itemForm.submit();
			}
		});
		
		// 상품 삭제
		btnRemoveItem.on("click", function(e){
			itemForm.attr("action", "/admin/product/detail/remove");
			itemForm.submit();
		});
		// -------------------------------- 상세 상품 modal end -------------------------------- //

		$("#btnDeleteCategory").on("click", function(e){
			let msg = "정말로 삭제 하시겠습니까?";
			if (pCount > 0)
				msg = "카테고리에 " + pCount + "건의 상품이 등록되어 있습니다.\n" + msg;
			if (confirm(msg)) {
				const formObj = $("#categoryForm");
				formObj.attr("action", "/admin/product/remove");
				formObj.submit();
			}
		});
		
		// 상품아이디 클릭
		$(".showItem").on("click", "a", function(e){
			var pid = $(this).data("pid");
			//console.log(pid);
			getProduct(pid, function(product) {
				console.log(product);
				itemForm.find("[name='pid']").val(product.pid);
				itemForm.find("[name='pid']").attr("readonly", "readonly")
				itemForm.find("[name='pcategory']").val(product.pcategory);
				itemForm.find("[name='temperature']").radioSelect(product.temperature);
				itemForm.find("[name='capacity']").val(product.capacity);
				itemForm.find("[name='price']").val(product.price);
				itemForm.find("[name='onsale']").radioSelect(product.onsale);
				itemForm.find("error").remove();
				
				btnAddItem.hide();
				btnModifyItem.show();
				btnRemoveItem.show();
				
				modal.modal("show");
			});
		});

	});

	function regist() {
		// check input
		if(checkInputCategory($("#categoryForm")) == false) {
			return false;
		}
		return true;
	}
</script>
<style type="text/css">
.modal-header, .modal-footer {
	background-color: rgb(174, 113, 113);
}
</style>

</head>
<body>

	<%@ include file="../header.jsp"%>

	<div class="container">
		<div class="row mt-5">
			<div class="col-md-5">
				<img id="preview" src="/resources/img/${category.imagefile}"
					style="width: 100%">
			</div>
			<form id="categoryForm" class="form-horizontal col-md-7"
				action="/admin/product/modify" method="post"
				enctype="multipart/form-data" accept-charset="UTF-8">
				<div class="form-group row">
					<label class="col-sm-2">카테고리<br>코드
					</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" readonly="readonly" maxlength="50" name="pcategory" value="${category.pcategory}">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">분류</label>
					<div class="col-sm-4">
						<select class="custom-select" name="ptype">
							<c:forEach var="ptype" items="${ptypeList}">
								<option value="${ptype.code}"
									${ptype.code == category.ptype ? "selected" : "disabled" }>${ptype.disp}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">이름</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" required="required" maxlength="50" name="pname" value="${category.pname}">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">설명</label>
					<div class="col-sm-10">
						<textarea name="description" rows="2" cols="50" maxlength="200" class="form-control" required="required">${category.description}</textarea>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">영양정보</label>
					<div class="col-sm-10">
						<div class="form-group row">
							<div class="form-group col-sm-3">
								<label>열량(kcal)</label>
								<input type="text" class="form-control" required="required" maxlength="4" name="calorie" value="${category.calorie}">
							</div>
							<div class="form-group col-sm-3">
								<label>카페인(mg)</label>
								<input type="text" class="form-control" required="required" maxlength="4" name="caffeine" value="${category.caffeine}">
							</div>
							<div class="form-group col-sm-3">
								<label>당류(g)</label>
								<input type="text" class="form-control" required="required" maxlength="4" name="sugars" value="${category.sugars}">
							</div>
							<div class="form-group col-sm-3">
								<label>단백질(g)</label>
								<input type="text" class="form-control" required="required" maxlength="4" name="protein" value="${category.protein}">
							</div>
							<div class="form-group col-sm-3">
								<label>포화지방(g)</label>
								<input type="text" class="form-control" required="required" maxlength="4" name="fat" value="${category.fat}">
							</div>
							<div class="form-group col-sm-3">
								<label>나트륨(mg)</label>
								<input type="text" class="form-control" required="required" maxlength="4" name="sodium" value="${category.sodium}">
							</div>
						</div>


					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">이미지</label>
					<div class="col-sm-10">
						<input type="hidden" name="imagefile" value="${category.imagefile}">
						<!-- <input type="file" name="uploadfile" accept="image/png,image/jpeg"> -->
					    <div class="custom-file">
					      <input type="file" name="uploadfile" class="custom-file-input" accept="image/png, image/jpeg" id="validatedInputGroupCustomFile">
					      <label class="custom-file-label" for="validatedInputGroupCustomFile">Choose file...</label>
					    </div>
					</div>
				</div>
				<div class="form-group row">
					<div class="offset-sm-2 ">
						<input type="submit" class="btn btn-success" value="수정" onclick="return regist();">
						<input type="button" class="btn btn-danger" value="삭제" id="btnDeleteCategory">
						<!-- Button trigger modal -->
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#registerModal" id="btnNewProduct">상품추가</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- 상품 -->
	<div class="container">
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">상세 상품</h6>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="dataTable2" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th>상품번호</th>
								<th>아이스 / 핫</th>
								<th>용량</th>
								<th>가격</th>
								<th>상태</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="product" items="${productList}">
								<tr>
									<td class="showItem"><a href="#" data-pid="${product.pid}">${product.pid}</a></td>
									<td><c:forEach var="temper" items="${temperList}">
									${temper.code == product.temperature ? temper.disp : ''}
									</c:forEach></td>
									<td><c:forEach var="capacity" items="${capacityList}">
									${capacity.code == product.capacity ? (category.ptype == 1?'-':capacity.disp) : ''}
									</c:forEach></td>
									<td>${product.price}</td>
									<td><c:forEach var="onsale" items="${onsaleList}">
									${onsale.code == product.onsale ? onsale.disp : ''}
									</c:forEach></td>
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
				<div class="modal-header">
					<h5 class="modal-title" id="registerModalLabel">상품 정보</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">


					<form id="itemForm" action="/admin/product/item/add" method="post">
						<div class="form-group row">
							<label class="col-sm-3">카테고리<br>코드
							</label>
							<div class="col-sm-5">
								<input type="text" name="pcategory" class="form-control" readonly="readonly" value="${category.pcategory}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3">상품아이디</label>
							<div class="col-sm-5">
								<input type="text" name="pid" class="form-control" required="required" value="${category.pcategory}">
								<%-- <div class="input-group">
									<input type="text" name="pcategory" class="form-control" readonly="readonly" value="${category.pcategory}">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">-</span>
									</div>
									<input type="text" name="pcategory" class="form-control" value="">
								</div> --%>
							</div>
						</div>
						<div class="form-group row" id="rowTemperature">
							<label class="col-sm-3">구분</label>
							<div class="col-sm-9">
								<div class="input-group">
									<c:forEach var="temper" items="${temperList}">
										<div class="form-check">
											<input type="radio" name="temperature" value="${temper.code}">
											<label>${temper.disp}</label>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="form-group row" id="rowCapacity">
							<label class="col-sm-3">용량</label>
							<div class="col-sm-5">
								<select class="custom-select" name="capacity" required="required">
									<c:forEach var="capacity" items="${capacityList}">
										<option value="${capacity.code}">${capacity.disp}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3">가격</label>
							<div class="col-sm-5">
								<input type="text" name="price" class="form-control" maxlength="10" required="required">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3">판매</label>
							<div class="col-sm-9">
								<div class="input-group">
									<c:forEach var="onsale" items="${onsaleList}">
										<div class="form-check">
											<input type="radio" name="onsale" value="${onsale.code}">
											<label>${onsale.disp}</label>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</form>


				</div>
				<div class="modal-footer">
					<button type="button" id="btnAddItem" class="btn btn-primary">등록</button>
					<button type="button" id="btnModifyItem" class="btn btn-success">수정</button>
					<button type="button" id="btnRemoveItem" class="btn btn-danger">삭제</button>
					<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>