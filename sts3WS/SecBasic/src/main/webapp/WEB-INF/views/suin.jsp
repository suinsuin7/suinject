<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>여기는 수인</h1>
	<form action="/sec/logout" method="post">
		<sec:csrfInput/>
		<button>로그아웃이지용</button>
	</form>

	<input type="text" id="bj" value="bjmerong">
	<button onclick="fAjax()">아작스 전송</button>
	<script>
		//서버에서 발행된 헤더네임과 토큰갑사 저장
		var header = '${_csrf.headerName}';
		var token =  '${_csrf.token}';
		var myBJ = document.querySelector("#bj");

		function fAjax() {
	        let xhr = new XMLHttpRequest();
	        xhr.open("post", "/sec/angma/seoju", true);
	        xhr.setRequestHeader(header, token);
	        xhr.setRequestHeader('Content-Type','text/html;charset=utf-8');
	        xhr.onreadystatechange = function () {
	            if (xhr.status == 200 && xhr.readyState == 4) {
	                console.log(xhr.responseText);
	            }
	        }
	        xhr.send("mName=" + myBJ.value);
	    }

	</script>

</body>
</html>