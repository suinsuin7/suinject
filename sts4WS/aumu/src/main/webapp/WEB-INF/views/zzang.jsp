<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Model 데이터 확인할 땐 항상 해당 페이지에 통째로 찍어서 디버깅</h1>
	<p>${hungryList}</p>
	<h1>머라 썼어요??</h1>
	<h1>${hungryList.get(1)}</h1>
<!-- hungryList를 자바스트립트 배열에 옮기시오 -->
<script>
	let hungryList = []; // jstl c:foreach를 이용해서
	let merong = "${hungryList.get(1)}";
	alert(merong);
</script>
</body>
</html>