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
    font-size: 2em; /* �� ũ�� ���� */
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
	<h3>��ǰ�����ۼ� �˾�â</h3>

<!-- 
�ܰ� 1) 
[prod_review]
review_idx, prod_idx, member_id, review_content, review_date, review_starnum
prod_review�� ��Ʈ�ѷ����� ���ε� �ؼ� �ް� name ���� �ʵ尪�� ��ġ��Ű��

�ܰ� 2) 
[review_picture]
review_picture_idx, review_idx, review_picture_url
review_picture�� ���ε� ����? �ٵ� ������ �ø� �Ÿ�.. ���ε� �� ���� �ʳ�?
��� req.getParameter('����')���� �������� �� �� ������

http://localhost:8888/farmocean/product/product_review_write/prod/insert_review
 -->

<button>�̹��� ���ε�</button> <br />
<form action="../../prod/insert_review" class="mb-3" name="myform" id="myform" method="post">
	���̵� : <input type="text" id="member-id-input" name="member_id" value="${sessionScope.loginId.member_id }"/><br />
	�г��� : <input type="text" id="member-nickname-input" name="review_content"/><br />
	��ǰidx : <input type="text" id="prod-idx-input" name="prod_idx" value="��ǰ�ε�����ȣ"/><br />
	
	<fieldset>
		<span class="text-bold">&nbsp;������ �������ּ���&nbsp;</span>
		<input type="radio" name="review_starNum" value="5" id="rate1"><label
			for="rate1">��</label>
		<input type="radio" name="review_starNum" value="4" id="rate2"><label
			for="rate2">��</label>
		<input type="radio" name="review_starNum" value="3" id="rate3"><label
			for="rate3">��</label>
		<input type="radio" name="review_starNum" value="2" id="rate4"><label
			for="rate4">��</label>
		<input type="radio" name="review_starNum" value="1" id="rate5"><label
			for="rate5">��</label>
	</fieldset>
	
	<div>
		<textarea rows="15" cols="65" class="col-auto form-control" type="text" id="review-contents" name="review_content"
				  placeholder="�ı⸦ �����ּ���"></textarea>
	</div>
</form>
<div id="btn-area"><input id="submit-btn" type="submit" value="���"/> <input type="reset" value="���" form="myform"/></div>

 




</body>
<script charset="EUC-KR" src="${path}/resources/js/product/product_review_write.js"></script>
</html>