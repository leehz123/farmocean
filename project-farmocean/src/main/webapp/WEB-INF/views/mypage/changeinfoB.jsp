<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/resources/jspf/header.jspf" %>
<title>회원 정보 수정</title>
<link rel="stylesheet" href="/farmocean/resources/js/member/joinDesign.css">
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;회원 정보 수정 (구매자)</h2>
		
		<table>
		
			<form id="changeInfo" action="changeinfo" method="POST" action="https://test.mobilians.co.kr/mcash_webnoti/step1.php" accept-charset="EUC-KR">
				<c:forEach items="${members }" var="member">
	
		<tr style="margin-top: 100px;">
        	<td class="col1"><span class="starEm">*</span>아이디</td>
        	<td class="col2">
            	<input type="text" name="member_id" value="${member.getMember_id() }" id="post_member_id" maxlength="12" readonly />
       		</td>
    	</tr>
		<tr>
        	<td class="col1"><span class="starEm">*</span>이름</td>
        	<td class="col2">
        		<input type="text" id="member_name" name="member_name" value="${member.getMember_name() }" maxlength="5" readonly />
			</td>
    	<tr>
        	<td class="col1"><span class="starEm">*</span>닉네임</td>
        	<td class="col2">
            	<input type="text" id="nickname" name="member_nickName" value="${member.getMember_nickName() }"	/>
				<button type="button" class="but1" id="checkNickBtn">중복확인</button>
            	<p>※닉네임은 <span class="num">3자 이상 10자 이하, 영어 또는 숫자 또는 한글</span>로 사용 가능합니다.</p>
            	<div id="out" >&nbsp;</div>
        	</td>
    	</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>이메일</td>
			<td class="col2">
			<input type="text" id="email" name="member_email" value="${member.getMember_email() }" />
            <div id="out2">&nbsp;</div>
            </td>
		</tr>
		<tr>
				<td class="col1"><span class="starEm">*</span>현재주소</td>
				<td class="col2"><input id="member_address" name="member_address" value="${member.getMember_address() }" type="text" readonly /></td>
		</tr>
		<tr>
					<td class="col1"><span class="starEm">*</span>우편번호</td>
					<td class="col2">
					<input type="text" id="sample6_postcode" name="postcode" placeholder="우편번호" >
					 	<button type="button" class="but1" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">우편번호 찾기</button>
					</td>
		</tr>
		<tr> 
					<td class="col1"><span class="starEm">*</span>주소</td>
					<td class="col2"><input type="text" id="sample6_address" name="address"
						placeholder="주소" ></td>
		</tr>
		<tr>
					<td class="col1"><span class="starEm">*</span>참고주소</td>
					<td class="col2"><input type="text" id="sample6_extraAddress"
						name="extraAddress" placeholder="참고항목" ></td>
		</tr>
		<tr>
					<td class="col1"><span class="starEm">*</span>추가주소</td>
					<td class="col2"><input type="text" id="sample6_detailAddress"
						name="detailAddress" placeholder="추가주소" ></td>
		</tr>			
	
			</form>
				</c:forEach>
		</table>
		
		<div class="create">
    		<button class="but3" id="subBtn" type="submit" form="changeInfo">내 정보 수정하기</button>  
  		</div>
		
		<script src="/farmocean/resources/js/mypage/changeinfoB.js?ver=<%=System.currentTimeMillis() %>"></script>
		<script src="/farmocean/resources/js/mypage/changeinfo2.js?ver=<%=System.currentTimeMillis() %>"></script>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>