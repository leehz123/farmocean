<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<script src="<c:url value="/resources/js/product/prod_buy.js" />"></script>
<title>${productTitle}</title>
</head>
<body>
	<style>
		*{
			margin: 0;
			padding: 0;
		}
	</style>
	<script type="text/javascript">
		window.resizeTo(570,435);
	</script>
	<c:choose>
    	<c:when test="${sessionScope.loginId eq null || productTitle eq null }">
    		<script type="text/javascript">
    			alert('로그인 정보가 없거나 잘못된 상품 입니다.');
    			window.close();
    		</script>
    	</c:when>    
    	<c:otherwise>
    	<input type="hidden" id="productId" value="${productId }" />
    	<c:set var="prodimg1" value="${productImg[0].img_url}" />
    	<c:set var="prodimg2" value="${productImg[1].img_url}" />
    	
			<div class="card mb-3" style="max-width: 540px;margin:auto;" >
				<div class="row g-0">
					<div class="col-4">	
						<c:choose>						
							<c:when test="${prodimg1 eq null}">
								<img style="padding:16px 0px 8px 20px;width:150px;height:150px;" src="<c:url value="/resources/image/prod/no_img.jpg" />" class="img-fluid rounded-start" alt="${productTitle}">
							</c:when>
							<c:otherwise>
								<c:choose>
	                            	<c:when test="${fn:contains(prodimg1, 'http')}">
										<img style="padding:16px 0px 8px 20px;width:150px;height:150px;" src="${prodimg1 }" class="img-fluid rounded-start" alt="${productTitle}">
	                            	</c:when>
									<c:otherwise>
										<img style="padding:16px 0px 8px 20px;width:150px;height:150px;" src="<c:url value="${prodimg1 }"/> " class="img-fluid rounded-start" alt="${productTitle}">
									</c:otherwise>
	                            </c:choose>   
								
							</c:otherwise>
						</c:choose>
						<br>
						<c:choose>						
							<c:when test="${prodimg2 eq null}">
								<img style="padding:16px 0px 8px 20px;width:150px;height:150px;" src="<c:url value="/resources/image/prod/no_img.jpg" />" class="img-fluid rounded-start" alt="${productTitle}">
							</c:when>
							<c:otherwise>
								<c:choose>
	                            	<c:when test="${fn:contains(prodimg2, 'http')}">
										<img style="padding:16px 0px 8px 20px;width:150px;height:150px;" src="${prodimg2 }" class="img-fluid rounded-start" alt="${productTitle}">
	                            	</c:when>
									<c:otherwise>
										<img style="padding:16px 0px 8px 20px;width:150px;height:150px;" src="<c:url value="${prodimg2 }"/> " class="img-fluid rounded-start" alt="${productTitle}">
									</c:otherwise>
	                            </c:choose>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-8">
						<div class="card-body">
							<h5 class="card-title">${productTitle}</h5>
							<p class="card-text">
							<small class="text-muted">가격 : ${productPrice} 원</small><br>
							<small class="text-muted">마감 시간 : ${productDeadline}</small>
							</p>
							<p class="card-text">
								배송지 정보 입력<br />
						
								<input type="text" onclick="execDaumPostcode()" id="postcode" placeholder="우편번호" readonly>
								<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
								<input type="text" id="roadAddress" placeholder="도로명주소" readonly>
								<input type="text" id="jibunAddress" placeholder="지번주소" readonly>
								<span id="guide" style="color:#999;display:none"></span>
								<input type="text" id="detailAddress" placeholder="상세주소">
								<input type="text" id="extraAddress" placeholder="참고항목">
							</p>
							<p class="card-text">
								<button onclick="fnBuyProd()" class="btn btn-primary">구매 신청</button>					
								<button onclick="window.close();"  class="btn btn-danger">창 닫기</button>
							</p>
						</div>
					</div>
				</div>
			</div>
    		<%
    			/*    			
   				<div class="card" style="width: 18rem;">
				<img src="${productImg.img_url }" class="card-img-top" alt="${productTitle}">
				<div class="card-body">
					<h5 class="card-title">${productTitle}</h5>
					<p class="card-text">[간략내용]</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">가격 : ${productPrice} 원</li>
					<li class="list-group-item">마감 시간 <br /> ${productDeadline}</li>
					<li class="list-group-item">
						배송지 정보 입력<br />
						
						<input type="text" onclick="execDaumPostcode()" id="postcode" placeholder="우편번호" readonly>
						<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
						<input type="text" id="roadAddress" placeholder="도로명주소" readonly>
						<input type="text" id="jibunAddress" placeholder="지번주소" readonly>
						<span id="guide" style="color:#999;display:none"></span>
						<input type="text" id="detailAddress" placeholder="상세주소">
						<input type="text" id="extraAddress" placeholder="참고항목">
						
					</li>
				</ul>
				<div class="card-body">
					<button onclick="fnBuyProd()" class="btn btn-primary">구매 신청</button>					
					<button onclick="window.close();"  class="btn btn-danger">창 닫기</button>					
				</div>
			</div>
			*/
    		%>
   		</c:otherwise>	
    </c:choose>	
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	    function execDaumPostcode() {
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
	                postcode.value = data.zonecode;
	                roadAddress.value = roadAddr;
	                jibunAddress.value = data.jibunAddress;
	                
	                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	                if(roadAddr !== ''){
	                	extraAddress.value = extraRoadAddr;
	                } else {
	                	extraAddress.value = '';
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
</body>
</html>