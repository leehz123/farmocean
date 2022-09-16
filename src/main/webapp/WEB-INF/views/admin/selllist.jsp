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
<title>sample</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
<%@ include file="/resources/jspf/admin/body_left.jspf" %>

		<form action="<c:url value="/admin/selllist" />" method="post">
			�Ǹ��� ID : <input type="text" id="member_id" name="member_id" value="sample63" />
			<input type="submit" value="�˻�" />
		</form>
			<hr />
			
			<div id="searchResult">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">��û�� / ��ǰ��</th>
							<th scope="col">�Ǹ���</th>
							<th scope="col">����</th>
							<th scope="col">�����ȣ</th>
							<th scope="col">�ּ�</th>
							<th scope="col">����</th>
							<th scope="col">���¼���</th>
						</tr>
					</thead>
				
					<tbody id="tableAdd">						
						<c:forEach items="${sellList }" var="sellInfo" varStatus="status">
						<tr>
							<td> [${sellInfo.view_regdate }] <br>
								 ${sellInfo.prod_name }</td>
							<td> ${sellInfo.member_nickname }(${buyInfo.sell_id })</td>
							<td> ${sellInfo.view_price }��</td>
							<td> ${sellInfo.post_code }</td>
							<td> ${sellInfo.view_address }</td>
							<td>
								<select name="state${status.index }" id="state${status.index }">
									<option value="0"<c:if test="${sellInfo.state eq 0}"> selected </c:if>>��û</option>
									<option value="1"<c:if test="${sellInfo.state eq 1}"> selected </c:if>>����</option>
									<option value="2"<c:if test="${sellInfo.state eq 2}"> selected </c:if>>�����</option>
									<option value="3"<c:if test="${sellInfo.state eq 3}"> selected </c:if>>���Ȯ��</option>
									<option value="4"<c:if test="${sellInfo.state eq 4}"> selected </c:if>>��ǰ</option>
									<option value="5"<c:if test="${sellInfo.state eq 5}"> selected </c:if>>���</option>
									<option value="10"<c:if test="${sellInfo.state eq 10}"> selected </c:if>>�ǸſϷ�</option>									
								</select>
							</td>
							<td> <button class="btn btn-danger" onclick="fnChgBuyInfo(${sellInfo.buy_idx},'state${status.index}' )">���¼���</button></td>
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