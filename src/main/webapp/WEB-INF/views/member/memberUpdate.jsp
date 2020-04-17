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


<div class="container">
	
	<h1>UPDATE PAGE</h1>
 <form action="./memberUpdate" method="post">

    <div class="form-group">
     <label for="id">ID:</label>
      <input type="text" class="form-control" id="id" placeholder="Enter id" name="id" value="${member.id}"  readonly="readonly">
    </div>
    
    <div class="form-group">
      <label for="password">PASSWORD:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" value="${member.password}">
    </div>
    
   <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter name" name="name" value="${member.name}">
    </div>
    <div class="form-group">
      <label for="email">EMAIL:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${member.email}">
    </div>
    <div class="form-group">
      <label for="tel">TEL:</label>
      <input type="tel" class="form-control" id="tel" placeholder="Enter tel" name="tel" value="${member.tel}">
    </div>
    <div class="form-group">
      <label for="age">Age:</label>
      <input type="text" class="form-control" id="age" placeholder="Enter age" name="age" value="${member.age}">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>


</body>
</html>