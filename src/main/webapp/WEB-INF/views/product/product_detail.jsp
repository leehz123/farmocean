
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="${path}/resources/css/product/product_detail.css">

<title>상품 상세 페이지(여기에 상품 이름 들어감)</title>
<%@ include file="/resources/jspf/header.jspf" %>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>   
<input id="input-prod-idx" type="hidden" value="${product.prod_idx }"></input>
판매자 아이디 : <input id="input-seller-id" type="text" value="${product.member_id}">


    <!-- http://localhost:8888/farmocean/product/detail/2525 -->
	<a href="#"></a>      
    <c:choose>
		<c:when test="${sessionScope.loginId eq null }">
		   로그인 후 이용 가능합니다
		</c:when>
		<c:otherwise>
		   ID : [${sessionScope.loginId.member_id }] 
		   이름 : [${sessionScope.loginId.member_name}]
		   비번 : [${sessionScope.loginId.member_pw}]
		</c:otherwise>
    </c:choose>



    <a href="#" id="login">로긴</a>
    <a href="#" id="logout">로가웃</a>
	
	<a id="test-a" href=""></a>
	
    <div id="prod-detail-container">
		
        <div id="prod-info1" class="prod-detail" >
            <!-- 상품 이미지, 이름, 가격, 판매여부, 찜, 남은 시간(카운트다운 어떻게 할 지 고민)...  -->
            <c:choose>
            	<c:when test="${prodImg.img_url eq null}">
       				<img id="prod-img" src="http://localhost:8888/farmocean/resources/upload/prod_img/34a828af-e0cc-4aa6-a807-769d253b56dc.jpg" alt="" />     		
            	</c:when>
            	<c:otherwise>
            		<img id="prod-img" src="${prodImg.img_url}" alt="" />
            	</c:otherwise>
            </c:choose>
            <div id="prod-info1-simple">
                <div id="prod-info1-name">${product.prod_name }</div>
                <div id="prod-info1-price">${product.prod_price }원</div>
                <div id="prod-info1-sell-status">${product.prod_sell }</div>
                <a href="#" id="prod-info1-heart">찜</a>
                <div id="prod-info1-deadline-timer">남은 시간 119일 6시간 56분 8초</div>
                <!-- out.div로 하면 될 듯 -->
            </div>
        </div>        
		
        <div id="prod-seller" class="prod-detail">
            <!-- 상품 판매자 정보 -->
           	<img id="seller-img" src="${member.member_image}" alt="" />
           	<table id="seller-table">
           		<tr><td id="seller-nickname" class="seller-td">${member.member_nickName }</td></tr>
           		<tr><td id="seller-phone" class="seller-td">연락처 : ${member.member_phoneNum }</td></tr>
           		<tr><td id="seller-account" class="seller-td">계좌 : ${member.member_accountNum }</td></tr>
           	</table>
           	<a href="#" id="seller-contact">쪽지 보내기</a>
        </div>

        <div id="prod-detail-nav" class="prod-detail">
            <a href="#prod-info2" id="prod-detail-nav-prod-info">상세 정보</a>
            <a href="#prod-review" id="prod-detail-nav-prod-review">후기</a>
            <a href="#prod-comment" id="prod-detail-nav-prod-comment">주문/문의</a>
        </div>

        <div id="prod-info2" class="prod-detail">
            <!-- 상품 상세 내용 (.innerHTML로 prod_detail 의 prod-content 불러오면 됨)
            <br />+ 글자 수에 따라 높이 조절, padding 설정 -->
            ${product.prod_info }
        </div>

        <div id="prod-review" class="prod-detail"> <!--flex. column-->
            <!-- 상품 후기
            <br />아예 틀을 5개 만들어놓고 거기에 해당하는 정보를 불러오기
            <br>리뷰에 등록된 사진이 없을 경우 visibility hidden으로. 등록된 사진 갯수가 2개 이상일 경우 사진개수 표시
            <br>리뷰사진 목록은 컨테이너 연한 배경, 개수에 따라 hidden, visible. 맨 마지막에 +리뷰사진개수 표시(근데 이것도 더 볼 사진 없으면 hidden)
            <br>그리고 후기 작성 페이지도 생각해놓기 (사진 등록) -->
           	<div id="review-write-popup-btn-area"><button id="review-write-popup-btn">리뷰 작성</button></div>
            <div id="prod-review-picture-container"> <!--flex. row-->
                <div id="prod-review-picture1" class="prod-review-picture"></div>
                <div id="prod-review-picture-more" class="prod-review-picture"></div>
            </div>
            
            <div id="review-container"></div>
            
			<nav aria-label="Page navigation example">
				<ul class="pagination" id="review-pagination-out">
				</ul>
			</nav>
        </div>

        <div id="prod-comment" class="prod-detail"> 
            
            <c:choose>
                <c:when test="${sessionScope.loginId eq null }"></c:when>
                <c:otherwise>
                    <div id="prod-comment-input">
                        <textarea id="prod-comment-textarea"></textarea><button id="prod-comment-input-btn">입력</button>
 	                    <div id="comment-secret-div"><input id="comment-secret" type="checkbox"><label for="comment-secret">&nbsp;비밀글</label></div>
                    </div>
                </c:otherwise>
            </c:choose>

			<div id="comment-container">
			</div>
            
			<nav aria-label="Page navigation example">
				<ul class="pagination" id="comment-pagination-out">
		
				</ul>
			</nav>
            <div id="no-comment"></div>
        </div>
    </div>


<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>

	<script charset="EUC-KR" src="${path}/resources/js/product/prod_detail.js"></script>

</html>




