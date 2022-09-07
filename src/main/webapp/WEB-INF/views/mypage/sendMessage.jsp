<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>쪽지 보내기</title>
</head>
<body>

		<h3>쪽지 보내기</h3>
		
		<table border="1">
		
			<form action="sendMessage" method="POST">

				<div id="out">보낼사람의 아이디를 적어주세요</div>
				<div class="input-group mb-3">
					<span class="input-group-text"> &nbsp;받는사람&nbsp; </span>
					<input class="form-control" id="id" name="id" type="text" />
				</div>
				
				<div class="input-group mb-3">
					<span class="input-group-text"> &nbsp;제목&nbsp; </span>
					<input class="form-control" id="title" name="title" type="text" />
				</div>
				
				<div class="input-group mb-3">
					<span class="input-group-text"> &nbsp;내용&nbsp; </span>
					<textarea class="form-control" id="content" name="content" cols="50" rows="8" style="height:300px;resize: none;"></textarea>
				</div>
				
			<input id="submitBtn" type="submit" value="쪽지 보내기" />
				
			</form>
		
		</table>
		
		<script src="/farmocean/resources/js/mypage/sendMessage.js?ver=<%=System.currentTimeMillis() %>"></script>
		
		<script>
			const title = document.getElementById("title");
			const content = document.getElementById("content");
			const submitBtn = document.getElementById("submitBtn");

			submitBtn.addEventListener('click', (e) => {
				
				if (title.value == "") {
					alert("제목을 작성해주세요");
					e.preventDefault();
				} else if (content.value == "") {
					alert("내용을 작성해주세요");
					e.preventDefault();
				} else {
					alert("성공적으로 발송했습니다");
					
				}				
			});
		</script>

</body>
</html>