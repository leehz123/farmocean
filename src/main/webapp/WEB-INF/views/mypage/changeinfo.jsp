<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�� ���� ����</title>
</head>
<body>

		<h3>ȸ�� ���� ����</h3>
		
		<table>
		
			<form action="changeinfo" method="POST">
			
				<c:forEach items="${memberinfo }" var="info">
	
					<tr>
						<td>���̵�</td>
						<td><input name="member_id" value="${info.member_id }" type="text" readonly/></td>
					</tr>
					<tr>
						<td>��й�ȣ</td>
						<td><input name="member_pw" value="${info.member_pw }" type="text"/></td>
					</tr>
					<tr>
						<td>�̸�</td>
						<td><input name="member_name" value="${info.member_name }" type="text" readonly/></td>
					</tr>
					<tr>
						<td>�г���</td>
						<td>
							<input id="nickname" name="member_nickName" value="${info.member_nickName }" type="text" />
							<button type="button" id="nickNameCheck">�г��� �ߺ�Ȯ��</button>
							<div id="out"></div>
						</td>
					</tr>
					<tr>
						<td>����Ʈ</td>
						<td><input name="member_point" value="${info.member_point }" type="text" readonly/></td>
					</tr>
					<tr>
						<td>�̸���</td>
						<td><input name="member_email" value="${info.member_email }" type="text" /></td>
					</tr>
					<tr>
						<td>�ּ�</td>
						<td><input name="member_address" value="${info.member_address }" type="text" /></td>
					</tr>
					<tr>
						<td>ǥ��</td>
						<td><input name="member_type" value="${info.member_type }" type="text" readonly/></td>
					</tr>
					<tr>
						<td>������ �̹���</td>
						<td><input name="member_image" value="${info.member_image }" type="text" /></td>
					</tr>
						<c:if test="${check eq 'S' }">
							<tr>
								<td>�ڵ��� ��ȣ</td>
								<td><input name="member_phoneNum" value="${info.member_phoneNum }" type="text" /></td>
							</tr>
							<tr>
								<td>���� ��ȣ</td>
								<td><input name="member_accountNum" value="${info.member_accountNum }" type="text" /></td>
							</tr>
						</c:if>
					<tr>
						<td></td>
						<td><input type="submit" value="�����ϱ�"/></td>
					</tr>
				</c:forEach>
			
			</form>
			
		</table>
		
		<a href="<c:url value="/mypage/main" />">main���� ����</a>
		<script>
		const xhttp = new XMLHttpRequest();
		const nickNameChecker = document.getElementById('nickNameCheck');
		const nickNameField = document.getElementById('nickname');
		const out = document.getElementById('out');
		
		
		nickNameChecker.addEventListener('click',(e)=>{
			xhttp.open('GET','/farmocean/member/list');
			xhttp.send();
			xhttp.addEventListener('readystatechange',(e)=>{
				 if(xhttp.readyState == 4){
				        if(xhttp.status == 200){            
				            const member = JSON.parse(xhttp.responseText);
				            const memberNickNames = new Array();
				            
				            for(i = 0 ; i < member.length;++i){		                
				                memberNickNames[i] = member[i].member_nickName;
				            }
				            if(memberNickNames.includes(nickNameField.value) || 
			            		nickNameField.value == ''||
			            		nickNameField.value == null){
				                out.innerText = "���Ұ����մϴ�";
				                out.style.color ="red";
				                
				                nickNameField.value = '';
				                nickNameField.focus();

				            } else {
				            	out.innerText = "��� �����մϴ�";
				                out.style.color ="green";    
				            }
				        }
				    }
			});
		});
		</script>
		
		
		

</body>
</html>