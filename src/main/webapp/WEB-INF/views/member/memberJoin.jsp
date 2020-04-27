<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container">
	
	<h1>MemmberJoin</h1>
 <form action="./memberJoin" method="post" enctype="multipart/form-data">

    <div class="form-group">
      <label for="id">ID:</label>
      <input type="text" class="form-control" id="id" placeholder="Enter id" name="id">
      
      
    </div>
    <div class="form-group">
      <label for="password">PASSWORD:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
    </div>
   <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
    </div>
    <div class="form-group">
      <label for="email">EMAIL:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>
    <div class="form-group">
      <label for="tel">TEL:</label>
      <input type="tel" class="form-control" id="tel" placeholder="Enter tel" name="tel">
    </div>
    <div class="form-group">
      <label for="age">Age:</label>
      <input type="text" class="form-control" id="age" placeholder="Enter age" name="age">
    </div>
    
      <div class="form-group">
      <label for="pic">Avatar:</label>
      <input type="file" class="form-control" id="avatar" placeholder="Enter Avatar" name="avatar">
    </div>
    
    
    
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>


<!-- 왜 에이작스를 이용하느냐 다른데이터를 다 쓰고 아이디 검사하면 데이터 다 날아감 -->
<script type="text/javascript">
$("#id").blur(function() {
	var id = $("#id").val();

	$.ajax({
		type: "post",	//method 형식
		url : "./memberIdCheck", //URL 주소
		data : {
			id: id
		},	//parameter
		success: function(data){
			alert(data);
		},
		error : function() {
			alert("error 발생");
		}

	});
	
});


	/* $.post("./memberIdCheck", {id:id}, function(data) {
		alert(data);
		 */
		
//////////////////////////////////////////////////////////////////////////

/* 	 	/*  id1=id1.trim(); */
		
/* 		if(result=="1"){
			location.reload(); //함수호출
		}else{
			alert("아이디 중복");
		}  

	});
	}); */
	
	
</script>
</body>
</html>