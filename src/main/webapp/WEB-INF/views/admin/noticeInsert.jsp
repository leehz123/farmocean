<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<%@ include file="/resources/jspf/csboard.jspf" %>
<script src="<c:url value="/resources/js/board/ajaxadmin.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/admin/main.css" />" />
<title>���� ���</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		
		<form id="frm-ins" action="noticeInsert" method="post" enctype="multipart/form-data" >
			<div class="input-group">
				<hr />
			</div>
			
			<div class="input-group mb-3">
			  <span class="input-group-text" id="basic-addon1"> &nbsp;������&nbsp; </span>
			  <select name="board_cate">
			  <c:forEach items="${catagorys }" var="catagory">
			  	<option value="${catagory.cate_idx }">${catagory.cate_name }</option>
			  </c:forEach>	  
			  
			  </select>
			</div>    
		
			<div class="input-group mb-3">
			  <span class="input-group-text" id="basic-addon1"> &nbsp;����&nbsp; </span>
			  <input type="text" class="form-control" name="board_title" id="board_title" aria-describedby="basic-addon1" value="�׽�Ʈ">
			</div>
			
			<div class="input-group mb-3">
			  <span class="input-group-text" id="basic-addon1"> &nbsp;����&nbsp;&nbsp; </span>
			  <textarea id="editor1" rows="5" cols="60" name="board_memo" id="board_memo"></textarea>
			</div>
			
			<div class="input-group">
				<hr />
			</div>
			
			<div class="input-group">
				<div class="frm-in-center">
					<button id="btn-ins" class="btn btn-primary">���</button> <input type="reset" value="���" class="btn btn-danger" />
					<a class="btn btn-dark" href="notice" role="button">�������</a>
				</div>
			</div>
		</form>
		
		<script type="text/javascript">
			CKEDITOR.replace('editor1',{filebrowserUploadUrl: loot_depth + '/board/upload/cs_img'});
		</script>
		

			
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>