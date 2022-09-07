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
          <a class="nav-link active" aria-current="page" href="#">�̿�ȳ�</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">�����˻�</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            �ڷ�˻�
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#">�������˻�</a></li>
            <li><a class="dropdown-item" href="#">�����ڷ�˻�</a></li>            
            <li><a class="dropdown-item" href="#">��õ����</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">�غ���..</a>
        </li>
      </ul>
      <form class="d-flex">
        ���� �˻�<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
�˻� ���
<table class="table">
  <thead>
    <tr>
      <th scope="col">������ȣ</th>
      <th scope="col">����</th>
      <th scope="col">����</th>
      <th scope="col">���ǻ�</th>
      <th scope="col">����⵵</th>
    </tr>
  </thead>
  <tbody>   
    <tr>
      <th scope="row">1</th>
      <td>(���α׷����� �����ϴ� ������� ����) Good java</td>
      <td>���糲 </td>
      <td>�Ѻ���ī����</td>
      <td>2016</td>
    </tr>  
    <tr>
      <th scope="row">2</th>
      <td>(Javascript�� ����) �ڷᱸ�� = Learning data structures in javascript</td>
      <td>���ֿ� </td>
      <td>����������</td>
      <td>2019</td>
    </tr>  
    <tr>
      <th scope="row">3</th>
      <td>Java�� ���� : �ֽ� Java 8.0 ����. 1</td>
      <td>���ü�  </td>
      <td>����</td>
      <td>2016</td>
    </tr>  
    <tr>
      <th scope="row">4</th>
      <td>Java�� ���� : �ֽ� Java 8.0 ����. 2</td>
      <td>���ü�  </td>
      <td>����</td>
      <td>2016</td>
    </tr>
    <tr>
      <th scope="row">5</th>
      <td>(���� ����) HTML5 & CSS & JavaScript</td>
      <td>�ֿ���   </td>
      <td>�Ͻ�Ȧ���ۺ���</td>
      <td>2019</td>
    </tr> 
    <tr>
      <th scope="row">6</th>
      <td>(New)Java programming with a workbook : Java Bean ���</td>
      <td>����, ��ö [��]��   </td>
      <td>�������ǻ�</td>
      <td>2009</td>
    </tr> 
    <tr>
      <th scope="row">7</th>
      <td>�ֽ� Java ���α׷���</td>
      <td>������ </td>
      <td>21�����</td>
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