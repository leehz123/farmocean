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
<div class="container">
	<div class="row">
		<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		
		<div class="col-md-9" id="adminBody">
			<h1>[작업중]메인 상단 배너 등록</h1>
			<hr />			
			<form id="frmMainTopBanner" name="frmMainTopBanner" method="post" action="<c:url value="/admin/setMainTopBanner" />" enctype="multipart/form-data">
				<input type="hidden" name="mainTopIdx" id="" value=""/>
				<input type="hidden" name="mainTopFileName" id="" value=""/>
				배너이름 : <input type="text" name="mainTopName" id="mainTopName" value="test1" />
				배너링크 : <input type="text" name="mainTopLink" id="mainTopLink" value="/" />
				배너이미지 : <input type="file" name="mainTopImg" id="mainTopImg" /> <br />
				<input type="hidden" name="mainTopIdx" id="" value=""/>
				<input type="hidden" name="mainTopFileName" id="" value=""/>
				배너이름 : <input type="text" name="mainTopName" id="mainTopName" value="테스트2" />
				배너링크 : <input type="text" name="mainTopLink" id="mainTopLink" value="<c:url value="/board/" />" />
				배너이미지 : <input type="file" name="mainTopImg2" id="mainTopImg2" /> <br />
				<input type="hidden" name="mainTopIdx" id="" value=""/>
				<input type="hidden" name="mainTopFileName" id="" value=""/>
				배너이름 : <input type="text" name="mainTopName" id="mainTopName" value="test3" />
				배너링크 : <input type="text" name="mainTopLink" id="mainTopLink" value="<c:url value="/board/notice" />" />
				배너이미지 : <input type="file" name="mainTopImg3" id="mainTopImg3" /> <br /> <br />
				
				<button id="btnFrmSubmit">등록</button>
				<input type="reset" />
			</form>
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>