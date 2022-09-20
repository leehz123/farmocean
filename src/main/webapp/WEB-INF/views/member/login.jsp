<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>Insert title here</title>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="/farmocean/resources/js/member/login.css">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/a81368914c.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<script>
   sessionStorage.clear();
</script>

<body>


 <section clsas="section1">
        <div class="sec1-cont">
	        <form method="post">
	            <div class="sec1-left">
	                <div class="sec1-tit">
	                <h2>�ʿ��� �α���</h2>
	                    <h5>�����Ͻ� ������ �α��� �ϼ���</h5>
	                </div>
	                <div class="sec1-login">
	                	<input type="hidden" id="retUrl" name="retUrl" value="${retUrl }" />
	                    <input type="text" id="member_id" name="member_id" placeholder="ID" required><br>
	                    <input type="password" id="member_pw" name="member_pw" placeholder="Password" required>
	                </div>
	                <div class="sec1-login-btn">
	                   <input class="login-box" formaction="/farmocean/member/logincheck" type="submit" class="btn" value="Login">
						<div id="naver_id_login"></div>
	                </div>
	            </div>
           	</form>
	            <div class="sec1-right">
	                <div class="sec1-right-top">
	                    <div class="sec1-tit">
	                        <h2>ȸ������</h2>
	                        <h5>�ʿ��� ȸ���� �Ǿ� ���񽺸� �̿��غ�����</h5>
	                    </div>
	                    <div class="sec1-btn">
	                        <a href="/farmocean/member/join"><div>������ȸ������</div></a>
	                        <a href="/farmocean/member/sellerjoin"><div>�Ǹ���ȸ������</div></a>
	                    </div>
	                </div>
	
	                <div class="sec1-right-bottom">
	                    <div class="sec1-tit">
	                        <h2>ID/PW ã��</h2>
	                        <h5>���̵�, ��й�ȣ�� ��ﳪ�� �����Ű���?</h5>
	                    </div>
	                    <div class="sec1-btn">
	                        <a href="/farmocean/member/searchId"><div>���̵� ã��</div></a>
	                        <a href="/farmocean/member/searchPw"><div>��й�ȣ ã��</div></a>
	                    </div>
	                </div>
	            </div>
	        </div>
    </section>
    
	<div id="naver_id_login"></div>
	<script type="text/javascript">
     var naver_id_login = new naver_id_login("tFcf6kO8bBQSvTpMwwWV", "http://localhost:8888/farmocean/member/naver_callback");
     var state = naver_id_login.getUniqState();
     naver_id_login.setButton("white", 5,70);
     naver_id_login.setDomain("http://localhost:8888/farmocean/member/login");
     naver_id_login.setState(state);
     // naver_id_login.setPopup(true);
     naver_id_login.init_naver_id_login();
  </script>

	<script>
   
   
   
   
   </script>

</body>
</html>