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
			<div class="form-group">
  				<label for="writer">Name:</label>
  				<input type="text" class="form-control" id="writer">
			</div>
			
			<div class="form-group">
  				<label for="comment">Contents:</label>
  				<textarea class="form-control" rows="5" id="contents"></textarea>
			</div>
			
			
			<button id="btn" class="btn btn-danger">WRITE</button>
		</div>
		
		<div class="row">
		<!-- 겟리스트값을 가지고 온건데 result안에다가 집어 넣을 수 있도록 -->
		<table  id="result"class="table table-boarder">
			<tr>
				<td>NUM</td>
				<td>Contents</td>
				<td>Writer</td>
				<td>Date</td>
			</tr>
		
		</table>
	</div>
	
	<div>
		<button id= more>더보기</button>
	</div>
	</div>
	
	
	
	
<script type="text/javascript">
	var count=1;
	//두번쓰니까 함수로 만들기
		function getList(curPage) {
			$.get("getList?curPage="+curPage, function(result) {
				$("#result").append(result);
			});	
		}
		
	getList(count);

	//더보기
	$("#more").click(function(){
		count++
		//카운트로 보낸후에 카운트로 가자~
		getList(count);
	});
	
/* 	<!-- 겟리스트값을 가지고 온건데 result안에다가 집어 넣을 수 있도록 -->
	//버튼 write// contents */
	$("#btn").click(function() {
			var writer = $("#writer").val();
			var contents = $("#contents").val();
			
			//공백으로 바뀌면서 들어갈 수 있도록
			$("#writer").val('');
			$("#contents").val('');
			//$.get("url?name=value")
			//$.post()
			//파라미터가 2개 이상이 들어가서 두가지를 묶어줄 {파라미터이름, (변수명)밸류}  문자열 형식 아님
			$.post("./memoInsert", {writer:writer, contents:contents}, function(result) {
				result=result.trim();
				
				if(result>0){
					location.reload(); //함수호출
					//내글이 가장 위에 있어야 하므로 가장 최신 글이 있는 1페이지로 바꾼 후에
					/* count=1;
					getList(count); */
				}else{
					alert("write fail");
				}
				//alert(result);
				//alert(result=='1'); 공백이 있어서 다듬어줘야 함
			});
		});
	
	</script>
	
</body>
</html>