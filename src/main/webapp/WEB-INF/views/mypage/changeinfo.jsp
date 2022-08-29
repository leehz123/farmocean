<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 정보 수정</title>
</head>
<body>

		<h3>회원 정보 수정</h3>
		
		<table>
		
			<form action="changeinfo" method="POST" action="https://test.mobilians.co.kr/mcash_webnoti/step1.php" accept-charset="EUC-KR">
			
				<c:forEach items="${memberinfo }" var="info">
	
					<tr>
						<td>아이디</td>
						<td><input name="member_id" value="${info.member_id }" type="text" readonly/></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input name="member_pw" value="${info.member_pw }" type="text"/></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input name="member_name" value="${info.member_name }" type="text" readonly/></td>
					</tr>
					<tr>
						<td>닉네임</td>
						<td>
							<input id="nickname" name="member_nickName" value="${info.member_nickName }" type="text" />
							<button type="button" id="nickNameCheck">닉네임 중복확인</button>
							<div id="out"></div>
						</td>
					</tr>
					<tr>
						<td>포인트</td>
						<td><input name="member_point" value="${info.member_point }" type="text" readonly/></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input name="member_email" value="${info.member_email }" type="text" /></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input name="member_address" value="${info.member_address }" type="text" /></td>
					</tr>
					<tr>
						<td>표시</td>
						<td><input name="member_type" value="${info.member_type }" type="text" readonly/></td>
					</tr>
					<tr>
						<td>프로필 이미지</td>
						<td><input name="member_image" value="${info.member_image }" type="text" /></td>
					</tr>
						<c:if test="${check eq 'S' }">
							<tr>
								<td>핸드폰 번호</td>
								<td><input name="member_phoneNum" value="${info.member_phoneNum }" type="text" /></td>
							</tr>
							<tr>
								<td>계좌 번호</td>
								<td><input name="member_accountNum" value="${info.member_accountNum }" type="text" /></td>
							</tr>
						</c:if>
					<tr>
						<td></td>
						<td><input id="subBtn" type="submit" value="수정하기"/></td>
					</tr>
				</c:forEach>
			
			</form>
			
		</table>
		
		<button id="btn">테스트 버튼</button>
		
		<a href="<c:url value="/mypage/main" />">main으로 가기</a>
		
		<script>
		const xhttp = new XMLHttpRequest();
		const nickNameChecker = document.getElementById('nickNameCheck');
		const nickNameField = document.getElementById('nickname');
		const out = document.getElementById('out');
		const subBtn = document.getElementById('subBtn');
		
		const divBtn = document.getElementById('divBtn');
		//const check;
		
		
		nickNameChecker.addEventListener('click',(e)=>{
			
			// 닉네임 제약조건
			const pattern = new RegExp("^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$")

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
				            
				            if(memberNickNames.includes(nickNameField.value)) {
				            	out.innerText = "이미 존재하는 닉네임 입니다";				            	
				                out.style.color ="red"
				                
				                nickNameField.value = '';
				                nickNameField.focus();
				            } 
				            
				            else if(nickNameField.value == ''){
				            	out.innerText = "닉네임이 비어있습니다";				            	
				                out.style.color ="red"
				                
				                nickNameField.value = '';
				                nickNameField.focus();
				            }
				            
				            else if(nickNameField.value == null){
				            	out.innerText = "닉네임이 null입니다";				            	
				                out.style.color ="red"
				                
				                nickNameField.value = '';
				                nickNameField.focus();
				            }
				            
				            
				            else if(!pattern.test(nickNameField.value)) {
				            	//alert('2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성되어야 합니다??');
				            	
				            	out.innerText = "2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성되어야 합니다";
				            	out.style.color ="red";
				                
				                nickNameField.value = '';
				                nickNameField.focus();
				            } else {
				            	out.innerText = "사용 가능합니다";
				                out.style.color ="green";    				            	
				            } 
				            
				        }
				    }
			});
		});
		
		subBtn.addEventListener('click',(e)=>{
			const check = out.innerText;
			
			if(check == '사용 가능합니다') {
				
			} else {
				alert('닉네임 중복확인을 완료 해주세요');
				// 이벤트 중단
				e.preventDefault();
			} 
		});		
		
		
		 function emulAcceptCharset(form) {
			   if (form.canHaveHTML) {
			     document.charset = form.acceptCharset;
			   }

			   return true;
			 }
		 
		 
		 function OpenWin() {
			   var f = document.cplogn;
			  
			   shape = 'width=520,height=650,';
			   shape += 'left=70,top=10,toolbar=no,location=no,directories=no,status=yes,';
			   shape += 'menubar=yes,scrollbars=no,resizable=yes';
			  
			   var win = open('', 'MC', shape);
			  
			   f.target='MC';
			  
			   emulAcceptCharset(f);
			  
			   f.submit();
			  
			   if(win.focus){win.focus();}
			 }
		</script>
		
		
		

</body>
</html>