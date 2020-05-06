<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
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
<form action="./${board}Update" method="post" enctype="multipart/form-data">
<!-- &lt :꺽쇠 -->
	<h1>${fn:toUpperCase(board)} Update Form</h1>
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

 	  <input type="button" id="add" class="btn btn-info" value="AddFile">
		<div id="file">
		
		</div>
		
		
	  <div class="form-group" >
	  		
		  	<label for="files">Files:</label>
		  	<c:catch>
		  	<c:forEach items="${vo.boardFileVOs}" var="fileVO">
			  	<p>${fileVO.oriName}<i id="${fileVO.fileNum }" title="${fileVO.board }" class="glyphicon glyphicon-remove remove fileDelete"></i></p>
		  	
		  	</c:forEach>
		  	</c:catch>
		  	
		  </div>
	
   <input type="submit" id="btn" class="btn btn-default" value="Write">
  </form>

</div>





<%-- 	//$("선택자").action();
/* 	$("#contents").summernote({
		height: 400,                 
		minHeight: null,            
		maxHeight: null,             
		focus: true    
	
	});
	var size=${size};
	size=${vo.boardFileVOs.size()};// 뒤에 있는 사이즈 자동으로 게터를 찾는데 없으니까 자동으로 문자열로 인식
	// 따라서 게터가 아닌 메서드들을 부를때 소괄호 해서 메서드 형식으로 쓴다.
	// el에서 직접호출
	size=${fn:length(vo.boardFileVOs)};
	alert(size); */ --%>
<script type="text/javascript" src="../resources/js/boardForm.js"></script>
	<script type="text/javascript">
		$("#contents").summernote('code', '${vo.contents}');
		
		var size = ${size};
		
		size = ${vo.boardFileVOs.size()};
		
		size = ${fn:length(vo.boardFileVOs)};
		
		
		
		count = count+size;
		
		$(".fileDelete").click(function() {
			
			var check = confirm("정말 지울 거냐??");
			
			if(check){
				var s = $(this);
				
				$.post("../boardFile/fileDelete", {fileNum:$(this).attr("id"), board:$(this).attr("title")}, function(data) {
					//키없이 값만 오면 trim 할 필요가 없다.
					//data.trim()>0
					if(data>0){
						s.parent().remove();
						count--;
					}else {
						alert("File Delete Fail");
					}
				} );
			}
		});
	</script>
</body>
</html>