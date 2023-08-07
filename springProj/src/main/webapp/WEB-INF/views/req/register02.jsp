<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
요청URI : /req/register0201
요청파라미터 : {userId=~~}
요청방식 : post
 -->
<form action="/req/register0201" method="post">
	<p>userId : <input type="text" name="userId" value="suin" /></p>
	<p>password : <input type="text" name="password" value="suin" /></p>
	<p>coin : <input type="text" name="coin" value="1000" /></p>
	<p>
		<input type="submit" value="register0201" />
	</p>
</form>