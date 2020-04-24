/**
 * 
 */

	 /* 	<div class="form-group" >
	  <label for="contents">Contents:</label>
		<input type="file" class="form-control files" name="files">		   
	</div>  */

	var count=1;
    $("#add").click(function() {
    
		 if(count<6){		
			 $("#file").append('<div class="form-group" > <label for="file"> File :</label> <input type="file" class="form-control files" name="files"> <i class="glyphicon glyphicon-remove remove"></i> </div> ');
			 count++; 
		 }else{
			 alert("파일은 최대5개 입니다.");
		 } 
    });
    
	//부모를 클릭하면 자식에게 이벤트 위임
	$("#file").on("click", ".remove", function() {
		alert("파일을 삭제하겠습니까?");
		$(this).parent().remove();
		count--;
	});
    
	//$("선택자").action();
	$("#contents").summernote({
		height: 400,                 
		minHeight: null,            
		maxHeight: null,             
		focus: true    
	});
	//제이쿼리
	$("#btn").click(function(){
		/* //title,contents 데이터 유무 검증 */
		var title=$("#title").val();
		contents =$("#contents").summernote('code');  //summernote로 하고 있을 때 
		
		
		/* //반복문 */
		var ch3=true;
		$(".files").each(function(){
			if($(this).val()==""){
				ch3=false;
		}});
		
		
 		var ch1=title !="";
		var ch2= $("#contents").summernote('isEmpty'); 
		//console.log($("#contents").summernote('isEmpty'));
		
	 	if(ch1 && !ch2 &&ch3){
	 		/* //contents가 null
	 		/* $("#con").val(contents);  */
	 		
	 		
			/* //form을 전송(submit event 강제 발생) */
			$("#frm").submit();/* //강제 발생했을 때 */
			
		}else{
			/* //submit event 종료 */
			alert("필수 요소를 다 입력하세요");
		}   
	 	
	});
	 		
	 /* 	var contents=$("#contents").val();
		console.log(title=='');
		console.log(contents=="");
		console.log(title.length);
		console.log(contents.length); */
	
	
/* 	//자바스크립트
	var btn=document.getElememtById("btn");
	btn.addEventListner("click",function(){
		
	})  */

	
	
	
