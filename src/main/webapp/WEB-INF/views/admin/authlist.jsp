<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<script src="<c:url value="/resources/js/board/ajaxadmin.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/admin/main.css" />" />
<title>권한 관리</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		
			<button class="btn btn-primary" id="btnSearchFaulty" onclick="searchFaultyList()">신고자 검색</button>
		
			<hr />			
			<div id="resultFaulty">			
				<table class="table">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">이름</th>
							<th scope="col">닉네임</th>
							<th scope="col">상태</th>
							<th scope="col">신고수</th>
							<th scope="col">상태수정</th>
						</tr>
					</thead>
				
					<tbody id="tableAdd">
								  		    
					</tbody>
				</table>			
			</div>
			
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>