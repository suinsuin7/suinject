<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
registerForm.jsp -> ModelController.java -> result.jsp
{userId=suin,password=suin}		전달안됨 -> modelattribute 전달 ㅇ
 -->
<h3>result</h3>
<p>userId : ${userId}</p>
<p>password : ${password}</p>
<p>userId : ${memberVO.userId}</p>
<p>userId : ${memberVO.password}</p>