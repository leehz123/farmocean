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
       width:400px;
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
            	
            		���̵�  <input type="text" id="post_buyer_id" placeholder="���̵� �Է����ּ���."><br>
           			��й�ȣ <input type="password" id="post_buyer_pw" placeholder="��й�ȣ�� �Է����ּ���."><br><br>
           			�̸� <input type="text" id="post_buyer_name"  placeholder="�̸� �Է����ּ���."> <br>
					�̸��� <input type="text" id="post_buyer_email" placeholder="�̸����� �Է����ּ���." ><br>
					�̹��� <input type="text" id="post_buyer_image" placeholder="������ ���ε����ּ���"  ><br>
            		<button id="join_btn">ȸ������</button>

        	</div> 
    	

	<script src="/farmocean/resources/js/join.js"></script>
	


</body>
</html>

