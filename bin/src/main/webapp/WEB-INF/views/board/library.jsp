<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>sample</title>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">이용안내</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">도서검색</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            자료검색
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#">주제별검색</a></li>
            <li><a class="dropdown-item" href="#">신작자료검색</a></li>            
            <li><a class="dropdown-item" href="#">추천도서</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">준비중..</a>
        </li>
      </ul>
      <form class="d-flex">
        도서 검색<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
검색 결과
<table class="table">
  <thead>
    <tr>
      <th scope="col">도서번호</th>
      <th scope="col">제목</th>
      <th scope="col">저자</th>
      <th scope="col">출판사</th>
      <th scope="col">발행년도</th>
    </tr>
  </thead>
  <tbody>   
    <tr>
      <th scope="row">1</th>
      <td>(프로그래밍을 시작하는 사람들을 위한) Good java</td>
      <td>우재남 </td>
      <td>한빛아카데미</td>
      <td>2016</td>
    </tr>  
    <tr>
      <th scope="row">2</th>
      <td>(Javascript로 배우는) 자료구조 = Learning data structures in javascript</td>
      <td>오주영 </td>
      <td>에듀컨텐츠</td>
      <td>2019</td>
    </tr>  
    <tr>
      <th scope="row">3</th>
      <td>Java의 정석 : 최신 Java 8.0 포함. 1</td>
      <td>남궁성  </td>
      <td>도우</td>
      <td>2016</td>
    </tr>  
    <tr>
      <th scope="row">4</th>
      <td>Java의 정석 : 최신 Java 8.0 포함. 2</td>
      <td>남궁성  </td>
      <td>도우</td>
      <td>2016</td>
    </tr>
    <tr>
      <th scope="row">5</th>
      <td>(쉽게 배우는) HTML5 & CSS & JavaScript</td>
      <td>최옥경   </td>
      <td>북스홀릭퍼블리싱</td>
      <td>2019</td>
    </tr> 
    <tr>
      <th scope="row">6</th>
      <td>(New)Java programming with a workbook : Java Bean 기반</td>
      <td>방대욱, 고병철 [공]저   </td>
      <td>이한출판사</td>
      <td>2009</td>
    </tr> 
    <tr>
      <th scope="row">7</th>
      <td>최신 Java 프로그래밍</td>
      <td>한정란 </td>
      <td>21세기사</td>
      <td>2019</td>
    </tr>
  </tbody>
</table>

<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item disabled">
      <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
    </li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
      <a class="page-link" href="#">Next</a>
    </li>
  </ul>
</nav>

</body>
</html>