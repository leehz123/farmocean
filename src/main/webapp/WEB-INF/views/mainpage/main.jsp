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
	            	<c:if test="${listcheck != 'empty'}">
	                    <c:forEach items="${list}" var="list">
		                    <a href="${path }/product/detail/${list.prod_idx }">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
		                    				<img src="${list.img_url }" style="width: 100%; height: 80%;" />
		                    			</div>
		                    			<div class="abc">
		                    			<div class="ls_prod_name">
		                    				${list.prod_name}
		                    			</div>
		                    			<div class="ls_prod_price">
		                    				${list.prod_price}
		                    			</div>
		                    			<div class="ls_member_id">
		                    				${list.member_id}
		                    			</div>
		                    			<div class="ls_prod_sell" data-deadline="${list.prod_sell_deadline}"></div>
		                    			</div>
		                    		</div>
		                    	</div>	
		                    </a>
	                    </c:forEach>
	            	</c:if>	 
                </div>
            </div>
			<br><br><br>
			<div class="ls_wrap">
				<div class="ls_div_subject">
					최신순 상품 10
				</div>
                <!-- 상품 리스트 O -->
                <div class="ls_div">
	            	<c:if test="${listcheck2 != 'empty'}">
	                    <c:forEach items="${list2}" var="list2">
		                    <a href="${path }/product/detail/${list2.prod_idx }">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
		                    				<img src="${list2.img_url }" style="width: 100%; height: 80%;" />
		                    			</div>
		                    			<div class="ls_prod_name">
		                    				${list2.prod_name}
		                    			</div>
		                    			<div class="ls_prod_price">
		                    				${list2.prod_price}
		                    			</div>
		                    			<div class="ls_member_id">
		                    				${list2.member_id}
		                    			</div>
		                    			
		                    			<div class="ls_prod_sell" data-deadline="${list2.prod_sell_deadline}"></div>
		                    		</div>
		                    	</div>	
		                    </a>
	                    </c:forEach>
	            	</c:if>	 
                </div>
            </div>
			<br><br><br>
			<br><br><br>
			<div class="ls_wrap">
			<div class="ls_div_subject">
					인기순 상품 10
				</div>
                <!-- 상품 리스트 O -->
                <div class="ls_div">
	            	<c:if test="${listcheck2 != 'empty'}">
	                    <c:forEach items="${list3}" var="list3">
		                    <a href="${path }/product/detail/${list3.prod_idx }">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
		                    				<img src="${list3.img_url }" style="width: 100%; height: 80%;" />
		                    			</div>
		                    			<div class="ls_prod_name">
		                    				${list3.prod_name}
		                    			</div>
		                    			<div class="ls_prod_price">
		                    				${list3.prod_price}
		                    			</div>
		                    			<div class="ls_member_id">
		                    				${list3.member_id}
		                    			</div>
		                    			<div class="ls_prod_sell" data-deadline="${list3.prod_sell_deadline}"></div>
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
		
		// gnb_area 로그아웃 버튼
		$("gnb_logout_btn").click(function() {
			// alert("버튼 작동");
			// ajax jquery
			$.ajax({
				type:"POST",
				url:"/farmocean/member/logout.do",
				success:function(data) {
					alert("로그아웃 성공");
					document.location.reload();
				}
			});
		});
		
	</script>

</body>
</html>