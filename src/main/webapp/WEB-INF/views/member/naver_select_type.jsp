<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<%@ include file="/resources/jspf/header.jspf"%>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf"%>
	<button id="naver_join_buyer">구매자 회원가입</button>
	<button id="naver_join_seller">판매자 회원가입</button>
	<script>
	const naver_buyer_btn = document.getElementById('naver_join_buyer');
	const naver_seller_btn = document.getElementById('naver_join_seller');

	naver_buyer_btn.addEventListener('click',(e)=>{
	    window.location.href='/farmocean/member/naverBuyerJoin';
	});
	
	naver_seller_btn.addEventListener('click',(e)=>{
		window.location.href='/farmocean/member/naverSellerJoin';
	});
	</script>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>

</html>