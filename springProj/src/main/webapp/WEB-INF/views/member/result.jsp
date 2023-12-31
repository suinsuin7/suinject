<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<p>아이디 : ${memberVO.userId}</p>
<p>이름 : ${memberVO.userName}</p>
<p>비밀번호 : ${memberVO.password}</p>
<p>소개 : ${memberVO.introduction}</p>
<p>취미 :  
<c:forEach var="hobby" items="${memberVO.hobbys}">
	${hobby}
</c:forEach>
</p>
<p>성별 : ${memberVO.gender}</p>
<p>국적 : ${memberVO.nationality}</p>
<p>보유 자동차 : 
<c:forEach var="car" items="${memberVO.cars}">
	${car}
</c:forEach>
</p>