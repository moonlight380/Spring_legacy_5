<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<%-- <div class="container">
<div class="row">
	<h1>TITLE:${vo.title}</h1>
	<h1>WRTIER:${vo.writer}</h1>
	<h1>CONTENTS:${vo.contents}</h1>
	<h1>DATE:${vo.regDate}</h1>
	<h1>HIT:${vo.hit}</h1>
</div> 
</div>--%>

<div class="container">
  <div class="panel panel-default">
    <div class="panel-heading">TITLE</div>
    <div class="panel-body">${vo.title}</div>
  </div>
  
    <div class="panel panel-default">
    <div class="panel-heading">Writer</div>
    <div class="panel-body">${vo.writer}</div>
  </div>
  
      <div class="panel panel-default">
    <div class="panel-heading">Contents</div>
    <div class="panel-body">${vo.contents}</div>
  </div>
  
  <div class="panel panel-default">
  <div class="panel-body">${vo.regDate}</div>
</div>

<div>
	<a href="./${board }Update?num=${vo.num}"  class="btn btn-danger">Update</a>
	<a href="./${board }Delete?num=${vo.num}"  class="btn btn-primary">Delete</a>
	<c:if test="${board ne 'notice'}">
	<a href="./${board }Reply?num=${vo.num}"  class="btn btn-info">Reply</a>
	</c:if>
</div>
</div>
	
</body>
</html>