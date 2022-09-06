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




<div class="container">
	<div class="content" style="width: 1000">
		<form id="frm-ins" action="insert" method="post" enctype="multipart/form-data" >

			  <div class="input-group mb-3">
				<span class="input-group-text" id="basic-addon1"> &nbsp;��ǰ �з�&nbsp; </span>
				<select name="prod_cate">
					<option selected>ī�װ��� �������ּ���</option>
					<c:forEach items="${requestScope.cateList }" var="cate">
						<option value="${cate.cate_idx }">${cate.cate_name }</option>
					</c:forEach>
				</select>
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text" > &nbsp;����&nbsp; </span>
				<input type="text" class="form-control" name="board_title" id="board_title">
			</div>
		
			<div class="input-group mb-3">
				<span class="input-group-text" > &nbsp;������&nbsp; </span>
				<input type="number" class="form-control" name="prod-stock" id="prod-stock" aria-describedby="basic-addon1">
				<span class="input-group-text" > &nbsp;����&nbsp; </span>
				<input type="number" class="form-control" name="prod-price" id="prod-price" aria-describedby="basic-addon1">
				<span id="" cass="input-group-text" > &nbsp;�Ǹ������Ͻ�&nbsp; </span>
				<input  type="datetime-local" class="form-control" name="prod-sell-deadline" id="prod-sell-deadline" aria-describedby="basic-addon1">	
			</div>
				
			
			<div class="input-group mb-3">
				<textarea id="editor1" rows="5" cols="60" name="prod_memo" id="prod_memo"></textarea>
			</div>
			
			<div class="input-group">
				<div class="frm-in-center">
					<button id="btn-ins" class="btn btn-primary">���</button> <input id="resetBtn" type="reset" value="���" class="btn btn-primary"/>
					<!-- <a class="btn btn-dark" href="notice" role="button">�������</a> -->
				</div>
			</div>
	</form>
	</div>
</div>


<script type="text/javascript">
	//replace() : 'editor1'�� '/farmocean/ó���� ����Ʈ��Ʈ�ѷ�'�� ����
	CKEDITOR.replace('editor1',
		{
			filebrowserUploadUrl: '/farmocean/prod/insert_product',
			width : 1000,  // �Է�â�� ����, ���̴� config.js ���� % �� ����
			height : 500,  // �Է�â�� ����
			startupFocus : false
		});	
</script>

</body>
<script src="/farmocean/resources/js/product/prod_detail_write.js"></script>
</html>