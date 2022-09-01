<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="${path}/resources/css/mypage/changeinfo.css">
<title>프로필 이미지 변경</title>
</head>
<body>

		<c:forEach items="${memberinfo }" var="info">
		
			<img id="preview" src="/farmocean/resources/image/${info.member_image }" width="200" height="200"/> <br>
			
			<form action="changeimg" method="POST" action="https://test.mobilians.co.kr/mcash_webnoti/step1.php" accept-charset="EUC-KR" enctype="multipart/form-data">
			
				<label for="fileInput">
  					<div class="btn-upload">프로필 이미지 선택하기</div>
				</label>
				 
				<label for="basicImg">
  					<div class="basic-upload">기본 이미지 선택하기</div>
				</label>
				 
				<label for="submitInput">
  					<div class="submit-upload">프로필 이미지 변경하기</div>
				</label>
				
				<button type="button" name = "basicImg" id = "basicImg" onclick="changeBasicImg()">버튼</button>
				<input type="hidden" name = "member_id" id = "member_id" value="${info.member_id }"/>
				<input type="hidden" name = "checkImg" id = "checkImg" value="change"/>
				<input id="submitInput" name="submitInput" type="submit" value="프로필 이미지 변경하기" />
				<input id="fileInput" name="fileInput" type="file" onchange="readURL(this);"/>
			
			</form>

		</c:forEach>
		
		<a href="<c:url value="/mypage/main" />">main으로 가기</a>
		
		<script src="/farmocean/resources/js/mypage/changeimg.js?ver=<%=System.currentTimeMillis() %>"></script>

</body>
</html>