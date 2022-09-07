<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="${path }/resources/css/mainpage/prod_reg/goodsEnroll.css" />
</head>
<body>

				<%@ include file="../includes/prod_reg/header.jsp" %>
				
				<div class="prod_content_wrap">
					<div class="prod_content_subject"><sapn>��ǰ ���</sapn></div>
					<div class="prod_content_main">
						<form action="/mainpage/prod_reg/goodsEnroll" method="POST" id="enrollForm">
							<div class="form_section">
								<div class="form_section_title">
									<label>��ǰ �̸�</label>
								</div>
								<div class="form_section_content">
									<input name="prod_name">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>�Ǹ��� ID</label>
								</div>
								<div class="form_section_content">
									<input name="member_id">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>��ǰ ����</label>
								</div>
								<div class="form_section_content">
									<input name="prod_info">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>ī�װ� ID</label>
								</div>
								<div class="form_section_content">
									<input name="cate_idx">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>��ǰ �Ǹ� ����</label>
								</div>
								<div class="form_section_content">
									<input name="prod_sell" value="�Ǹ���">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>��ǰ ����</label>
								</div>
								<div class="form_section_content">
									<input name="prod_price">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>��ǰ �Ǹ� ����</label>
								</div>
								<div class="form_section_content">
									<input name="prod_sell_deadline" value="22/09/03 12:33:33">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>��ǰ ���</label>
								</div>
								<div class="form_section_content">
									<input name="prod_stock">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>prod_delete</label>
								</div>
								<div class="form_section_content">
									<input name="prod_delete" value="0">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>prod_heartnum</label>
								</div>
								<div class="form_section_content">
									<input name="prod_heartnum" value="0">
								</div>
							</div>
						</form>
						<div class="btn_section">
							<button id="cancelBtn" calss="btn">�� ��</button>
							<button id="enrollBtn" calss="btn enroll_btn">�� ��</button>
						</div>
					</div>
				</div>
				
				<%@ include file="../includes/prod_reg/footer.jsp" %>
	
	
	<script>
		let enrollForm = $("#enrollForm")
		
		// ��� ��ư
		$(#cancelBtn).click(function() {
			location.href="/mainpage/prod_reg/goodsManage"
		});
		
		// ��� ��ư
		$(#enrollBtn).click(function() {
			e.preventDefault();
			enrollForm.submit();
		});
		
	</script>

</body>
</html>