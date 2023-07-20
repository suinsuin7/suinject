<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 등록하기</title>
</head>
<body>
<h1>책 등록</h1>
<!-- 뷰(View) : 화면을 담당
Client -> 요청(/create) -> Server(create()메소드와 매핑. view에 create.jsp를 포워딩)
		<- 응답(create_jsp.java, create_jsp.class, HTML**) <-
웹 브라우저(크롬)에서 HTML을 렌더링 : 브라우저가 읽어서 해석
 -->
 <!-- 
 요청URL : /create
 요청파라미터 : {title=수인이의 모험, category="소설",price=10000}
 요청방식 : post
  -->
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