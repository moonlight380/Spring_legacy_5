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
<form action="./${board}Write" method="post" enctype="multipart/form-data" >

    <div class="form-group">
      <label for="title">title:</label>
      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title" >
    </div>
   <div class="form-group">
      <label for="writer">writer:</label>
      <input type="text" class="form-control" id="writer" placeholder="Enter writer" name="writer" >
    </div>

   <div class="form-group" >
		    <label for="contents">Contents:</label>
		    <textarea rows="20" cols="" class="form-control" id="contents" name="contents"></textarea>
	</div> 
	
	
	<div class="form-group" >
		    <label for="files">Files:</label>
		   <input type="file" class="form-control" name="files">
		   <input type="file" class="form-control" name="files">
	</div> 
	
	

    <input type="submit" class="btn btn-default">Submit</input>
  </form>

</div>

<script type="text/javascript">
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