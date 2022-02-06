<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>index</title>
</head>
<body>
	<c:if test="${ loginVo == null }">
		<input type="button" value="회원가입" onclick="location.href='signup'">
		<input type="button" value="로그인" onclick="location.href='loginForm'">
		<input type="button" value="매칭후기" onclick="location.href='review'">
		
	</c:if>
	<c:if test="${ loginVo != null }">
		<p>${loginVo.nickname}님</p>
		<i class="glyphicon glyphicon-user" style="font-size:48px;" onclick="location.href='mypage'"></i>
		<input type="button" value="로그아웃" onclick="location.href='logout'">
		<input type="button" value="매칭후기" onclick="location.href='review'">
	</c:if>
</body>
</html>