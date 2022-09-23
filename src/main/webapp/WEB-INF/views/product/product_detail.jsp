
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix= "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">

<!-- 슬릭 슬라이더 -->
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- slick 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css">

<link rel="stylesheet" href="${path}/resources/css/product/product_detail.css">

<title>상품 상세 페이지(여기에 상품 이름 들어감)</title>
<%@ include file="/resources/jspf/header.jspf" %>

<style type="text/css">
#slider-div {    
	width: 350px;
	height: 350px;
    <!-- background-color: yellow; -->
}

#slider-div img {
	width: 350px;
	height: 350px;
}




.slick-dots {

}

.slick-prev {
    left: 15px;
}

.slick-next {
    right: 15px;
}
    
</style>
</head>



<body>
<%@ include file="/resources/jspf/body_header.jspf" %>   
<input id="input-prod-idx" type="hidden" value="${product.prod_idx }"></input>
판매자 아이디(hidden으로 돌릴 것) : <input id="input-seller-id" type="text" value="${product.member_id}">
<input id="input-cate-idx" type="hidden" value="${product.cate_idx }" >

	
    <div id="prod-detail-container">
        <div id="prod-info1" class="prod-detail shadow" >
            <c:choose>
                <c:when test="${fn:length(prodImg) eq 0}">
                       <img id="prod-img" src="http://localhost:8888/farmocean/resources/upload/prod_img/34a828af-e0cc-4aa6-a807-769d253b56dc.jpg" alt="" />     		
                </c:when>
                <c:otherwise>
                    <div id="slider-div">
                        <c:forEach items="${prodImg}" var="img">
                         	<c:set var = "imgURL" value = "${img.img_url}"/>
                            <c:choose>
                            	<c:when test="${fn:contains(imgURL, 'http')}">
									<img class="prod-img hover01" src="${img.img_url}" alt="" />
                            	</c:when>
								<c:otherwise>
									<img class="prod-img hover01" src="/farmocean${img.img_url}" alt="" />
								</c:otherwise>
                            </c:choose>                            
                        </c:forEach>    
                    </div>
                </c:otherwise>
            </c:choose>
            
            <div id="prod-info1-simple">
                <div id="prod-info1-name"><h4>${product.prod_name }</h4></div>
                <div id="prod-info1-price">${product.prod_price }원</div>
                <div id="prod-info1-sell-status"></div>
                <div id="prod-info1-deadline"></div>
                <div id="prod-info1-deadline-timer" data-deadline="${product.prod_sell_deadline }" style="color: gray;"></div>
                
                <button id="buy-btn" class="btn-hover color-3 round-btn" onClick="fnWinOpen(290, 860, '<c:url value="/buy/prod/${product.prod_idx }" />');">상품 구매</button>
            	
                <button id="prod-heart-btn" data-text="찜등록">찜</button>
                
                <c:choose>
            		<c:when test="${sessionScope.loginId.member_id eq product.member_id || sessionScope.loginId.member_id eq 'sample63'}">
            			<button onclick="location.href='/farmocean/product/product_detail_edit/${product.prod_idx}';">상품 수정</button>
            			<button id="prod-delete-btn">상품 삭제</button>
            		</c:when>
            	</c:choose>
            </div>
        </div>        
		
        <div id="prod-seller" class="prod-detail shadow">
           	<a id="seller-img-a" href="/farmocean/Sell/member/${product.member_id}" alt="판매자 프로필 이미지" class="margin-little"><img id="seller-img" src="/farmocean/resources/image/mypage/${member.member_image}" alt="" /></a>
           	<div id="seller-detail">
                <a id="seller-nickname" href="/farmocean/Sell/member/${product.member_id}" class="seller-detail-part a-link margin-right-10" alt="판매자 닉네임">${member.member_nickName }</a>
                <c:choose>
                    <c:when test="${member.member_report eq 0 || member.member_report eq null}">
                        <span style="color:gray">판매자 신고 횟수 없음</span>
                    </c:when>
                    <c:otherwise>
                        <span style="color:#8E37D7; font-size: 15px;">판매자 누적 신고 횟수: ${member.member_report } 회</span>
                    </c:otherwise>
                </c:choose>
                
                <div id="seller-phone" class="seller-detail-part ">연락처 : ${sellerPhoneNum }</div><div id="seller-account" class="seller-detail-part">계좌 : ${sellerAccountNum }</div>
                <div>
                    <button id="seller-contact" class="btn-hover color-2 round-btn" name="/farmocean/mypage/sendMessages?id=${member.member_id}" onclick="window.open(this.name,'_blank', 'width=500, height=600, scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;">쪽지</button>
                    <button id="seller-follow" data-text="팔로우">팔로우</button>
                </div>
            </div>    
        </div>
            
            

        <div id="prod-detail-nav" class="prod-detail">
			<button id="prod-detail-nav-prod-info" class="nav-btn btn-hover color-1" onclick="onLinkClick(this);" data-scroll-to="prod-info2">상세정보</button>
			<button id="prod-detail-nav-prod-review" class="nav-btn btn-hover color-1" onclick="onLinkClick(this);" data-scroll-to="prod-review">후기</button>
			<button id="prod-detail-nav-prod-comment" class="nav-btn btn-hover color-1" onclick="onLinkClick(this);" data-scroll-to="prod-comment">문의</button>
        </div>

        <div id="prod-info2" class="prod-detail shadow">
            ${product.prod_info }
        </div>

        <div id="prod-review" class="prod-detail shadow"> <!--flex. column-->
            <div id="review-advice">
                <h3>상품 리뷰</h3>
                <div style="color: gray;">구매하신 상품에 대한 리뷰를 남겨주세요.</div>
            </div>
            <div id="review-write-popup-btn-area">
                <c:choose>
                    <c:when test="${sessionScope.loginId eq null }">
                        <button id="review-write-popup-btn" class="btn-hover color-2 round-btn" onclick="banReview();">리뷰 작성</button>
                    </c:when>
                    <c:otherwise>
                        <button id="review-write-popup-btn" class="btn-hover color-2 round-btn" onclick="permitReview();">리뷰 작성</button>
                    </c:otherwise>
                </c:choose>
            </div>
            <div id="review-container"></div>
            
			<nav aria-label="Page navigation example">
				<ul class="pagination" id="review-pagination-out">
				</ul>
			</nav>
        </div>

        <div id="prod-comment" class="prod-detail shadow"> 
            <div id="comment-advice">
                <h3>상품 문의</h3>
				<div style="color: gray;">상품에 대해 궁금하신 점이 있으시면 문의해주세요.
				<a href="/farmocean/mypage/sendMessages?id=${member.member_id}" onclick="window.open(this.href,'_blank', 'width=500, height=600, scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;">판매자에게 쪽지 보내기</a>
				</div>
            </div>
            <c:choose>
                <c:when test="${sessionScope.loginId eq null }"></c:when>
                <c:otherwise>
                    <div id="prod-comment-input">
                        <textarea id="prod-comment-textarea" placeholder="질문을 입력해주세요."></textarea><button id="prod-comment-input-btn" class="btn-hover color-2">입력</button>
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

    var seller = "<c:out value ='${product.member_id }'/>";    


    
    //판매자 팔로우 버튼
    $("#seller-follow").off().on('click', function() {
    
        if(this.getAttribute('data-text') == '팔로우') {
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
                        this.setAttribute('data-text', '언팔로우');
                    } else if(result.result == 2) {
                        alert("이미 팔로우 중입니다.");
                        this.setAttribute('data-text', '언팔로우');
                    } else if(result.result == 0) {
                        alert('로그인이 필요합니다.');
                    }
                }
            });

        } else if(this.getAttribute('data-text') == '언팔로우') {
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
                        this.setAttribute('data-text', '팔로우');
                    } else if(result.result == 2) {
                        alert("이미 언팔로우 중입니다.");
                        this.setAttribute('data-text', '팔로우');
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




