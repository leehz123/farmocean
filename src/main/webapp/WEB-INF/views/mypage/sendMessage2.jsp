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

				<div id="out">받는사람의 닉네임을 작성해주세요</div>
				<div class="input-group mb-3">
					<span class="input-group-text"> &nbsp;받는사람&nbsp; </span>
					<c:forEach items="${sendMessageId }" var="ID">		
						<input class="form-control" id="id" name="id" type="text" value="${ID.member_nickName }"  readonly></input>
					</c:forEach>	
				</div>
				
				<div id="test_cnt1">(0 / 20)</div>
				<div class="input-group mb-3">
					<span class="input-group-text"> &nbsp;제목&nbsp; </span>
					<input class="form-control" id="title" name="title" type="text" />
				</div>
				
				<div id="test_cnt">(0 / 200)</div>
				<div class="input-group mb-3">
					<span class="input-group-text"> &nbsp;내용&nbsp; </span>
					<textarea class="form-control" id="content" name="content" cols="50" rows="8" style="height:300px;resize: none;"></textarea>
				</div>
				
			<input id="submitBtn" type="submit" value="쪽지 보내기" />
				
			</form>
		
		</table>
		
		
		
		<script src="/farmocean/resources/js/mypage/sendMessage.js?ver=<%=System.currentTimeMillis() %>"></script>
		
		<script>
		
			// 글자수 제한
			
			$(document).ready(function() {
			    $('#content').on('keyup', function() {
			        $('#test_cnt').html("("+$(this).val().length+" / 200)");
			 
			        if($(this).val().length > 200) {
			            $(this).val($(this).val().substring(0, 200));
			            $('#test_cnt').html("(200 / 200)");
			        }
			    });
			});
			
			$(document).ready(function() {
			    $('#title').on('keyup', function() {
			        $('#test_cnt1').html("("+$(this).val().length+" / 20)");
			 
			        if($(this).val().length > 20) {
			            $(this).val($(this).val().substring(0, 20));
			            $('#test_cnt1').html("(20 / 20)");
			        }
			    });
			});

		</script>

</body>
</html>