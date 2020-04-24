<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
<c:import url="../template/summer.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container">
<form action="./${board}Update" method="post">

      <input type="hidden"  id="num" name="num" value="${vo.num}">
   	<!-- mapper에서 몇개의 데이터를 보내주는지 체크 -->
    
    <div class="form-group">
      <label for="title">title:</label>
      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title" value="${vo.title}">
    </div>
    
   <div class="form-group">
      <label for="writer">writer:</label>
      <input type="text" class="form-control" id="writer" placeholder="Enter writer" name="writer" value="${vo.writer}" disabled="disabled">
    </div>

    <div class="form-group" >
		    <label for="contents">Contents:</label>
		<textarea rows="5" cols="" class="form-control" id="contents" name="contents">${vo.contents}</textarea>
	</div> 

<!-- 	  <input type="button" id="add" class="btn btn-info" value="AddFile">
		<div id="file">
			
		</div> -->

    <button type="submit" class="btn btn-default">Submit</button>
  </form>

</div>



<!-- <script type="text/javascript" src="../resources/js/boardForm.js"> -->
<script type="text/javascript" ">
	//$("선택자").action();
	$("#contents").summernote({
		height: 400,                 
		minHeight: null,            
		maxHeight: null,             
		focus: true    
		
	});
	
	
</script>
</body>
</html>