<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>register</h3>
<p>
	<!-- 	
	요청URI : /board/post?register
	요청방식 : post
	요청파라미터 : register -->
	<form action="/board/post" method="post">
		<button type="submit" name="register">Register</button>
	</form>
</p>