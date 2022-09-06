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
					<!-- �α��� X -->
					<c:if test = "${member == null }">
						<li>
							<a href="${path }/member/login">�α���</a>
						</li>
						<li>
							<a href="${path }/member/join">ȸ������</a>
						</li>					
					</c:if>
					<!-- �α��� O (member ���� ����) -->
					<c:if test = "${member != null }">
						<!-- �Ǹ��� ���� (�α��� ���� ���� �ٲ� ��.) -->
						<c:if test = "${member.admin == 1 }">
							<li><a href="${path }/mainpage/seller/main">�Ǹ��� ������</a></li> <!-- �Ǹ��ڷ� �α��� �� ��ǰ ��� ������ ���� ���� -->
						</c:if>
						<li>
							<a id="gnb_logout_btn">�α׾ƿ�</a>
						</li>
						<li>
							����������
						</li>
						<li>
							��ٱ���
						</li>
					</c:if>
					<li>
						������
					</li>
					<!-- �Ǹ��ڷ� �α��� �ؾ� �������� ��ø� �α���X�� �� ���Ƿ� ���� (��ǰ ��� ������) -->
					<li><a href="${path }/mainpage/prod_reg/main">�Ǹ��� ������</a></li>
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
                					<option value="T">��ǰ �̸�</option>
                					<option value="A">�Ǹ��� ID</option>
                				</select>
                				<input type="text" name="keyword">
                    			<button class='btn search_btn'>�� ��</button>                				
                			</div>
                		</form>
                	</div>
				</div>
				<div class="login_area">
					<div class="login_btn"><a href="${path }/member/login">�α���</a></div>
					<span><a href="${path }/member/join">ȸ������</a></span>
				</div>
				<div class="clearfix"></div>				
			</div>
			<div class="navi_bar_area">
				<div class="navi_bar_area">
					<div class="dropdown">
						<button class="dropbtn">�ķ��۹�
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates2 }" var="cate">
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">Ư���۹�
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates7 }" var="cate">
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">���Ϸ�
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates1 }" var="cate">
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">ä�ҷ�
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates6 }" var="cate">
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">���깰
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates5 }" var="cate">
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">�Ѹ���?
							<i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<c:forEach items="${cates3 }" var="cate">
							<!-- ī�װ� ��ȸ �������� ��� �����ϸ� �� -->
								<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
							</c:forEach>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">�ǰ���?
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

			<!-- �� �͵�
			<div>
				<br><br> 
				<h1>
					�����ϰ� ������ or �̹����� ��� ��� ���̵� �߰��ϼ� <br><br> 
					!!�˻��ϱ� �Ϸ� or ī�װ� ���� Ŭ���ϸ� ī�װ� ����Ʈ ����ϰ� �����!!<br><br> 
					�� �α�Ű���� 8���� ����<br> <br> 
				</h1>
				<h3>
					���� ���� �����̵�ó�� �Ѿ�� �ϰ� <br> 
					�ֽż� �α���� ��ư ������ �����ϸ� �ɵ� a�±� �޴��� <br> 
					ó������ �ֽż� ����Ʈ ������ �ϰ�<br> 
					�α� Ű����� �˻� �ؿ� ������ �ǰ�<br> 
					ī�װ��� ������ ī�� ����Ʈ �����µ� �׷� ������ �����̵� �� �����̰� ���� ���Ѽ� ������ �ɷ� ���°� ����� ��<br> 
					�� �׸��� ī�װ� �� ���Ƽ� �������� ���� �Ѹ��� �ǰ� ������ �������ϱ� �׺κе� �������Ѿ��� ��<br> 
					�׳� ī�� ����Ʈ ���ڷ� �� ������ �ϸ� �� ��<br> 
				</h3>
			</div>
			
			<br><br><br>
			-->
			
			<div class="ls_wrap">
				<div class="ls_div_subject">
					�� ���� ���� ����Ʈ 8
				</div>
                <!-- ��ǰ ����Ʈ O -->
                <div class="ls_div">
	            	<c:if test="${listcheck != 'empty'}">
	                    <c:forEach items="${list}" var="list">
		                    <a href="${path }/prod/prodDetail/${list.prod_idx }">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
		                    			</div>
		                    			<!-- product dto�� member_id �־ ����ϱ� -->
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
					�ֽż� ��ǰ 10
				</div>
                <!-- ��ǰ ����Ʈ O -->
                <div class="ls_div">
	            	<c:if test="${listcheck2 != 'empty'}">
	                    <c:forEach items="${list2}" var="list2">
		                    <a href="${path }/prodDetail/${list2.prod_idx }">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
		                    			</div>
		                    			<!-- product dto�� member_id �־ ����ϱ� -->
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
					�α�� ��ǰ 10
				</div>
                <!-- ��ǰ ����Ʈ O -->
                <div class="ls_div">
	            	<c:if test="${listcheck2 != 'empty'}">
	                    <c:forEach items="${list3}" var="list3">
		                    <a href="${path }/prodDetail/${list3.prod_idx }">
		                    	<div class="ls_div_content_wrap">
		                    		<div class="ls_div_content">
		                    			<div class="image_wrap">
		                    			</div>
		                    			<!-- product dto�� member_id �־ ����ϱ� -->
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
			
			<!-- Footer ���� -->
	        <div class="footer_nav">
	            <div class="footer_nav_container">
	                <ul>
	                    <li>�̿�ȳ�</li>
	                    <span class="line">|</span>
	                    <li>��������ó����ħ</li>
	                    <span class="line">|</span>
	                    <li>���ٿ�ε�</li>
	                    <span class="line">|</span>
	                    <li>���۱Ǻ�ȣ��å</li>
	                    <span class="line">|</span>
	                    <li>���ڿ����ּҼ����ź�</li>
	                    <span class="line">|</span>
	                    <li>���ô±�</li>
	                    <span class="line">|</span>
	                    <li>����Ʈ��</li>
	                </ul>
	            </div>
	        </div>
	        
	        <div class="footer">
	            <div class="footer_container">
	                <div class="footer_left">
	                    <img src=""> <!-- �ΰ� �̹��� �ƹ��ų� ���� ���߿� -->
	                </div>
	                <div class="footer_right">
	                    [11921] ��� ������ �ǿ���� 44 ����������ǰ�������
	                    <br>
	                    ��ȭ : ooo-oo-ooooo
	                    <br>
	                    �ѽ� : oooo-oooo
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
				prevArrow : "<button type='button' class='ls_div_content_prev'><</button>",		// ���� ȭ��ǥ ��� ����
				nextArrow : "<button type='button' class='ls_div_content_next'>></button>"		// ���� ȭ��ǥ ��� ����
			});
				
		});
		
		// gnb_area �α׾ƿ� ��ư
		$("gnb_logout_btn").click(function() {
			// alert("��ư �۵�");
			// ajax jquery
			$.ajax({
				type:"POST",
				url:"/farmocean/member/logout.do",
				success:function(data) {
					alert("�α׾ƿ� ����");
					document.location.reload();
				}
			});
		});
		
	</script>

</body>
</html>