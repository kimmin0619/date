<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글쓰기</title>
</head>
<body>
<h1>글쓰기</h1>
	
	<form action="/reviewInsert" method="post">
	<p><input type="text" id="title" name="title" placeholder="제목을 입력하세요." style="width:300px;height:20px;font-size:12px;"></p> 
	<p><input type="text" id="content" name="content" placeholder="내용을 입력하세요." style="width:300px;height:600px;font-size:12px;"></p>
	<p><button type="submit">작성</button>
	</form>
</body>
</html>