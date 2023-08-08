<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
$(function(){
	$("#inputFile").on("change",function(){
		console.log("잘 떠?");
		
		let userId = $("input[name='userId']").val(); //suin
		let password = $("input[name='password']").val(); //suin
		//event.target : <input type="file" id="inputFile" name="pictures" multiple />
		let files = event.target.files;
		//첫 번째 파일만 선택해서 파일 object에 할당
		let file = files[0];
		//<form></form>
		let formData = new FormData();
		//<form><input type="file" name="file" /></form>
		formData.append("file",file);
		formData.append("userId",userId);
		formData.append("password",password);
		//<form><input type="file" name="file" /><input type="text" name="userId"~</form>
		$.ajax({
			url:"/req/registerFile05Post",
			processData:false,
			contentType:false,
			data:formData,
			type:"post",
			dataType:"text",
			success:function(result){
				console.log("result : " + result);
			}
			
		})
	})
})
</script>
<!-- 
요청URI: /req/registerFile05Post
요청방식 : post
 -->
<form action="/req/registerFile05Post" method="post"
	enctype="multipart/form-data">
	<!-- 텍스트 필드 요소 -->
	<p><input type="text" name="userId" value="suin" /></p>
	<p><input type="text" name="password" value="suin" /></p>
	<!-- 파일업로드 폼 파일 요소 -->
	<p><input type="file" id="inputFile" name="pictures" /></p>
	<p><input type="submit" value="파일업로드" /></p>
</form>