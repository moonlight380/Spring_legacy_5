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
	<h1>마이페이지</h1>
	
		<table class="table table-hover">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
			<td>전화번호</td>
			<td>나이</td>

		</tr>
		
				<tr class="info">
					<td>${sessionScope.member.id}</td> <!-- jsp에서 꺼내는 방법은 el을 통해서 꺼낸다. -->
					<td>${member.name}</td> <!--  영역별로 같은 이름을 넣는다면 속성명을 써야 한다. 왜냐하면 내장객체 page부터 차례대로 이름을 찾기 때문 -->
					<td>${member.email}</td>
					<td>${member.tel}</td>
					<td>${member.age}</td>
				</tr>		
		
		</table>	
		
		<button class ="btn btn-primary" id="up">UPDATE</button>
		<button class ="btn btn-danger" id="del">DELETE</button>
	</div>
</div>	
<script type="text/javascript">
	//js document.getElementById
	//js document.querySelector
	//jquery $(선택자)
	$('#del').click(function(){
		var result=confirm("정말 탈퇴하시겠습니까?");
		if(result){
			/* location.href="./memberDelete?id=${sessionScope.member.id}";	아이디가 주소창에 드러나기 때문에 이 방법은 쓰지 않음 */
			 location.href="./memberDelete?"
		}else{
			location.href="#";
		}
	});
	
	$("#up").click(function(){
		var result=confirm("수정하시겠습니까?");
		
		if(result){ 
			location.href="./memberUpdate";	
	 	}
		
		
	});
	
	
 	
</script>
</body>
</html>