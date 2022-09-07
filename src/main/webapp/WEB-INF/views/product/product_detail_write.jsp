<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<meta charset="EUC-KR">

<script src="/farmocean/resources/ckeditor/ckeditor.js"></script>
<title>��ǰ �Խñ� �ۼ�</title>
<%@ include file="/resources/jspf/header.jspf" %>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>



<c:choose>
	<c:when test="${sessionScope.loginId eq null }">
	   �α��� �� �̿� �����մϴ�
	</c:when>
	<c:otherwise>
	   ID : [${sessionScope.loginId.member_id }] 
	   �̸� : [${sessionScope.loginId.member_name}]
	   ��� : [${sessionScope.loginId.member_pw}]

	   	<c:choose>
			<c:when test="${sessionScope.loginId.member_type eq 'B' }">
					�Ǹ��� ������ ��ǰ �Խñ��� �ۼ��� �� �ֽ��ϴ�.
			</c:when>
			<c:otherwise>
					<div id="container" class="container">
						<div id="edit-container" class="content" style="width: 1000">
							
								
							<form id="frm-ins" action="insert_prod" method="post" enctype="multipart/form-data"></form>	
							<div class="input-group mb-3">
								<span class="input-group-text" id="basic-addon1"> &nbsp;��ǰ �з�&nbsp; </span>
								<select id="cate" name="cate_idx" form="frm-ins">
									<c:forEach items="${requestScope.cateList }" var="cate">
										<option value="${cate.cate_idx }">${cate.cate_name }</option>
									</c:forEach>
								</select>
							</div>
					
							<div class="input-group mb-3">
								<span class="input-group-text" form="frm-ins"> &nbsp;����&nbsp; </span>
								<input type="text" class="form-control" name="prod_name" id="title" form="frm-ins">
							</div>
						
							<div class="input-group mb-3">
								<span class="input-group-text" > &nbsp;������&nbsp; </span>
								<input type="number" min="0" class="form-control" name="prod_stock" id="stock" aria-describedby="basic-addon1" form="frm-ins">
								<span class="input-group-text" > &nbsp;����&nbsp; </span>
								<input type="number" min="0" class="form-control" name="prod_price" id="price" aria-describedby="basic-addon1" form="frm-ins">
								<span class="input-group-text" > &nbsp;�Ǹ������Ͻ�&nbsp; </span>
								<input  type="datetime-local" class="form-control" name="deadline" id="deadline" aria-describedby="basic-addon1" form="frm-ins">
							</div>
								
							
							<div class="input-group mb-3">
								<textarea id="editor1" rows="5" cols="60" name="prod_info" id="prod_info" form="frm-ins"></textarea>
							</div>
							
							<div class="input-group">
								<div class="frm-in-center" id="btn-container">
									<button id="btn-ins" class="btn btn-primary">�� ���</button> <input id="reset-btn" type="reset" value="���" class="btn btn-primary" form="frm-ins"/>
									<!-- <a class="btn btn-dark" href="notice" role="button">�������</a> -->
								</div>
							</div>
							
						</div>
					</div>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>


</body>
<script charset="EUC-KR" src="/farmocean/resources/js/product/prod_detail_write.js"></script>
</html>