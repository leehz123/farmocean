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
			
			<div class="ls_wrap">
				<div class="ls_div_subject">
					찜 갯수 순위 베스트 8
				</div>
                <!-- 상품 리스트 O -->
                <div class="ls_div">
	            	<c:if test="${joinlistcheck != 'empty'}">
	                    <c:forEach items="${joinlist}" var="joinlist">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
				                    		<!-- 상품 이미지 넣기 Test 
				                    		<a class="prod-img-out" href="<c:url value="/product/detail/${joinlist.prod_idx}"/>">
												<c:forEach items="${imgList}" var="imgList">  
													<c:set var = "imgURL" value = "${imgList.img_url}"/> 
													<c:choose>
														<c:when test="${fn:contains(imgURL, 'http')}">
															<div><img id="prod-img" src="${imgList.img_url}" alt="" /></div> 
											            </c:when>
														<c:otherwise>
															<div><img id="prod-img" src="/farmocean${imgList.img_url}" alt="" /></div>
														</c:otherwise>
													</c:choose>                            
												</c:forEach> 
											</a>
				                    		-->
		                    				<a class="prod-link" href="${path }/product/detail/${joinlist.prod_idx }">
		                    					<img src="${joinlist.img_url }" style="width: 100%; height: 80%;" />
		                    				</a>
		                    			</div>
		                    			<div class="ls_prod_name">
		                    				<a class="prod-link" href="${path }/product/detail/${joinlist.prod_idx }">${joinlist.prod_name}</a>
		                    			</div>
		                    			<div class="ls_prod_price">
		                    				${joinlist.prod_price}
		                    			</div>
		                    			<div class="ls_member_id">
		                    				${joinlist.member_nickName}
		                    			</div>
		                    			<!-- 
		                    			<div class="member_id_dropdown">
		                    				<button class="ls_member_id">
		                    				${joinlist.member_nickName}
		                    				</button>
		                    				<div class="member_id_dropdown_content">
						                      <a href="/farmocean/Sell/member/${joinlist.member_id}">판매자 페이지</a>
						                      <a href="/farmocean/mypage/sendMessages?id=${joinlist.member_id}" onclick="window.open(this.href,'_blank', 'width=500, height=600, scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;">쪽지 보내기</a>
						                      <a href="" onclick="followAct(this); return false;" data-seller="${joinlist.member_id}">팔로우</a>
						                      <a href="" onclick="reportAct(this); return false;" data-seller="${joinlist.member_id}">판매자 신고</a>
						                    </div>
		                    			</div>
		                    			 -->
		                    			<div class="ls_prod_sell" data-deadline="${joinlist.prod_sell_deadline}">
		                    			<!-- 
		                    			<fmt:formatDate pattern="yyyy/MM/dd" value="${joinlist.prod_sell_deadline}"/>
		                    			 -->
		                    			</div>
		                    		</div>
		                    	</div>	
		                    
	                    </c:forEach>
	            	</c:if>	 
                </div>
            </div>
			<br>
			<div class="ls_wrap">
				<div class="ls_div_subject">
					최신순 상품 10
				</div>
                <!-- 상품 리스트 O -->
                <div class="ls_div">
	            	<c:if test="${joinlistcheck2 != 'empty'}">
	                    <c:forEach items="${joinlist2}" var="joinlist2">
		                    <a href="${path }/product/detail/${joinlist2.prod_idx }">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
		                    				<img src="${joinlist2.img_url }" style="width: 100%; height: 80%;" />
		                    			</div>
		                    			<div class="ls_prod_name">
		                    				${joinlist2.prod_name}
		                    			</div>
		                    			<div class="ls_prod_price">
		                    				${joinlist2.prod_price}
		                    			</div>
		                    			<div class="ls_member_id">
		                    				${joinlist2.member_nickName}
		                    			</div>
		                    			<div class="ls_prod_sell" data-deadline="${joinlist2.prod_sell_deadline}"></div>
		                    		</div>
		                    	</div>	
		                    </a>
	                    </c:forEach>
	            	</c:if>	 
                </div>
            </div>
			<br>

			<div class="ls_wrap">
			<div class="ls_div_subject">
					인기순 상품 10
				</div>
                <!-- 상품 리스트 O -->
                <div class="ls_div">
	            	<c:if test="${joinlistcheck3 != 'empty'}">
	                    <c:forEach items="${joinlist3}" var="joinlist3">
		                    <a href="${path }/product/detail/${joinlist3.prod_idx }">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
		                    				<img src="${joinlist3.img_url }" style="width: 100%; height: 80%;" />
		                    			</div>
		                    			<div class="ls_prod_name">
		                    				${joinlist3.prod_name}
		                    			</div>
		                    			<div class="ls_prod_price">
		                    				${joinlist3.prod_price}
		                    			</div>
		                    			<div class="ls_member_id">
		                    				${joinlist3.member_nickName}
		                    			</div>
		                    			<div class="ls_prod_sell" data-deadline="${joinlist3.prod_sell_deadline}"></div>
		                    		</div>
		                    	</div>	
		                    </a>
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
			
			$(".ls_div").slick({
				dots: true,
				autoplay : true,
				autoplaySpeed: 5000,
				slidesToShow: 4,
				slidesToScroll: 4,
				prevArrow : "<button type='button' class='ls_div_content_prev'><</button>",		// 이전 화살표 모양 설정
				nextArrow : "<button type='button' class='ls_div_content_next'>></button>"		// 다음 화살표 모양 설정
			});
				
		});
		
		// 이미지 생성
		const imgOutA = document.getElementsByClassName('prod-img-out');
		var arr = new Array();
	  
		<c:forEach items="${mainImgList}" var="img">
			arr.push('${img}');    
		</c:forEach>

		for( var i = 0; i < imgOutA.length; i++ ){
			var out1 = imgOutA.item(i);
			if(arr[i].includes('http')) {
				out1.innerHTML = '<img class="prod-img" src="' + arr[i] + '" alt="">';      	    	  
			} else {
				out1.innerHTML = '<img class="prod-img" src="/farmocean' + arr[i] + '" alt="">';
			}   
		}
		

		
	</script>
	        <%@ include file="/resources/jspf/body_footer.jspf" %>

</body>
</html>