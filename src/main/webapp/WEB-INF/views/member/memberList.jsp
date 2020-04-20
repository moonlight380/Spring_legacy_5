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
	<div class="row">
	<h1>member List </h1>
	<br>
	
	<form class="col-xs-6" action="./memberList" method="post">
    <div class="input-group">
    	<select class="form-control" id="sel1" name="kind">
		    <option value="id">id</option>
		    <option value="name">name</option>
		    <option value="phone">phone</option>
		    <option value="email">email</option>
		    
		 </select>
  
      <input type="text" class="form-control" placeholder="Search" name="search">
      <div class="input-group-btn">
        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
      </div>
    </div>
 	 </form>
 	 
 	 <br>
		<table class="table table-hover">
			<tr>
				<td>ID</td>
				<td>PASSWORD</td>
				<td>NAME</td>
				<td>EMAIL</td>
				<td>TEL</td>
				<td>AGE</td>
			</tr>
			<c:forEach items="${mlist}" var="vo">
			<tr>
			<td>${vo.id}</td>
			<td>${vo.password}</td>
			<td>${vo.name}</td>
			<td>${vo.email}</td>
			<td>${vo.tel}</td>
			<td>${vo.age}</td>
			</tr>
			
			</c:forEach>

		</table>
		<div>
		<ul class="pagination">
		<c:if test="${mp.curBlock gt 1 }">
		<!-- 1보다 크다면 -->
				<li><a href="./memberList?curPage=${mp.startNum-1}&kind=${mp.kind}&search=${mp.search}">이전</a></li>
		</c:if>
			<c:forEach begin="${mp.startNum}" end="${mp.lastNum}" var="i">
				<li><a href="./memberList?curPage=${i}&kind=${mp.kind}&search=${mp.search}">${i}</a></li>
			</c:forEach>
		<c:if test="${mp.curBlock lt mp.totalBlock}">
		<!-- 작다면 lt -->
			<li><a href="./memberList?curPage=${mp.lastNum+1}&kind=${mp.kind}&search=${mp.search}">다음</a></li>
		</c:if>
		</ul>
		
		
		
	</div>
</div>
</body>	
</html>