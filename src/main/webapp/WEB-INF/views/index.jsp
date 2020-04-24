
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<c:import url="./template/boot.jsp"></c:import>

</head>
<body>
	<c:import url="./template/header.jsp"></c:import>
	<button id="btn">BUTTON</button>
	<script type="text/javascript">
		$("#btn").click(function(){
			//자바, 제이쿼리 :AJAX
			//index는 가만히 있고 백만 돌아가도록
			//제이쿼리 :AJAX
			//GET방식 //POST $.post();
			//실행하면 응답을 어떻게 받을 것? 콜백을 하는 함수를 전해줘, 펑션의 매개변수에 result를 줘라
			//보드 샐렉트하는 최종 결과물을 받는 것
			alert("start");
			$.get("./notice/noticeSelect?num=14",function(result){
				console.log(result);
			alert("finish");
			})
			
		});
	
	</script>
</body>
</html>