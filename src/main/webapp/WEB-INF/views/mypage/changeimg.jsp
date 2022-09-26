<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="/resources/jspf/header.jspf" %>
<link rel="stylesheet" href="${path}/resources/css/mypage/changeinfo.css?ver=<%=System.currentTimeMillis() %>">
<script src="/farmocean/resources/js/mypage/changeimg.js?ver=<%=System.currentTimeMillis() %>"></script>
<title>프로필 이미지 변경</title>
		<style>
			
 			div {
 				text-align: center;
 			}
 			
 			
 			
		</style>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
		<c:forEach items="${memberinfo }" var="info">
		
			<div>		
				<img id="preview" src="/farmocean/resources/image/mypage/${info.member_image }" width="200" height="200"/> <br>
			</div>
			
			<form action="changeimg" method="POST" action="https://test.mobilians.co.kr/mcash_webnoti/step1.php" accept-charset="EUC-KR" enctype="multipart/form-data">
			
				<label for="fileInput">
  					<div class="btn-upload">프로필 이미지 선택하기</div>
				</label> <br>
				 
				<label for="basicImg">
  					<div class="basic-upload">기본 이미지 선택하기</div>
				</label> <br>
				 
				<label for="submitInput">
  					<div class="submit-upload">프로필 이미지 변경하기</div>
				</label> <br>
				
				<button type="button" name = "basicImg" id = "basicImg" onclick="changeBasicImg()">버튼</button>
				<input type="hidden" name = "member_id" id = "member_id" value="${info.member_id }"/>
				<input type="hidden" name = "checkImg" id = "checkImg" value="change"/>
				<input id="submitInput" name="submitInput" type="submit" value="프로필 이미지 변경하기" />
				<input id="fileInput" name="fileInput" type="file" onchange="readURL(this);"/>
			
			</form>

		</c:forEach>
		
				
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>