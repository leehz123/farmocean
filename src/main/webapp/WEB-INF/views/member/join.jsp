<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ȸ������</title>
<style>
   form{
       text-align: center;
       margin:0 auto;
   }

   .border{
       margin:0 auto;
       width:800px;
       height:500px;
       border:1px solid #000;
       border-radius: 10%;
       text-align: center;
       margin:0 auto;
   }


   input{
       width:300px;
   }
	</style>
</head>

<body>
	
    	
        	<div class="border">
            	<h1>ȸ������</h1>
            	
            		���̵�  <input type="text" id="post_member_id" placeholder="���̵� �Է����ּ���."><br><br>
           			��й�ȣ <input type="password" id="post_member_pw" placeholder="��й�ȣ�� �Է����ּ���."><br><br>
           			��й�ȣȮ�� <input type="password" id="post_member_pw_check" placeholder="��й�ȣ�� �Է����ּ���."><br><br>           			
           			�̸� <input type="text" id="post_member_name"  placeholder="�̸� �Է����ּ���."><br><br>
           			�г��� <input type="text" id="post_member_nickName"  placeholder="�г��� �Է����ּ���."><br><br>
					�̸��� <input type="text" id="post_member_email" placeholder="�̸����� �Է����ּ���." ><br><br>
					��ȭ��ȣ <input type="text" id="post_member_phoneNum" placeholder="��ȭ��ȣ�� �Է����ּ���." ><br><br>
					���¹�ȣ <input type="text" id="post_member_accountNum" placeholder="���¹�ȣ�� �Է����ּ���." ><br><br>
					�ּ� <input type="text" id="post_member_address" placeholder="�ּҸ� �Է����ּ���." ><br><br>
					

            		<button id="join_btn">ȸ������</button>

        	</div> 
    	

	<script src="/farmocean/resources/js/member/join.js?ver=123"></script>
	


</body>
</html>

