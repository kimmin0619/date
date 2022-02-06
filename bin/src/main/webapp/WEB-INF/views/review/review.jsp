<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>매칭 후기</title>
<script type="text/javascript">
	function ReviewInsertFalse() {
		alert('로그인 후 이용하세요.');
		location.href='loginForm';
	}
</script>
</head>
<body>
<h1>리뷰 게시판</h1>

	<table>
		<tr>
			<td>
				<label for="seq">글 번호</label><input type="text" id = "seq" name = "seq" value="${read.seq}"/>
			</td>
		</tr>
	</table>

<c:if test="${ loginVo == null}">
	<input type="button" value="글쓰기" onclick="ReviewInsertFalse()">
</c:if>
<c:if test="${ loginVo != null}">
	<input type="button" value="글쓰기" onclick="location.href='reviewForm'">
</c:if>
</body>
</html>