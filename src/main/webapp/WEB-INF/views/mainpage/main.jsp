<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		                    <a href="${path }/product/detail/${joinlist.prod_idx }">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
		                    				<img src="${joinlist.img_url }" style="width: 100%; height: 80%;" />
		                    			</div>
		                    			<div class="ls_prod_name">
		                    				${joinlist.prod_name}
		                    			</div>
		                    			<div class="ls_prod_price">
		                    				${joinlist.prod_price}
		                    			</div>
		                    			<div class="ls_member_id">
		                    				${joinlist.member_nickName}
		                    			</div>
		                    			<div class="ls_prod_sell" data-deadline="${joinlist.prod_sell_deadline}"></div>
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
		                    				${joinlist2.member_id}
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

	        <%@ include file="/resources/jspf/body_footer.jspf" %>

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
		

		
	</script>

</body>
</html>