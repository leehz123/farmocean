<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>Insert</title>
<script src="<c:url value="/resources/ckeditor/ckeditor.js"/>"></script>
</head>
<body>
	
<div class="view-padding">
<form id="frm-ins" action="insert" method="post" enctype="multipart/form-data" >
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
	
	<script type="text/javascript">	
		CKEDITOR.replace('editor1',{filebrowserUploadUrl: loot_depth + '/board/upload/cs_img'});
	</script>
</form>
</div>
</body>
</html>