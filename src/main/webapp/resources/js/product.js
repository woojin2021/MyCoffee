/**
 * 카테고리 상품 페이지(/admin/product/) 모듈
 */


$(function() {
	// 이미지 프리뷰
	$("input:file").on("change", function(event) {
		var file = event.target.files[0];
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#preview").attr("src", e.target.result);
		}
		reader.readAsDataURL(file);
	});
});

$.fn.isValidLength = function(isvalid) {
	if (isvalid === undefined) {
		this.removeClass("is-invalid");
		isvalid = true;
	}
	//console.log(this.attr("maxlength"));
	const maxlength = this.attr("maxlength");
	const blength = (function(s,b,i,c){
	    for(b=i=0;c=s.charCodeAt(i++);b+=c>>11?3:c>>7?2:1);
	    return b
	})(this.val());
	//console.log(blength + " Bytes");
	if (blength > maxlength) {
		this.addClass("is-invalid");
		isvalid = false;
		console.log(this.attr("name") + " is-invalid length ");
	}

	return isvalid;
};

$.fn.isValidRequired = function(isvalid) {
	if (isvalid === undefined) {
		this.removeClass("is-invalid");
		isvalid = true;
	}
	
	if (isvalid && (this.attr("required") && (this.val().length == 0))) {
		this.addClass("is-invalid");
		isvalid = false;
		console.log(this.attr("name") + " is-invalid required ");
	}
	
	return isvalid;
}

$.fn.isValidText = function() {
	// maxlength
	let isvalid = this.isValidLength();
	
	// required
	isvalid = this.isValidRequired(isvalid);
		
	return isvalid;
};

$.fn.isValidCode = function() {
	// text
	let isvalid = this.isValidText();
		
	// code
	const engNum =  /^[a-zA-Z0-9]*$/;
	if (isvalid && engNum.test(this.val()) == false) {
		this.addClass("is-invalid");
		isvalid = false;
		console.log(this.attr("name") + " is-invalid code");
	}
	
	return isvalid;
};

$.fn.isValidNum = function(min) {
	if (min === undefined) min = 0;
	//console.log(min);
	
	let isvalid = this.isValidRequired();

	const num =  /^[0-9]+$/;
	if (isvalid && num.test(this.val()) == false || this.val() < min) {
		this.addClass("is-invalid");
		isvalid = false;
		console.log(this.attr("name") + " is-invalid number");
	}
	
	return isvalid;
};

function checkInputCategory(form) {
	console.log(form);
	let isvalide = true;
	
	// 1. maxlength - common
	// 2. required - common
	// 3. code
	// 4. 
	
	isvalide = isvalide & form.find("[name='pcategory']").isValidCode();
	isvalide = isvalide & form.find("[name='pname']").isValidText();
	isvalide = isvalide & form.find("[name='description']").isValidText();
	
	isvalide = isvalide & form.find("[name='calorie']").isValidNum();
	isvalide = isvalide & form.find("[name='caffeine']").isValidNum();
	isvalide = isvalide & form.find("[name='sugars']").isValidNum();
	isvalide = isvalide & form.find("[name='protein']").isValidNum();
	isvalide = isvalide & form.find("[name='fat']").isValidNum();
	isvalide = isvalide & form.find("[name='sodium']").isValidNum();
	
	let file = form.find("[name='uploadfile']");
	if (file.attr("required")) {
		file.removeClass("is-invalid");
		if (file.val() == "") {
			file.addClass("is-invalid");
			isvalide = false;
		}
		console.log(file.val());
	}

	return isvalide;
}


function checkInputProduct(form) {
	// pid
	const pid = form.find("[name='pid']").val();
	console.log("product");
	getProduct(pid, function(product) {
		console.log(product == "");
	});
	// PK
	// 가격
	const price = form.find("[name='price']");
	return price.isValidNum();
}

// 상세상품 검색
function getProduct(pid, callback, error) {
	$.get("/admin/product/detail/get/" + pid,
		function(result) {
			if (callback) { callback(result); }
		}
	).fail(
		function(xhr, status, err) {
			if (error) { error(err); }
		}
	);
}