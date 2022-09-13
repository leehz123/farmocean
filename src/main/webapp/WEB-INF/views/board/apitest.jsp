<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<script src="<c:url value="/resources/js/board/ajaxadmin.js"/>"></script>
<title>Insert</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
		
	<h1>[미완료]사진 저장 경로</h1>
	<hr>
	<h1>[작업중]
	<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/Sell/member/sample63" />">판매자 팝업</a></h1>
	<hr>	
	<h1>[작업중]신고받은ID(페이징)</h1>
	<div id="resultFaulty"></div>
	<hr>
	<h1>[작업중]메인 상단 배너 등록</h1>
	<form id="frmMainTopBanner" name="frmMainTopBanner" method="post" action="<c:url value="/admin/setMainTopBanner" />" enctype="multipart/form-data">
	<input type="hidden" name="mainTopIdx" id="" value=""/>
	<input type="hidden" name="mainTopFileName" id="" value=""/>
	배너이름 : <input type="text" name="mainTopName" id="mainTopName" value="test1" />
	배너링크 : <input type="text" name="mainTopLink" id="mainTopLink" value="/" />
	배너이미지 : <input type="file" name="mainTopImg" id="mainTopImg" /> <br />
	<input type="hidden" name="mainTopIdx" id="" value=""/>
	<input type="hidden" name="mainTopFileName" id="" value=""/>
	배너이름 : <input type="text" name="mainTopName" id="mainTopName" value="테스트2" />
	배너링크 : <input type="text" name="mainTopLink" id="mainTopLink" value="<c:url value="/board/" />" />
	배너이미지 : <input type="file" name="mainTopImg2" id="mainTopImg2" /> <br />
	<input type="hidden" name="mainTopIdx" id="" value=""/>
	<input type="hidden" name="mainTopFileName" id="" value=""/>
	배너이름 : <input type="text" name="mainTopName" id="mainTopName" value="test3" />
	배너링크 : <input type="text" name="mainTopLink" id="mainTopLink" value="<c:url value="/board/notice" />" />
	배너이미지 : <input type="file" name="mainTopImg3" id="mainTopImg3" /> <br />
	<button id="btnFrmSubmit">등록</button>
	<input type="reset" />
	</form>
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
	<input type="text" id="searchProdValue" value="sample5" /> <button id="btnSearchProd">검색</button>
	<div id="resultProd">
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">상품번호</th>
	      <th scope="col">상품명</th>
	      <th scope="col">판매자</th>
	      <th scope="col">판매여부</th>
	    </tr>
	  </thead>
	  <tbody id="tableAdd">
	  	<!-- 양식 -->
	  	<!-- 
	    <tr>
	      <th scope="row">1</th>
	      <td>Mark</td>
	      <td>Otto</td>
	      <td>@mdo</td>
	    </tr>
	     -->
	    <!-- 양식 -->			    
	  </tbody>
	</table>
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
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>