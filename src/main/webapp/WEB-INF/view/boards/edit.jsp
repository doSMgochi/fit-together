<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
	<c:when test="${empty title }">
		<title>핏투게더</title>
	</c:when>
	<c:otherwise>
		<title>${title }::핏투게더</title>
	</c:otherwise>
</c:choose>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/css/style.css?<%=System.currentTimeMillis() %>" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>
	<%@ include file="../nav.jsp"%>
	<div class="wrap-l px-1">
		<div
			style="border: 2px solid lightgrey; border-radius: 20px; margin: 10px; padding: 20px; margin-top: 50px">
			<form
				action="${pageContext.servletContext.contextPath }/boards/edit-handle"
				method="post">
				<input type="hidden" name="id" value="${board.id }"/>
				<table style="border-collapse: collapse; width: 100%">
					<tr>
						<td
							style="border: 1px solid #ccc; padding: 12px; text-align: center; background-color: #eee; width: 120px">작성자</td>
						<td style="border: 1px solid #ccc; padding: 12px;">${board.writerId }</td>
					</tr>
					<tr>
						<td
							style="border: 1px solid #ccc; padding: 12px; text-align: center; background-color: #eee ; ">카테고리</td>
						<td style="border: 1px solid #ccc; padding: 12px;"><select name = category style="padding: 6px 10px; width: 580px; border: 1px solid #ccc">
								<option  value="공지사항">공지사항</option>
								<option  value="대회일정">대회일정</option>
								<option  value="질문/답변">질문/답변</option>
								<option  value="자유게시판">자유게시판</option>
						</select></td>
					</tr>
					<tr>
						<td
							style="border: 1px solid #ccc; padding: 12px; text-align: center; background-color: #eee">제목</td>
						<td style="border: 1px solid #ccc; padding: 12px;"><input
							name="title" type="text" value="${board.title }"
							style="padding: 6px 10px; width: 580px; border: 1px solid #ccc" /></td>
					</tr>
					<tr>
						<td style="border: 1px solid #ccc; padding: 12px;" colspan="2">
							<textarea name="body"
								style="height: 300px; resize: none; width: 100%; box-sizing: border-box; padding: 6px 10px; border: 1px solid #ccc">${board.body }</textarea>

						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 20px;">
					<button class="badge-warning"
					style="border: 2px solid lightgray; margin-top: 10px" type="submit">저장</button>
					<button class="badge-warning"
					style="border: 2px solid lightgray; margin-top: 10px" type="reset">취소</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>