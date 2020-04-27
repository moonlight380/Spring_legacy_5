<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>


<script type="text/javascript">
/*나중에 해주세요*/



	$(function() {
		//체크박스 // 부모한테 한 것을 자식한테 위임(이벤트 위임)
		$("#result").on("click","#checkAll",function(){
			
			$(".check").prop("checked", $(this).prop("checked"));
		});
		
		////////////////////////////////
		$("#result").on("click",".check", function(){	
			
			var result=true;
			$(".check").each(function() {
				if(!$(this).prop("checked")){
					result=false;
				}
			});
			////////////////////////////////////////////////
			$("#checkAll").prop("checked", result);
		});
		

	
	///////////////////////////////////////////
	$("#result").on("click", "#del", function() {
			var ids = [];//빈 배열 생성
			$(".check").each(function() {
				if($(this).prop("checked")){
					//var id = $(this).attr("title");
					//alert($("#"+id).text());
					
					ids.push($(this).attr("id"));
					
				}
			});
			
			console.log(ids);
			//foreach 끝
			$.ajax({
				type:"get",
				traditional:true,
				url:"./memberDeletes",
				data: {
					ids:ids
				},
				success:function(data){
					$.get("./memberLists", function(data) {
						$("#result").html(data.trim());
					});
				}
				
			});
		});
	});// end $(function()
	
	
/* 	$(function() {
		$("result").on()
		//del -delete
		$("#del").click(function() {
			var ids=[]; // 빈 배열 생성
			$(".check").each(function() {
				if($(this).prop("checked")){
					
					//title="id${i.index}"
			/* 		var id=$(this).attr("title");
					//문자열 만들어주기 위해서 #
					alert($("#"+id).text()); 
					 */
					/* //id="${vo.id }
					alert($(this).attr("id")); */
					
					/* ids.push($(this).attr("id"));
					
					
				}
			});
			
			//foreach 끝
			
			$.ajax({
				type:"get",
				traditional:true,
				url:"./memberDeletes",
				data: {
					ids:ids
				},
				success:function(data){
					//ajxa의 ajax
					$.get("./memberLists",function(data){
						//html 덮어씌우기
						$("#result").html(data.trim());
					})
				}
				
			});
			
		});
		
	});// end $(function(){} */ 
	
	
	
</script>


</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container">
	<div class="row">
	<h1>member List </h1>
	<br>
	
	<form class="col-xs-6" action="./memberList" method="post">
    <div class="input-group">
    	<select class="form-control" id="sel1" name="kind">
		    <option value="id">id</option>
		    <option value="name">name</option>
		    <option value="phone">phone</option>
		    <option value="email">email</option>
		    
		    
		 </select>
  
      <input type="text" class="form-control" placeholder="Search" name="search">
      <div class="input-group-btn">
        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
      </div>
    </div>
 	 </form>
 	 
 	 
 	 <br>
 	 <div id="result">
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
		</div>
	 </div>
</div>
</body>	
</html>