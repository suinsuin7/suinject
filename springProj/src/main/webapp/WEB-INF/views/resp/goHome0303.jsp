<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
$(function(){
	//요청 URI : /resp/goHome030101
	//요청방식 : get
	$.ajax({
		url:"/resp/goHome030101",
		type:"get",
		dataType:"json",
		success:function(result){
			console.log("result : " + JSON.stringify(result));
		}
	});
	
	//1) 도서정보 확인 버튼을 클릭
	$("#btnDetail").on("click", function(){
		console.log("찍혀용?");
		
		//2) bookId 입력값을 받아서 JSON Object에 넣어보자
		let bookId = $("input[name='bookId']").val();
		
		let data = {"bookId":bookId};
		
		console.log("data : " + JSON.stringify(data));
		
		// /resp/goHome030102?bookId=1를 요청하여 content에 내용을 넣어보자
		$.ajax({
			url:"/resp/goHome030103",
			contentType:"application/json;charset:utf-8",
			data: JSON.stringify(data),
			type:"post",
			dataType:"json",
			success:function(result){
				console.log("result : " + JSON.stringify(result));
				//result : BookVO
				//result.content : BookVO의 content멤버변수의 데이터
				$("textarea[name='content']").val(result.content);
			}
		});
		
	});	
});

</script>
<p><input type="text" name="bookId" value="1" /></p>
<p><button type="button" id="btnDetail">도서정보 확인</button></p>
<p>
	<textarea name="content" rows="5" cols="30"></textarea>
</p>