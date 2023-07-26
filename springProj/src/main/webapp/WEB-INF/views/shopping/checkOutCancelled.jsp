<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
//out.print(greeting);
// out.print(test);//문제발생!
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<title>주문 취소</title>
</head>
<body>
	<!-- ////////////////// header 시작 (menu.jsp) /////////////////// -->
	<%@ include file="menu.jsp" %>
	<!-- ////////////////// header 끝 /////////////////// -->
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">
				주문 취소
			</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="text-center">
			<h2 class="alert alert-danger">주문이 취소되었습니다</h2>
		</div>
	</div>
	
	<div class="container">
    	<p><a href="/shopping/products" class="btn btn-secondary">&laquo;상품 목록</a></p>
	</div>
	<!-- /////////////// footer 시작 (footer.jsp) /////////////// -->
	<%@ include file="footer.jsp" %>
	<!-- /////////////// footer 끝 (footer.jsp) /////////////// -->
<script type="text/javascript">
//현재시간 처리
function gogo(){
	//JSTL변수를 J/S변수에 할당
	let tagline = "${tagline}";
	console.log("tagline : " + tagline);
	
	//<h3>태그 select(object)
	let h3 = document.getElementsByTagName("h3")[0];
	
	let today = new Date();
	
	console.log("today : " + today);
	
	let year = today.getFullYear();
	let month = ('0' + (today.getMonth() + 1)).slice(-2);
	let day = ('0' + today.getDate()).slice(-2);
	
	let dateString = year + "-" + month + "-" + day;
	
	console.log("dateString : " + dateString);
	
	let hours   = ('0' +   today.getHours()).slice(-2);
	let minutes = ('0' + today.getMinutes()).slice(-2);
	let seconds = ('0' + today.getSeconds()).slice(-2);
	
	let timeString = hours + ":" + minutes + ":" + seconds;
	
	console.log("result : " + dateString + " " + timeString);
	//select
	h3.innerHTML = tagline + " " + "현재 접속 시각 : " 
				+ dateString + " " + timeString;
}
setInterval(gogo,1000);	//1초마다 gogo함수를 요청
</script>
</body>
</html>







