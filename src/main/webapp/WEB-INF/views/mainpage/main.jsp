<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="${path }/resources/css/mainpage/main.css" />
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous">
</script>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
</head>
<body>

	<div class="wrapper">
		<div class="wrap">
			<div class="top_gnb_area">
				<ul class="list">
					<!-- 로그인 X -->
					<c:if test = "${member == null }">
						<li>
							<a href="${path }/member/login">로그인</a>
						</li>
						<li>
							<a href="${path }/member/join">회원가입</a>
						</li>					
					</c:if>
					<!-- 로그인 O (member 임의 지정) -->
					<c:if test = "${member != null }">
						<!-- 판매자 계정 (로그인 변수 보고 바꿀 것.) -->
						<c:if test = "${member.admin == 1 }">
							<li><a href="${path }/mainpage/seller/main">판매자 페이지</a></li> <!-- 판매자로 로그인 시 상품 등록 페이지 접속 가능 -->
						</c:if>
						<li>
							<a id="gnb_logout_btn">로그아웃</a>
						</li>
						<li>
							마이페이지
						</li>
						<li>
							장바구니
						</li>
					</c:if>
					<li>
						고객센터
					</li>
					<!-- 판매자로 로그인 해야 보이지만 잠시만 로그인X일 때 임의로 설정 (상품 등록 페이지) -->
					<li><a href="${path }/mainpage/prod_reg/main">판매자 페이지</a></li>
				</ul>
			</div>
			<div class="top_area">
				<div class="logo_area">
					<h1>logo area</h1>
				</div>
				<div class="search_area">
					<div class="search_wrap">
                		<form id="searchForm" action="${path }/mainpage/search" method="get">
                			<div class="search_input">
                				<select name="type">
                					<option value="T">상품 이름</option>
                					<option value="A">판매자 ID</option>
                				</select>
                				<input type="text" name="keyword">
                    			<button class='btn search_btn'>검 색</button>                				
                			</div>
                		</form>
                	</div>
				</div>
				<div class="login_area">
					<div class="login_btn"><a href="${path }/member/login">로그인</a></div>
					<span><a href="${path }/member/join">회원가입</a></span>
				</div>
				<div class="clearfix"></div>				
			</div>
			<div class="navi_bar_area">
				<div class="navi_bar_area">
					<div class="dropdown">
						<button class="dropbtn">식량작물
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates2 }" var="cate">
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">특용작물
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates7 }" var="cate">
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">과일류
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates1 }" var="cate">
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">채소류
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates6 }" var="cate">
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">수산물
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates5 }" var="cate">
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">뿌리류?
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates3 }" var="cate">
							<!-- 카테고리 조회 페이지로 경로 수정하면 됨 -->
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">건강즙?
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates4 }" var="cate">
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			
			<div class="content_area">
				<div class="slide_div_wrap">
				<div class="slide_div">
					<div>
						<a>
							<img src="../resources/img/slide_01.jpg" style="max-width: 100%; height: auto;">
						</a>
					</div>
					<div>
						<a>
							<img src="../resources/img/slide_02.jpg" style="max-width: 100%; height: auto;">
						</a>
					</div>
					<div>
						<a>
							<img src="../resources/img/slide_03.jpg" style="max-width: 100%; height: auto;">
						</a>
					</div>			
				</div>	
			</div>

			<!-- 할 것들
			<div>
				<br><br> 
				<h1>
					정갈하게 디자인 or 이미지도 출력 멤버 아이디도 추가하셈 <br><br> 
					!!검색하기 완료 or 카테고리 별로 클릭하면 카테고리 리스트 출력하게 만들기!!<br><br> 
					아 인기키워드 8개도 있음<br> <br> 
				</h1>
				<h3>
					대충 찜은 슬라이드처럼 넘어가게 하고 <br> 
					최신순 인기순은 버튼 눌러서 접속하면 될듯 a태그 달던가 <br> 
					처음에는 최신순 리스트 나오게 하고<br> 
					인기 키워드는 검색 밑에 있으면 되고<br> 
					카테고리는 누르면 카테 리스트 나오는데 그럴 때마다 슬라이드 안 움직이고 고정 시켜서 가리는 걸로 가는게 깔끔할 듯<br> 
					아 그리고 카테고리 좀 많아서 수산으로 가면 뿌리랑 건강 밑으로 내려가니까 그부분도 고정시켜야할 듯<br> 
					그냥 카테 리스트 일자로 쫙 나오게 하면 될 듯<br> 
				</h3>
			</div>
			
			<br><br><br>
			-->
			
			<div class="ls_wrap">
				<div class="ls_div_subject">
					찜 갯수 순위 베스트 8
				</div>
                <!-- 상품 리스트 O -->
                <div class="ls_div">
	            	<c:if test="${listcheck != 'empty'}">
	                    <c:forEach items="${list}" var="list">
		                    <a href="${path }/prod/prodDetail/${list.prod_idx }">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
		                    			</div>
		                    			<!-- product dto에 member_id 넣어서 출력하기 -->
		                    			<div class="ls_member_id">
		                    				<c:out value="${list.member_id}"></c:out>
		                    			</div>
		                    			<!--  
		                    			<div class="ls_prod_idx">
		                    				<c:out value="${list.prod_idx}"></c:out>
		                    			</div>
		                    			-->
		                    			<div class="ls_prod_name">
		                    				<c:out value="${list.prod_name}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_price">
		                    				<c:out value="${list.prod_price}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_stock">
		                    				<c:out value="${list.prod_stock}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_sell">
		                    				<c:out value="${list.prod_sell}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_sell_deadline">
		                    				<c:out value="${list.prod_sell_deadline}"></c:out>
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
		                    <a href="${path }/prodDetail/${list2.prod_idx }">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
		                    			</div>
		                    			<!-- product dto에 member_id 넣어서 출력하기 -->
		                    			<div class="ls_member_id">
		                    				<c:out value="${list2.member_id}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_name">
		                    				<c:out value="${list2.prod_name}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_price">
		                    				<c:out value="${list2.prod_price}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_stock">
		                    				<c:out value="${list2.prod_stock}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_sell">
		                    				<c:out value="${list2.prod_sell}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_sell_deadline">
		                    				<c:out value="${list2.prod_sell_deadline}"></c:out>
		                    			</div>
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
		                    <a href="${path }/prodDetail/${list3.prod_idx }">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
		                    			</div>
		                    			<!-- product dto에 member_id 넣어서 출력하기 -->
		                    			<div class="ls_member_id">
		                    				<c:out value="${list3.member_id}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_name">
		                    				<c:out value="${list3.prod_name}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_price">
		                    				<c:out value="${list3.prod_price}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_stock">
		                    				<c:out value="${list3.prod_stock}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_sell">
		                    				<c:out value="${list3.prod_sell}"></c:out>
		                    			</div>
		                    			<div class="ls_prod_sell_deadline">
		                    				<c:out value="${list3.prod_sell_deadline}"></c:out>
		                    			</div>
		                    		</div>
		                    	</div>	
		                    </a>
	                    </c:forEach>
	            	</c:if>	 
                </div>
            </div>
			</div>
			
			<!-- Footer 영역 -->
	        <div class="footer_nav">
	            <div class="footer_nav_container">
	                <ul>
	                    <li>이용안내</li>
	                    <span class="line">|</span>
	                    <li>개인정보처리방침</li>
	                    <span class="line">|</span>
	                    <li>뷰어다운로드</li>
	                    <span class="line">|</span>
	                    <li>저작권보호정책</li>
	                    <span class="line">|</span>
	                    <li>전자우편주소수집거부</li>
	                    <span class="line">|</span>
	                    <li>오시는길</li>
	                    <span class="line">|</span>
	                    <li>사이트맵</li>
	                </ul>
	            </div>
	        </div>
	        
	        <div class="footer">
	            <div class="footer_container">
	                <div class="footer_left">
	                    <img src=""> <!-- 로고 이미지 아무거나 넣자 나중에 -->
	                </div>
	                <div class="footer_right">
	                    [11921] 경기 구리시 건원대로 44 이젠농수산식품유통공사
	                    <br>
	                    전화 : ooo-oo-ooooo
	                    <br>
	                    팩스 : oooo-oooo
	                    <br>
	                    E-mail : oooo@ezen.or.kr
	                    <br>
	                    <br>
	                    COPYRIGHT @2012 by Korea Agro-Fisheries & Food Trade Corporation ALL RIGHTS RESERVED.
	                </div>
	                <div class="clearfix"></div>
	            </div>
	        </div>
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