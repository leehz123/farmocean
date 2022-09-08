<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상품 리뷰 작성 팝업창</title>
<%@ include file="/resources/jspf/header.jspf" %>
<style>

#myform fieldset{
    display: inline-block;
    direction: rtl;
    border:0;
}
#myform fieldset legend{
    text-align: right;
}
#myform input[type=radio]{
    display: none;
}
#myform label{
    font-size: 2em; /* 별 크기 조절 */
    color: transparent;
    text-shadow: 0 0 0 #f0f0f0;
}
#myform label:hover{
    text-shadow: 0 0 0 rgba(0, 104, 250, 0.99);
}
#myform label:hover ~ label{
    text-shadow: 0 0 0 rgba(0, 104, 250, 0.99);
}
#myform input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 rgba(0, 104, 250, 0.99);
}
#reviewContents {
    width: 100%;
    height: 250px;
    padding: 10px;
    box-sizing: border-box;
    border: solid 1.5px #D3D3D3;
    border-radius: 5px;
    font-size: 16px;
    resize: none;
}

</style>

</head>


<body>
	<h3>상품리뷰작성 팝업창</h3>

<!-- 
단계 1) 
[prod_review]
review_idx, prod_idx, member_id, review_content, review_date, review_starnum
prod_review는 컨트롤러에서 바인딩 해서 받게 name 값을 필드값과 일치시키기

단계 2) 
[review_picture]
review_picture_idx, review_idx, review_picture_url
review_picture도 바인딩 가능? 근데 여러장 올릴 거면.. 바인딩 안 되지 않나?
얘는 req.getParameter('네임')으로 가져가야 할 것 같은데

http://localhost:8888/farmocean/product/product_review_write/prod/insert_review
 -->

<button>이미지 업로드</button> <br />
<form action="../../prod/insert_review" class="mb-3" name="myform" id="myform" method="post">
	아이디 : <input type="text" id="member-id-input" name="member_id" value="${sessionScope.loginId.member_id }"/><br />
	닉네임 : <input type="text" id="member-nickname-input" name="review_content"/><br />
	상품idx : <input type="text" id="prod-idx-input" name="prod_idx" value="상품인덱스번호"/><br />
	
	<fieldset>
		<span class="text-bold">&nbsp;별점을 선택해주세요&nbsp;</span>
		<input type="radio" name="review_starNum" value="5" id="rate1"><label
			for="rate1">★</label>
		<input type="radio" name="review_starNum" value="4" id="rate2"><label
			for="rate2">★</label>
		<input type="radio" name="review_starNum" value="3" id="rate3"><label
			for="rate3">★</label>
		<input type="radio" name="review_starNum" value="2" id="rate4"><label
			for="rate4">★</label>
		<input type="radio" name="review_starNum" value="1" id="rate5"><label
			for="rate5">★</label>
	</fieldset>
	
	<div>
		<textarea rows="15" cols="65" class="col-auto form-control" type="text" id="review-contents" name="review_content"
				  placeholder="후기를 남겨주세요"></textarea>
	</div>
</form>
<div id="btn-area"><input id="submit-btn" type="submit" value="등록"/> <input type="reset" value="취소" form="myform"/></div>

 




</body>
<script charset="EUC-KR" src="${path}/resources/js/product/product_review_write.js"></script>
</html>