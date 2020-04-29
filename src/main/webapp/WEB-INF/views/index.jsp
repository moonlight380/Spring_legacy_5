
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
	<button id="btn2">BUTTON2</button>
	
	
	<script type="text/javascript">
	$("#btn2").click(function() {
		$.get("https://api.manana.kr/exchange/rate.json?base=KRW&code=KRW,USD,JPY", function(data) {
			//자신의 데이터를 꺼내서 json 형태로 바꿔서 
			console.log(data);
			console.log(data[1].rate);
		})
		
	});
		
	
		$("#btn").click(function(){
			$.get("./json/json1",function(data){
				//0.data가 String인지 Json Object인지 판별
				
				//console.log(data);  "name":"iu"  ->string
				//console.log(data);  object  -> json Object
				
				//1.String 이라면 Json Object 변환
				//data=data.trim();  //object라면 안함
				//data = JSON.parse(data); //받아온 문자열을 json으로 변환하는 것.
				/* console.log(data);
				//키를 알면 꺼내 쓸 수 있음 controller에 name이라는 키가 있음
				console.log(data.name); */
				
				
				console.log(data);
				console.log(data.title);
				console.log(data.num);
				
			})
			
			//자바, 제이쿼리 :AJAX
			//index는 가만히 있고 백만 돌아가도록
			//제이쿼리 :AJAX
			//GET방식 //POST $.post();
			//실행하면 응답을 어떻게 받을 것? 콜백을 하는 함수를 전해줘, 펑션의 매개변수에 result를 줘라
			//보드 샐렉트하는 최종 결과물을 받는 것
			/* $.get("./notice/noticeSelect?num=14",function(result){
				console.log(result);
			alert("finish");
			}) */
			
		});
	
	</script>
</body>
</html>