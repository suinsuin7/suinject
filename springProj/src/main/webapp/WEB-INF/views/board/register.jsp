<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#btnHeaders").on("click", function (){
        let boardNo = "10";
        let title = "모순";
        let content = "모순 - 양귀자";
        let writer = "양귀자";

        let data = {
            "boardNo": boardNo,
            "title": title,
            "content": content,
            "writer": writer
        };

        //JSON: Object => String 변환
        //JSON : JavaScript Object Notation(표기법)
        //		클라이언트(브라우저)와 서버 간에 데이터를 교환할 때 활용. 텍스트.
        //		모든 자바스크립트 객체를 JSON으로 변환하고 JSON을 서버로 보낼 수 있음
        //		서버에서 받은 JSON을 자바스크립트 객체로 변환할 수 있음
        console.log("data: ", JSON.stringify(data));
        
        //아작나써유 피씨다타써
        /*
        	요청URI : /board/10
        	PathVariable : boardNo
        	요청방식 : post
        */
        $.ajax({
        	url: "/board/"+boardNo,
        	contentType: "application/json;charset=utf-8",
        	data: JSON.stringify(data),
        	type: "post",
        	success: function(result){
        		console.log("result : " + result);
        	}
        });
    });
	
	//도서상세정보(/detail?bookId=1)
	//요청URI : /board/9
	//pathVariable: boardNo -> bookId로 처리예정
	//요청방식 : get
	//data(응답타입) : JSON
	$("#btnBookDetail").on("click", function(){
		console.log("btnBookDetail 잘 왔나?");
		
		let bookId = $("input[name='bookId']");
		let boardNo = "1";
		
		$.get("/board/" + boardNo, function(result){
			console.log("result : " + JSON.stringify(result));
			 
		});
		
	});
});

</script>
<h3>register</h3>
<p>
<form action="/board/post" method="post">
    <input type="text" name="bookId" value="ISBN1234" />
    <button type="submit" name="register">REGISTER</button>
    <button type="submit" name="update">UPDATE</button>
    <button type="submit" name="delete">DELETE</button>
    <button type="submit" name="list">LIST</button>
</form>

<form action="/board/post" method="post">
	<a href="/board/get?read" class="btn btn-primary">READ</a>
	<button type="button" id="btnHeaders">HEADERS MAPPING</button>
    <button type="submit" name="remove">REMOVE</button>
    <button type="button" id="btnBookDetail">bookDetail</button>
</form>
</p>
