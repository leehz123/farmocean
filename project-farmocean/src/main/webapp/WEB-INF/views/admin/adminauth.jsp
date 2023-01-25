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
<title>���� ����</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		
			ID : <input type="text" id="member_id" name="member_id" value="" />
			<button class="btn btn-primary" id="btnAdminAdd" onclick="fnAdminAdd()">������ ���</button>
		
			<hr />			
			<div id="resultFaulty">			
				<table class="table">
					<thead>
						<tr>
							<th scope="col">������ ID</th>
							<th scope="col">�̸�</th>
							<th scope="col">�г���</th>
							<th scope="col">����</th>
							<th scope="col">����</th>
						</tr>
					</thead>
				
					<tbody id="tableAdd">
						<c:forEach items="${adminList }" var="admin">
						<tr>
							<th scope="col">${admin.member_id }</th>
							<th scope="col">${admin.member_name }</th>
							<th scope="col">${admin.member_nickName }</th>
							<th scope="col">${admin.member_type }</th>
							<th scope="col"><button onclick="fnAdminDel('${admin.member_id}')">����</button> </th>
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