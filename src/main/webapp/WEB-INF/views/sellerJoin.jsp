<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>�Ǹ��� ȸ������</title>
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
            	<h1>�Ǹ��� ȸ������</h1>
            	
            		���̵�  <input type="text" id="post_seller_id" placeholder="���̵� �Է����ּ���."><br><br>
           			��й�ȣ <input type="password" id="post_seller_pw" placeholder="��й�ȣ�� �Է����ּ���."><br><br>
           			��й�ȣȮ�� <input type="password" id="post_seller_pw_check" placeholder="��й�ȣ�� �Է����ּ���."><br><br>
           			�̸� <input type="text" id="post_seller_name"  placeholder="�̸� �Է����ּ���."><br><br>
					�̸��� <input type="text" id="post_seller_email" placeholder="�̸����� �Է����ּ���." ><br><br>
					�޴�����ȣ <input type="text" id="post_seller_phoneNum" placeholder="�̸����� �Է����ּ���." ><br><br>
					���¹�ȣ <input type="text" id="post_seller_accountNum" placeholder="�̸����� �Է����ּ���." ><br><br>

            		<button id="join_btn">�Ǹ��� ȸ������</button>

        	</div> 
    	

	<script src="/farmocean/resources/js/sellerjoin.js"></script>
	


</body>
</html>

