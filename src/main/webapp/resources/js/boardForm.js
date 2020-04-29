/**
 * 
 */

	var count=1;
	
	$("#file").on("click", ".remove", function() {
		$(this).parent().remove();
		count--;
	});

	$("#add").click(function() {
		if(count<6){
			$("#file").append('<div class="form-group"> <label for="file"> File :</label> <input type="file" class="form-control files" name="files"> <i class="glyphicon glyphicon-remove remove"></i> </div> ');
			count++;
		}else {
			alert("파일은 최대 5개 만 가능");
		}
	});
	/////////////////////////////////////////////////
	
	$("#contents").summernote({
		height: 300,
		callbacks:{
			onImageUpload:function(files, editor){
				console.log("d");
				var formData = new FormData();//<form></form>
				formData.append('files', files[0]); //<input type="file" name="">
				$.ajax({
					type:"POST",
					url:"../boardFile/fileInsert",
					data:formData,
					enctype:"multipart/form-data",
					cache:false,
					contentType:false,
					processData:false,
					success:function(imageName){
						console.log(imageName);
						imageName=imageName.trim();//공백제거는 습관적으로
						$("#contents").summernote('editor.insertImage', imageName);//에디터는 썸머 자기자신 */
						
					}
					
				});
			},//onImageUpload  , 로 구분
			onMediaDelete:function(files){
				var fileName= $(files[0]).attr("src");
				fileName=fileName.substring(fileName.lastIndexOf("/")); //가장 마지막 슬러시가 오는 곳 ,+1은 그다음 부터
				//파일 이름을 찾기 위해서
				console.log(fileName);
				//console.log(files); //files은 배열
				//경로명과 파일명은 속성에 있어서 속성을 꺼내 오는 것.
				////
				//아래의 url로 보낼 준비가 완성
				 $.ajax({
					type:"POST",
					url:"../boardFile/summerDelete",
					data:{
						fileName:fileName					
					},
					success:function(data){
						console.log(data);
					}
				}); 
			}//OnMediaDelete
		}	//callback
	}); 
	
	
	
	
	
	
	/////////////////////////////////////////////////
	$("#btn").click(function() {
		//title, contents 데이터 유무 검증
		var title = $("#title").val();
		var contents = $("#contents").summernote('code');
		
		var ch3 = true;
		$(".files").each(function() {
			if($(this).val()==""){
				ch3 = false;
			}
		});
		
		var ch1 = title !="";
		var ch2 = $("#contents").summernote('isEmpty');
		
		if(ch1 && !ch2 && ch3){
			//form 전송(submit event 강제 발생)
			<!-- contents Server null이 될때
			$("#con").val(contents);
			-->
			$("#frm").submit();
		}else {
			//submit event 종료
			alert("필수 요소는 다 입력하세요");
		}
		
		
/* 		var contents = $("#contents").val();
		console.log(title=='');
		console.log(contents=="");
		console.log(title.length);
		console.log(contents.length);
		
		console.log($("#contents").summernote('isEmpty')); */
	});
	
	
	
	// $("선택자").action();
	/*$("#contents").summernote({
		height: 300,
		callbacks:{
			onImageUpload:function(file){
				console.log("upload");
			}
		}
	});*/
	
	
	