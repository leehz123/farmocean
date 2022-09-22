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
		
			ID : <input type="text" id="member_id" name="member_id" value="" />
			<button class="btn btn-primary" id="btnAdminAdd" onclick="fnAdminAdd()">관리자 등록</button>
		
			<hr />			
			<div id="resultFaulty">			
				<table class="table">
					<thead>
						<tr>
							<th scope="col">관리자 ID</th>
							<th scope="col">이름</th>
							<th scope="col">닉네임</th>
							<th scope="col">상태</th>
							<th scope="col">삭제</th>
						</tr>
					</thead>
				
					<tbody id="tableAdd">
						<c:forEach items="${adminList }" var="admin">
						<tr>
							<th scope="col">${admin.member_id }</th>
							<th scope="col">${admin.member_name }</th>
							<th scope="col">${admin.member_nickName }</th>
							<th scope="col">${admin.member_type }</th>
							<th scope="col"><button onclick="fnAdminDel('${admin.member_id}')">삭제</button> </th>
						</tr>
						</c:forEach>   
					</tbody>
				</table>			
			</div>
			
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>