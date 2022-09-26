<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Farmocean</title>
<%@ include file="/resources/jspf/header.jspf" %>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous">
</script>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script src="<c:url value="/resources/js/mainpage/sell_deadline.js"/>"></script>
</head>
<body>

	<%@ include file="/resources/jspf/body_header.jspf" %>
			

				<div class="slide_div_wrap">
				<div class="slide_div">
					<div>
						<a>
							<img src="${path }/resources/image/mainpage/slide_01.jpg" style="max-width: 100%; height: auto;">
						</a>
					</div>
					<div>
						<a>
							<img src="${path }/resources/image/mainpage/slide_02.jpg" style="max-width: 100%; height: auto;">
						</a>
					</div>
					<div>
						<a>
							<img src="${path }/resources/image/mainpage/slide_03.jpg" style="max-width: 100%; height: auto;">
						</a>
					</div>			
				</div>	
			</div>
			
			
			<br> <br>
			<div class="ls_wrap2">
			<div class="ls_div_subject_main">
				<div class="ls_div_subject">
					찜 BEST
				</div>
				<div class="ls_div_subject2">
					| 찜 베스트 상품
				</div>
			</div>
                <!-- 상품 리스트 O -->
                <div class="ls_div2" id="product-list2">
	            	<c:if test="${joinlistcheck != 'empty'}">
	                    <c:forEach items="${joinlist}" var="joinlist">
		                    		<div class="ls_div_content2">
		                    			<a href="${path }/product/detail/${joinlist.prod_idx }">
		                    			<div class="image_wrap">
		                    				<c:set var = "imgURL" value = "${joinlist.img_url }"/>
											<c:choose>
												<c:when test="${fn:contains(imgURL, 'http')}">
													<div><img id="prod-img" src="${imgURL}" alt="" style="width: 200px; height: 200px; border-radius: 7px;"/></div>
		                            			</c:when>
												<c:otherwise>
													<div><img id="prod-img" src="/farmocean${imgURL}" alt="" style="width: 200px; height: 200px; border-radius: 7px;" /></div>
												</c:otherwise>
											</c:choose>  
		                    			</div>
		                    			</a>
		                    			<a href="${path }/product/detail/${joinlist.prod_idx }">
		                    			<div class="ls_prod_name">
		                    				${joinlist.prod_name}
		                    			</div>
		                    			</a>
		                    			<div class="ls_prod_price">
		                    				${joinlist.prod_price}
		                    			</div>
		                    			<a href="/farmocean/Sell/member/${joinlist.member_id}">
		                    			<div class="ls_member_id">
		                    				${joinlist.member_nickName}
		                    			</div>
		                    			</a>
		                    			<!-- 
		                    			 <div class="prod-info dropdown">
						                    <button class="nickname-ajax ls_member_id dropbtn prod-seller">${joinlist.member_nickName}</button>
						                    <div class="dropdown-content">
						                      <a href="/farmocean/Sell/member/${joinlist.member_id}">판매자 페이지</a>
						                      <a href="/farmocean/mypage/sendMessages?id=${joinlist.member_id}" onclick="window.open(this.href,'_blank', 'width=500, height=600, scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;">쪽지 보내기</a>
						                      <a href="" onclick="followAct(this); return false;" data-seller="${joinlist.member_id}">팔로우</a>
						                      <a href="" onclick="reportAct(this); return false;" data-seller="${joinlist.member_id}">판매자 신고</a>
						                    </div>
					                  	</div>
		                    			 -->
		                    			<div class="ls_prod_sell" data-deadline="${joinlist.prod_sell_deadline}"></div>
		                    		</div>
	                    </c:forEach>
	            	</c:if>	 
                </div>
            </div>
            <hr>
            
			<div class="ls_wrap">
			<div class="ls_div_subject_main">
				<div class="ls_div_subject">
					NEW
				</div>
				<div class="ls_div_subject2">
					| 매일매일 업데이트 되는 최신 상품
				</div>
			</div>
                <!-- 상품 리스트 O -->
                <div class="ls_div" id="product-list">
	            	<c:if test="${joinlistcheck2 != 'empty'}">
	                    <c:forEach items="${joinlist2}" var="joinlist2">
		                    		<div class="ls_div_content">
				                    <a href="${path }/product/detail/${joinlist2.prod_idx }">
		                    			<div class="image_wrap">
		                    				<c:set var = "imgURL" value = "${joinlist2.img_url }"/>
											<c:choose>
												<c:when test="${fn:contains(imgURL, 'http')}">
													<div><img id="prod-img" src="${imgURL}" alt="" style="width: 200px; height: 200px; border-radius: 7px;"/></div>
		                            			</c:when>
												<c:otherwise>
													<div><img id="prod-img" src="/farmocean${imgURL}" alt="" style="width: 200px; height: 200px; border-radius: 7px;" /></div>
												</c:otherwise>
											</c:choose>  
		                    			</div>
				                    </a>
				                    <a href="${path }/product/detail/${joinlist2.prod_idx }">
		                    			<div class="ls_prod_name">
		                    				${joinlist2.prod_name}
		                    			</div>
		                    		</a>
		                    			<div class="ls_prod_price">
		                    				${joinlist2.prod_price}
		                    			</div>
		                    			<a href="/farmocean/Sell/member/${joinlist2.member_id}">
		                    			<div class="ls_member_id">
		                    				${joinlist2.member_nickName}
		                    			</div>
		                    			</a>
		                    			<div class="ls_prod_sell" data-deadline="${joinlist2.prod_sell_deadline}"></div>
		                    		</div>
	                    </c:forEach>
	            	</c:if>	 
                </div>
            </div>
            <hr>
            
			<div class="ls_wrap" id="list-paging-set">
			<div class="ls_div_subject_main">
				<div class="ls_div_subject">
					BEST
				</div>
				<div class="ls_div_subject2">
					| 지금 가장 인기있는 상품
				</div>
			</div>
                <!-- 상품 리스트 O -->
                <div class="ls_div" id="product-list">
	            	<c:if test="${joinlistcheck3 != 'empty'}">
	                    <c:forEach items="${joinlist3}" var="joinlist3">
		                    		<div class="ls_div_content">
				                    <a href="${path }/product/detail/${joinlist3.prod_idx }">
		                    			<div class="image_wrap">
											<c:set var = "imgURL" value = "${joinlist3.img_url }"/>
											<c:choose>
												<c:when test="${fn:contains(imgURL, 'http')}">
													<div><img id="prod-img" src="${imgURL}" alt="" style="width: 200px; height: 200px; border-radius: 7px;"/></div>
		                            			</c:when>
												<c:otherwise>
													<div><img id="prod-img" src="/farmocean${imgURL}" alt="" style="width: 200px; height: 200px; border-radius: 7px;" /></div>
												</c:otherwise>
											</c:choose>                            
		                    			<!-- 
		                    				<img src="${joinlist3.img_url }" style="width: 100%; height: 80%;" />
		                    			 -->
		                    			</div>
				                    </a>
				                    <a href="${path }/product/detail/${joinlist3.prod_idx }">
		                    			<div class="ls_prod_name">
		                    				${joinlist3.prod_name}
		                    			</div>
		                    		</a>
		                    			<div class="ls_prod_price">
		                    				${joinlist3.prod_price}
		                    			</div>
		                    			<a href="/farmocean/Sell/member/${joinlist3.member_id}">
		                    			<div class="ls_member_id">
		                    				${joinlist3.member_nickName}
		                    			</div>
		                    			</a>
		                    			<!-- 
		                    			<div class="prod-info dropdown">
						                    <button name="${joinlist3.member_nickName}" class="nickname-ajax ls_member_id dropbtn prod-seller">${joinlist3.member_nickName}</button>
						                    <div class="dropdown-content">
						                      <a href="/farmocean/Sell/member/${joinlist3.member_id}">판매자 페이지</a>
						                      <a href="/farmocean/mypage/sendMessages?id=${joinlist3.member_id}" onclick="window.open(this.href,'_blank', 'width=500, height=600, scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;">쪽지 보내기</a>
						                      <a href="" onclick="followAct(this); return false;" data-seller="${joinlist3.member_id}">팔로우</a>
						                      <a href="" onclick="reportAct(this); return false;" data-seller="${joinlist3.member_id}">판매자 신고</a>
						                    </div>
					                  	</div>
		                    			 -->
		                    			<div class="ls_prod_sell" data-deadline="${joinlist3.prod_sell_deadline}"></div>
		                    		</div>
	                    </c:forEach>
	            	</c:if>	 
                </div>
            </div>


	<script>
	
		$(document).ready(function() {
			$(".slide_div").slick({
				dots: true,
				autoplay : true,
				autoplaySpeed: 5000
			});
			
			/*
			*/
			$(".ls_div2").slick({
				dots: true,
				autoplay : true,
				autoplaySpeed: 5000,
				slidesToShow: 5,
				slidesToScroll: 5,
				prevArrow : "<button type='button' class='ls_div_content_prev'><</button>",		// 이전 화살표 모양 설정
				nextArrow : "<button type='button' class='ls_div_content_next'>></button>"		// 다음 화살표 모양 설정
			});
				
		});
		

		
	</script>
	        <%@ include file="/resources/jspf/body_footer.jspf" %>

</body>
</html>