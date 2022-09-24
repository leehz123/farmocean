<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상품 리뷰 작성</title>
<%@ include file="/resources/jspf/header.jspf" %>

<style>

* {
	font-size: 17px;
}

.flex-col {
	display: flex;
	flex-direction: column;
}

#form1 fieldset{
    display: inline-block;
    direction: rtl;
    border:0;
}
#form1 fieldset legend{
    text-align: right;
}
#form1 input[type=radio]{
    display: none;
}
.star-label {
    font-size: 2em; /* 별 크기 조절 */
    color: transparent;
    text-shadow: 0 0 0 #f0f0f0;
}
#form1 label:hover {
    text-shadow: 0 0 0 rgba(0, 104, 250, 0.99);
}
#form1 label:hover ~ label {
    text-shadow: 0 0 0 rgba(0, 104, 250, 0.99);
}
#form1 input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 rgba(0, 104, 250, 0.99);
}
#review-content {    
	padding: 20px;
	margin-top: 15px;
    height: 200px;
    box-sizing: border-box;
    box-shadow: 0 5px 18px -7px rgba(0,0,0,1);
	resize: none;
}


.container {
	padding: 30px;
	display: block; 
	margin: 20px auto;
	box-sizing: border-box;
}

.container2 {
	display: block; 
	padding: 0 60px;
	margin: 30px auto;
	box-sizing: border-box;
}

#img-file-input-cont {
	padding: 0 60px;	
}

#img-file-input-cont #file-list {
	margin: 15px 0;
	padding: 15px; 
	overflow: auto;
	box-shadow: 0 5px 18px -7px rgba(0,0,0,1);
    border-radius: 10px;
}
#img-file-input-cont #file-list .filebox p {
    display: inline-block;
}
#img-file-input-cont #file-list .filebox .delete {
    margin-left: 10px;
    text-decoration: none;
}


#img-upload-label {	
	/* border-radius: 1px;
	border: 1px solid #D3D3D3; */
}


.preview-img {
	margin: 0 5px;
}

.img-preview {
	display: block;
	padding: 20px 0; 
	margin: auto;
	box-sizing: border-box;	
}


.btn-hover {
    font-weight: 600;
    color: #fff;
    cursor: pointer;
    text-align:center;
    border: none;
    background-size: 300% 100%;
    moz-transition: all .4s ease-in-out;
    -o-transition: all .4s ease-in-out;
    -webkit-transition: all .4s ease-in-out;
    transition: all .4s ease-in-out;
    text-align: center;
}

.btn-hover:hover {
    background-position: 100% 0;
    moz-transition: all .4s ease-in-out;
    -o-transition: all .4s ease-in-out;
    -webkit-transition: all .4s ease-in-out;
    transition: all .4s ease-in-out;
}

.btn-hover:focus {
    outline: none;
}

.round-btn {
    border-radius: 50px;
}

.btn {
	width: 100px;
	height: 40px;
	font-size: 17px;
}

.btn-hover.color-2 {
    background-image: linear-gradient(to right, #29323c, #485563, #2b5876, #4e4376);
    box-shadow: 0 4px 15px 0 rgba(45, 54, 65, 0.411);
}

.btn-hover.color-3 {
    background-image: linear-gradient(to right, #667eea, #764ba2, #6B8DD6, #8E37D7);
    box-shadow: 0 4px 15px 0 rgba(116, 79, 168, 0.75);
}

</style>
</head>


<body>

<form method="POST" action="../../prod/insert_review" class="mb-3" id="form1">
	<input type="hidden" id="buy-idx" name="buy_idx" value="${buy_idx}"/>
	<div class="container">
		<fieldset>
			<span class="text-bold" style="color: gray;">&nbsp;별점을 선택해주세요&nbsp;</span>
			<input type="radio" name="review_starnum" value="5" id="rate1"><label
				for="rate1" class="star-label">★</label>
			<input type="radio" name="review_starnum" value="4" id="rate2"><label
				for="rate2" class="star-label">★</label>
			<input type="radio" name="review_starnum" value="3" id="rate3"><label
				for="rate3" class="star-label">★</label>
			<input type="radio" name="review_starnum" value="2" id="rate4"><label
				for="rate4" class="star-label">★</label>
			<input type="radio" name="review_starnum" value="1" id="rate5"><label
				for="rate5" class="star-label">★</label>
		</fieldset>
		
		<div>
			<textarea rows="15" cols="65" class="col-auto form-control" type="text" id="review-content" name="review_content"
					  placeholder="후기를 남겨주세요"></textarea>
		</div>
	</div>
</form>

<div id="img-file-input-cont">	
	<form method="POST" action="../../prod/upload_image" id="form2" enctype="multipart/form-data">
        <input type="file" name="review-imgs" id="img-upload" onchange="addFile(this);" style="display:none;" multiple/>
		<label for="img-upload" id="img-upload-label" class="btn btn-hover color-2 round-btn">사진첨부</label>
		<span style="color: gray;">사진은 5 개까지 첨부할 수 있습니다.</span>
        <div id="file-list"></div>
        <div id="img-preview"></div>
		<input type="hidden" id="member-id" name="member_id" value="${sessionScope.loginId.member_id }"/>
		<input type="hidden" id="prod-idx" name="prod_idx" value="상품인덱스번호"/>
		<input type="hidden" id="file-paths" name="file_paths"/>
	</form>	
</div>


<div class="container2">
	<div id="btn-area">
		<button type="button" id="form1-submit-btn" class="btn btn-hover color-2 round-btn" form="form1">등록</button>
		&nbsp;
		<input type="reset" value="&nbsp;취소&nbsp;" class="btn btn-hover color-2 round-btn" form="form1"/>
	</div>
</div>


</body>
<script charset="EUC-KR" src="${path}/resources/js/product/product_review_write.js"></script>
</html>