<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script>
	const loot_depth = '/farmocean';
</script>
<link rel="stylesheet" href="<c:url value="/resources/css/mainpage/main.css" />" />
<script src="<c:url value="/resources/js/board/ajaxadmin.js"/>"></script>
<title>Insert</title>
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
				<div class="login_btn"><a href="/member/login">로그인</a></div>
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
		
			<h1>[미완료]사진 저장 경로</h1>
			<hr>
			<h1>[작업중]신고받은ID(페이징)</h1>
			<div id="resultFaulty"></div>
			<hr>
			<h1>판매자 검색(ID, 닉네임)</h1>
			<select id="searchMember">
				<option value="I">ID</option>	
				<option value="N">닉네임</option>
			</select>
			<input type="text" id="searchMemberValue" /> <button id="btnSearchMember">검색</button>
			<div id="resultMember"></div>
			<hr>
			<h1>판매상품 조회(판매자, 상품번호, 상품명)</h1>
			<select id="searchProd">
				<option value="M">판매자</option>	
				<option value="N">상품번호</option>
				<option value="P">상품명</option>
			</select>
			<input type="text" id="searchProdValue" /> <button id="btnSearchProd">검색</button>
			<div id="resultProd">
			</div>
			<hr>
			<h1>다음 주소 검색</h1>
			<input type="text" id="sample4_postcode" placeholder="우편번호">
			<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" id="sample4_roadAddress" placeholder="도로명주소">
			<input type="text" id="sample4_jibunAddress" placeholder="지번주소">
			<span id="guide" style="color:#999;display:none"></span>
			<input type="text" id="sample4_detailAddress" placeholder="상세주소">
			<input type="text" id="sample4_extraAddress" placeholder="참고항목">
			
			<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<script>
			    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
			    function sample4_execDaumPostcode() {
			        new daum.Postcode({
			            oncomplete: function(data) {
			                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			
			                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			                var roadAddr = data.roadAddress; // 도로명 주소 변수
			                var extraRoadAddr = ''; // 참고 항목 변수
			
			                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
			                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
			                    extraRoadAddr += data.bname;
			                }
			                // 건물명이 있고, 공동주택일 경우 추가한다.
			                if(data.buildingName !== '' && data.apartment === 'Y'){
			                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			                }
			                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			                if(extraRoadAddr !== ''){
			                    extraRoadAddr = ' (' + extraRoadAddr + ')';
			                }
			
			                // 우편번호와 주소 정보를 해당 필드에 넣는다.
			                document.getElementById('sample4_postcode').value = data.zonecode;
			                document.getElementById("sample4_roadAddress").value = roadAddr;
			                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
			                
			                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
			                if(roadAddr !== ''){
			                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
			                } else {
			                    document.getElementById("sample4_extraAddress").value = '';
			                }
			
			                var guideTextBox = document.getElementById("guide");
			                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
			                if(data.autoRoadAddress) {
			                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
			                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
			                    guideTextBox.style.display = 'block';
			
			                } else if(data.autoJibunAddress) {
			                    var expJibunAddr = data.autoJibunAddress;
			                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
			                    guideTextBox.style.display = 'block';
			                } else {
			                    guideTextBox.innerHTML = '';
			                    guideTextBox.style.display = 'none';
			                }
			            }
			        }).open();
			    }
			</script>
			
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
</body>
</html>