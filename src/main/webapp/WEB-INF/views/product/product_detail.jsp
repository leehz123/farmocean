
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

<button id="test">test</button>

    <!-- http://localhost:8888/farmocean/product/detail/2525 -->
<!-- 
      
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

 -->
    <!-- <c:choose>
		<c:when test="${sessionScope.loginId eq null }">
		   로그인 후 댓글 등록, 후기 등록 가능
		</c:when>
		<c:otherwise>
		   ID : [${sessionScope.loginId.member_id }] 
		   이름 : [${sessionScope.loginId.member_name}]
		   비번 : [${sessionScope.loginId.member_pw}]
		</c:otherwise>
    </c:choose>
 -->

	
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
            
            <!-- <table id="prod-info-simple">
                <tr><td id="prod-info1-name"></td></tr>
                <tr><td id="prod-info1-price"></td></tr>
                <tr><td id="prod-info1-sell-status"></td></tr>
                <tr><td id="prod-info1-deadline-timer"></td></tr>
                <tr><td><button id="prod-heart-btn" data-text="찜등록">찜</button>&nbsp;<button  onClick="fnWinOpen(290, 860, '<c:url value="/buy/prod/${product.prod_idx }" />');">상품 구매</button></td></tr>
            </table> -->
            
            <div id="prod-info1-simple">
                <div id="prod-info1-name">${product.prod_name }</div>
                <div id="prod-info1-price">${product.prod_price }원</div>
                <div id="prod-info1-sell-status"></div>
                <div id="prod-info1-deadline"></div>
                <div id="prod-info1-deadline-timer" data-deadline="${product.prod_sell_deadline }"></div>
                <button id="prod-heart-btn" data-text="찜등록">찜</button>
                <button  onClick="fnWinOpen(290, 860, '<c:url value="/buy/prod/${product.prod_idx }" />');">상품 구매</button>
            </div>
        </div>        
		
        <div id="prod-seller" class="prod-detail">
            <!-- 상품 판매자 정보 -->
           	<img id="seller-img" src="/farmocean/resources/image/mypage/${member.member_image}" alt="" />
           	<table id="seller-table">
           		<tr><td id="seller-nickname" class="seller-td">${member.member_nickName }</td></tr>
           		<tr><td id="seller-phone" class="seller-td">연락처 : ${member.member_phoneNum }</td></tr>
           		<tr><td id="seller-account" class="seller-td">계좌 : ${member.member_accountNum }</td></tr>
                <tr><td><button id="seller-contact">쪽지</button>&nbsp;&nbsp;<button id="seller-follow" data-text="팔로우">팔로우</button></td></tr>
            </table>
        </div>

        <div id="prod-detail-nav" class="prod-detail">
			<button id="prod-detail-nav-prod-info" class="nav-btn" onclick="onLinkClick(this);" data-scroll-to="prod-info2">상세정보</button>
			<button id="prod-detail-nav-prod-review" class="nav-btn" onclick="onLinkClick(this);" data-scroll-to="prod-review">후기</button>
			<button id="prod-detail-nav-prod-comment" class="nav-btn" onclick="onLinkClick(this);" data-scroll-to="prod-comment">문의</button>
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
			<!-- 
            <div id="prod-review-picture-container"> 
                <div id="prod-review-picture1" class="prod-review-picture"></div>
                <div id="prod-review-picture-more" class="prod-review-picture"></div>
            </div>
			 -->            
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

    <script>
        
    //var loginId = "<c:out value ='${sessionScope.loginId.member_id }'/>";
    var seller = "<c:out value ='${product.member_id }'/>";    

    document.getElementById('test').addEventListener('click', (e)=> {
        
    });

    //판매자 팔로우 버튼
    const followBtn = document.getElementById('seller-follow');
    followBtn.addEventListener('click', (e)=> {
        
        if(followBtn.getAttribute('data-text') == '팔로우') {
            const xhttp15 = new XMLHttpRequest();
            xhttp15.open('POST', '/farmocean/follow');
            var follow = {
                followee_id : seller
            }
            xhttp15.setRequestHeader('Content-Type', 'application/json;characterset=UTF-8');
            xhttp15.send(JSON.stringify(follow));
            xhttp15.addEventListener('readystatechange', (e)=> {
                const readyState = e.target.readyState;
                if(readyState == 4) {
                    const responseText = e.target.responseText;
                    const result = JSON.parse(responseText);
                    if(result.result == 1) {
                        alert('판매자를 팔로우 하였습니다.');
                        followBtn.setAttribute('data-text', '언팔로우');
                    } else if(result.result == 2) {
                        alert("이미 팔로우 중입니다.");
                        followBtn.setAttribute('data-text', '언팔로우');
                    } else if(result.result == 0) {
                        alert('로그인이 필요합니다.');
                    }
                }
            });

        } else if(followBtn.getAttribute('data-text') == '언팔로우') {
            const xhttp16 = new XMLHttpRequest();
            xhttp16.open('DELETE', '/farmocean/unfollow');
            var follow = {
                followee_id : seller
            }
            xhttp16.setRequestHeader('Content-Type', 'application/json;characterset=UTF-8');
            xhttp16.send(JSON.stringify(follow));
            xhttp16.addEventListener('readystatechange', (e)=> {
                const readyState = e.target.readyState;
                if(readyState == 4) {
                    const responseText = e.target.responseText;
                    const result = JSON.parse(responseText);
                    if(result.result == 1) {
                        alert('판매자를 언팔로우 하였습니다.');
                        followBtn.setAttribute('data-text', '팔로우');
                    } else if(result.result == 2) {
                        alert("이미 언팔로우 중입니다.");
                        followBtn.setAttribute('data-text', '팔로우');
                    } else if(result.result == 0) {
                        alert('로그인이 필요합니다.');
                    }
                }
            });        
        }
    });



    </script>



<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>

	<script charset="EUC-KR" src="${path}/resources/js/product/prod_detail.js"></script>

</html>




