<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>���� ������</title>
</head>
<body>

		<h3>���� ������</h3>
		
		<table border="1">
		
			<form action="sendMessage" method="POST">

				<div id="out">��������� ���̵� �����ּ���</div>
				<div class="input-group mb-3">
					<span class="input-group-text"> &nbsp;�޴»��&nbsp; </span>
					<input class="form-control" id="id" name="id" type="text" />
				</div>
				
				<div class="input-group mb-3">
					<span class="input-group-text"> &nbsp;����&nbsp; </span>
					<input class="form-control" id="title" name="title" type="text" />
				</div>
				
				<div class="input-group mb-3">
					<span class="input-group-text"> &nbsp;����&nbsp; </span>
					<textarea class="form-control" id="content" name="content" cols="50" rows="8" style="height:300px;resize: none;"></textarea>
				</div>
				
			<input id="submitBtn" type="submit" value="���� ������" />
				
			</form>
		
		</table>
		
		<script src="/farmocean/resources/js/mypage/sendMessage.js?ver=<%=System.currentTimeMillis() %>"></script>
		
		<script>
			const title = document.getElementById("title");
			const content = document.getElementById("content");
			const submitBtn = document.getElementById("submitBtn");

			submitBtn.addEventListener('click', (e) => {
				
				if (title.value == "") {
					alert("������ �ۼ����ּ���");
					e.preventDefault();
				} else if (content.value == "") {
					alert("������ �ۼ����ּ���");
					e.preventDefault();
				} else {
					alert("���������� �߼��߽��ϴ�");
					
				}				
			});
		</script>

</body>
</html>