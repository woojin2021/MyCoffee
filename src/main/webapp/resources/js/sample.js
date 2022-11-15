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

