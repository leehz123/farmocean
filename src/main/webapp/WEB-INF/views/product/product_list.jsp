<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상품 목록 페이지</title>
<%@ include file="/resources/jspf/header.jspf" %>
<%@ include file="/resources/jspf/csboard.jspf" %>

<style>
    body {
        padding: 0;
        margin: 0;
        height : 300vh;
    }

    html {
       /* font-size: 20px; */
    }

    #product-list-container {
        
        width: 80vw;
        height: 240vh;
        display: grid;
        background-color: cornflowerblue;

        grid-template-rows: 60vh 60vh 60vh 60vh;
        grid-template-columns: 20vw 20vw 20vw 20vw;
    }


    .product {
        background-color: white;
        border: 1px solid black;
        /* width: 50vh; */
        margin: 2vh 1vw;        
    }
    
    
    #list-paging-set {
    	
    	position: absolute;
        top: 30vh;
        left: 10vw;
        
    	display: flex;
    	flex-direction: column;
    	width: 80vw;
    	height: 280vh;
    	background-color : orange;
    }
    
    #paging-container {
    	margin-top: 5vh;
    	height: 13vh;
    	width: 80vw;
    	background-color : yellow;
    }
    
    
</style>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>


	<div id="list-paging-set">
	    <div id="product-list-container">
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	        <div class="product"></div>
	    </div>
	    
	    <div id="paging-container"></div>
	</div>


</body>
</html>