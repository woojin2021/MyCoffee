<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<%@ include file="../refer.jsp"%>
<script src="/resources/js/product.js"></script>
<script type="text/javascript">
	let xhrObject; // XMLhttpRequest객체를 저장할 변수, 전역변수선언
	
	function checkResult() {
		if(xhrObject.readyState == 4){
			if(xhrObject.status == 200){
				const result = xhrObject.responseText;
				console.log("result:" + result);
				if (result.length == 0) {
					document.getElementById("idChecked").value = true;
					document.getElementById("idCheckResult").innerText = "";
					$("[name=pcategory]").removeClass("is-invalid");
				} else {
					document.getElementById("idChecked").value = false;
					document.getElementById("idCheckResult").innerText = "중복된 코드입니다.";
					$("[name=pcategory]").addClass("is-invalid");
				}
			}
		}
	}
	
	function checkPcategory(input) {
		input.value = input.value.trim();
		let pcategory = input.value;
		console.log("input:" + input.name + ":" + pcategory);
		if (pcategory.length == 0) {
			return;
		}
		
		xhrObject = new XMLHttpRequest();
		
		var url = "/admin/product/check/" + pcategory; //요청 url 설정
		var reqparam = "";
		xhrObject.onreadystatechange = checkResult; // 응답결과를 처리메소드에 설정
		xhrObject.open("Get", url, "true"); //서버의 요청설정 -url변수에 설정된 리소스를 요청할 준비
		xhrObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		xhrObject.send(reqparam);
	}

	function regist() {
		// check input
		if(checkInputCategory($("#newCategory")) == false) {
			return false;
		}
		// check pcategory
		//console.log("regist");
		let idChk = document.getElementById("idChecked").value;
		if ($("input[name='pcategory']").val() != "" && $("#idChecked").val() == "false") {
			return false;
		}
		return true;
	}
</script>
</head>
<body>

	<%@ include file="../header.jsp"%>

	<div class="container">
		<div class="row mt-5">
			<div class="col-md-5">
				<img id="preview" src="" style="width: 100%">
			</div>
			<form id="newCategory" class="form-horizontal col-md-7" action="/admin/product/register" method="post"
			 enctype="multipart/form-data" accept-charset="UTF-8">
				<div class="form-group row">
					<label class="col-sm-2">코드</label>
					<div class="col-sm-3">
						<input type="text" name="pcategory" class="form-control" maxlength="10" required="required" onchange="checkPcategory(this);">
						<input type="hidden" id="idChecked" value="false">
					</div>
					<label class="col-sm-5 text-danger" id="idCheckResult"></label>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">분류</label>
					<div class="col-sm-4">
						<select class="custom-select" name="ptype" required="required">
							<c:forEach var="ptype" items="${ptypeList}">
								<option value="${ptype.code}">${ptype.disp}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">이름</label>
					<div class="col-sm-10">
						<input type="text" name="pname" class="form-control" maxlength="50" required="required">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">설명</label>
					<div class="col-sm-10">
						<textarea name="description" rows="2" cols="50" class="form-control" maxlength="200" required="required"></textarea>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">영양정보</label>
					<div class="col-sm-10">
						<div class="form-group row">
							<div class="form-group col-sm-3">
								<label>열량(kcal)</label>
								<input type="text" class="form-control" maxlength="4" required="required" value="0" name="calorie">
							</div>
							<div class="form-group col-sm-3">
								<label>카페인(mg)</label>
								<input type="text" class="form-control" maxlength="4" required="required" value="0" name="caffeine">
							</div>
							<div class="form-group col-sm-3">
								<label>당류(g)</label>
								<input type="text" class="form-control" maxlength="4" required="required" value="0" name="sugars">
							</div>
							<div class="form-group col-sm-3">
								<label>단백질(g)</label>
								<input type="text" class="form-control" maxlength="4" required="required" value="0" name="protein">
							</div>
							<div class="form-group col-sm-3">
								<label>포화지방(g)</label>
								<input type="text" class="form-control" maxlength="4" required="required" value="0" name="fat">
							</div>
							<div class="form-group col-sm-3">
								<label>나트륨(mg)</label>
								<input type="text" class="form-control" maxlength="4" required="required" value="0" name="sodium">
							</div>
						</div>


					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">이미지</label>
					<div class="col-sm-10">
						<!-- <input type="file" name="uploadfile" accept="image/png, image/jpeg" required="required"> -->
					    <div class="custom-file">
					      <input type="file" name="uploadfile" class="custom-file-input" accept="image/png, image/jpeg" id="validatedInputGroupCustomFile" required>
					      <label class="custom-file-label" for="validatedInputGroupCustomFile">Choose file...</label>
					    </div>
					</div>
				</div>
				<div class="form-group row">
					<div class="offset-sm-2 col-sm-2">
						<input type="submit" class="btn btn-primary" value="등록" id="btnRegist" onclick="return regist();">
					</div>
					<div class="offset-sm-1 col-sm-3">
						<!-- <button data-oper="productlist" class="btn btn-secondary">취소</button> -->
						<a href="/admin/product/list" class="btn btn-secondary">취소</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>