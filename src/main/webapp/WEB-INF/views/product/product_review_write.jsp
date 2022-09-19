<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ǰ ���� �ۼ� �˾�â</title>
<%@ include file="/resources/jspf/header.jspf" %>

<style>

* {
	font-size: 3vh;
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
    font-size: 2em; /* �� ũ�� ���� */
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
    height: 40vh;
    box-sizing: border-box;
    border: solid 1.5px #D3D3D3;
    border-radius: 5px;
    resize: none;
}

.container {
	padding 10vh 10vw;
	display: block; 
	width: 90vw;
	margin: 5vh auto;
	box-sizing: border-box;
}

#img-file-input-cont #file-list {
    overflow: auto;
    border: solid 1.5px #D3D3D3;
    border-radius: 5px;
    padding: 2vh;
}
#img-file-input-cont #file-list .filebox p {
    margin-top: 0.5vh;
    display: inline-block;
}
#img-file-input-cont #file-list .filebox .delete {
    color: rgba(0, 104, 250, 0.99);
    margin-left: 1vh;
    text-decoration: none;
}

#img-upload-label {	
	border-radius: 1px;
	border: 1px solid #D3D3D3;
}

.preview-img {
	margin: 0 0.3vh;
}

</style>
</head>


<body>

<form method="POST" action="../../prod/insert_review" class="mb-3" id="form1">
	<input type="hidden" id="buy-idx" name="buy_idx" value="${buy_idx}"/>
	<div class="container">
		<fieldset>
			<span class="text-bold">&nbsp;������ �������ּ���&nbsp;</span>
			<input type="radio" name="review_starnum" value="5" id="rate1"><label
				for="rate1" class="star-label">��</label>
			<input type="radio" name="review_starnum" value="4" id="rate2"><label
				for="rate2" class="star-label">��</label>
			<input type="radio" name="review_starnum" value="3" id="rate3"><label
				for="rate3" class="star-label">��</label>
			<input type="radio" name="review_starnum" value="2" id="rate4"><label
				for="rate4" class="star-label">��</label>
			<input type="radio" name="review_starnum" value="1" id="rate5"><label
				for="rate5" class="star-label">��</label>
		</fieldset>
		
		<div>
			<textarea rows="15" cols="65" class="col-auto form-control" type="text" id="review-content" name="review_content"
					  placeholder="�ı⸦ �����ּ���"></textarea>
		</div>
	</div>
</form>


<button id="show-upload-img-btn">���� ÷���ϱ�</button>
<div id="img-file-input-cont" class="container" style="visibility: hidden;">	
	<form method="POST" action="../../prod/upload_image" id="form2" enctype="multipart/form-data">
        <input type="file" name="review-imgs" id="img-upload" onchange="addFile(this);" style="display:none;" multiple/>
		<label for="img-upload" id="img-upload-label">&nbsp;�̹��� �߰�&nbsp;</label>
			�̹����� 5 ������ ÷���� �� �ֽ��ϴ�.	        
        <div id="file-list"></div>
        <div id="img-preview" class="container"></div>

		<input type="hidden" id="member-id" name="member_id" value="${sessionScope.loginId.member_id }"/><br />
		<input type="hidden" id="prod-idx" name="prod_idx" value="��ǰ�ε�����ȣ"/><br />
		<input type="hidden" id="file-paths" name="file_paths"/>
	</form>	
	<button type="submit" id="img-submit-btn">�̹��� ���ε�</button>
</div>	

<div id="uploaded-img" class="container"></div>


<div class="container">
	<div id="btn-area">
		<button type="button" id="form1-submit-btn" form="form1">���</button>
		<!-- <input type="submit" value="���" form="form1"/> -->
		<input type="reset" value="&nbsp;���&nbsp;" form="form1"/>
	</div>
</div>


</body>
<script charset="EUC-KR" src="${path}/resources/js/product/product_review_write.js"></script>
</html>