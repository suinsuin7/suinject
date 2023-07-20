<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 상세</title>
</head>
<body>
<h1>책 상세</h1>
 <form action="/create" method="post">
 	<!-- 폼데이터 -->
 	<p>제목 : <input type="text" name="title" required /></p>
 	<p>카테고리 : <input type="text" name="category" required /></p>
 	<p>가격 : <input type="number" name="price" maxLength="10" required /></p>
 	<p>설명 : <textarea rows="5" cols="30" name="content"></textarea> </p>
 	<p>
 		<input type="submit" value="저장" />
 		<input type="button" value="목록" />
 	</p>
 </form>
</body>
</html>