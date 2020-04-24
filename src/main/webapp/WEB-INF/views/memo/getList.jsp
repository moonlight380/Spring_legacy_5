<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<table class="table table-striped">

<c:forEach items="${list}" var="memo">
	<tr class="table table-hover">
		<td>${memo.num}</td>
		<td>${memo.contents}</td>
		<td>${memo.writer}</td>
		<td>${memo.regDate}</td>
	</tr>

</c:forEach>
</table>
