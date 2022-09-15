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

				<div id="out">�޴»���� �г����� �ۼ����ּ���</div>
				<div class="input-group mb-3">
					<span class="input-group-text"> &nbsp;�޴»��&nbsp; </span>
					<c:forEach items="${sendMessageId }" var="ID">		
						<input class="form-control" id="id" name="id" type="text" value="${ID.member_nickName }"  readonly></input>
					</c:forEach>	
				</div>
				
				<div id="test_cnt1">(0 / 20)</div>
				<div class="input-group mb-3">
					<span class="input-group-text"> &nbsp;����&nbsp; </span>
					<input class="form-control" id="title" name="title" type="text" />
				</div>
				
				<div id="test_cnt">(0 / 200)</div>
				<div class="input-group mb-3">
					<span class="input-group-text"> &nbsp;����&nbsp; </span>
					<textarea class="form-control" id="content" name="content" cols="50" rows="8" style="height:300px;resize: none;"></textarea>
				</div>
				
			<input id="submitBtn" type="submit" value="���� ������" />
				
			</form>
		
		</table>
		
		
		
		<script src="/farmocean/resources/js/mypage/sendMessage.js?ver=<%=System.currentTimeMillis() %>"></script>
		
		<script>
		
			// ���ڼ� ����
			
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