<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="${path}/resources/css/product/product_detail.css">
<script src="${path}/resources/js/product/product_detail.js"></script>
<title>상품 상세 페이지(여기에 상품 이름 들어감)</title>
</head>
<body>
    <!-- http://localhost:8888/farmocean/product/detail -->
    <h3>상품 상세페이지 입니다.</h3>
   
    <div id="prod-detail-container">
       
        <div id="prod-info1" class="prod-detail" >
            <!-- 상품 이미지, 이름, 가격, 판매여부, 찜, 남은 시간...  -->
            <div id="prod-img"></div>
            <div id="prod-info1-simple">
                <div id="prod-info1-name">직접 농사지은 성주참외 농가에서 직접 빠른 발송해드립니다.</div>
                <div id="prod-info1-price">50,000원</div>
                <div id="prod-info1-sell-status">판매중</div>
                <a href="#" id="prod-info1-heart">찜</a>
                <div id="prod-info1-deadline-timer">남은 시간 119일 6시간 56분 8초</div>
                <!-- out.div로 하면 될 듯 -->
            </div>
        </div>        

        <div id="prod-seller" class="prod-detail">
            상품 판매자 정보
        </div>

        <div id="prod-info2" class="prod-detail">
            상품 상세 내용
        </div>

        <div id="prod-review" class="prod-detail">
            상품 후기
        </div>

        <div id="prod-comment" class="prod-detail">
            상품 댓글
        </div>
 
    </div>

</body>
</html>