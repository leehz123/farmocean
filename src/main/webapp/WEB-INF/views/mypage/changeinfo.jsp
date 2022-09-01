<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>

		<h3>회원 정보 수정</h3>
		
		<table>
		
			<form action="changeinfo" method="POST" action="https://test.mobilians.co.kr/mcash_webnoti/step1.php" accept-charset="EUC-KR">
			
				<c:forEach items="${memberinfo }" var="info">
	
					<tr>
						<td>아이디</td>
						<td><input name="member_id" value="${info.member_id }" type="text" readonly/></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td>
							<input id="password" name="member_pw" value="${info.member_pw }" type="text"/>
							<div id="out1"></div>				
						</td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input name="member_name" value="${info.member_name }" type="text" readonly/></td>
					</tr>
					<tr>
						<td>닉네임</td>
						<td>
							<input id="nickname" name="member_nickName" value="${info.member_nickName }" type="text" />
							<div id="out"></div>
						</td>
					</tr>
					<tr>
						<td>포인트</td>
						<td><input name="member_point" value="${info.member_point }" type="text" readonly/></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td>
							<input id="email" name="member_email" value="${info.member_email }" type="text" />
							<div id="out2"></div>
						</td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input name="member_address" value="${info.member_address }" type="text" /></td>
					</tr>
					<tr>
						<td>표시</td>
						<td><input name="member_type" value="${info.member_type }" type="text" readonly/></td>
					</tr>
						<c:if test="${check eq 'S' }">
							<tr>
								<td>핸드폰 번호</td>
								<td>
									<input id="phone" name="member_phoneNum" value="${info.member_phoneNum }" type="text" />
									<div id="out3"></div>
								</td>
							</tr>
							<tr>
								<td>계좌 번호</td>
								<td><input name="member_accountNum" value="${info.member_accountNum }" type="text" /></td>
							</tr>
						</c:if>
					<tr>
						<td></td>
						<td><input id="subBtn" type="submit" value="내 정보 수정하기"/></td>
					</tr>
				</c:forEach>
			
			</form>
			
		</table>
		
		<a href="<c:url value="/mypage/main" />">main으로 가기</a>
		
		<script src="/farmocean/resources/js/mypage/changeinfo.js?ver=2"></script>
		
		

</body>
</html>