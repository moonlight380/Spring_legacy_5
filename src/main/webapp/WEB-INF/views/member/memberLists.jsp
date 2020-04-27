<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    


		<table class="table table-hover">
			<tr>
				<td>ID</td>
				<td>PASSWORD</td>
				<td>NAME</td>
				<td>EMAIL</td>
				<td>TEL</td>
				<td>AGE</td>
				<td><input type="checkbox" name="check" id="checkAll"> <button id="del" class="btn btn-info" >Delete</button>
				
				</td>
				
			</tr>
			<!-- varStatus 변수명을 쓰고 하나씩 증가될 수 있도록-->
		<c:forEach items="${mlist}" var="vo" varStatus="i">
		
			<tr>
				<td id="id${i.index}">${vo.id}</td>
				<td>${vo.password}</td>
				<td>${vo.name}</td>
				<td>${vo.email}</td>
				<td>${vo.tel}</td>
				<td>${vo.age}</td>
				<td><input type="checkbox" name="del" title="id${i.index}" id="${vo.id }"class="check" ></td>
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
		
	 