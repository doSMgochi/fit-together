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
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/css/style.css?<%=System.currentTimeMillis() %>" />
</head>
<body>
	<%@ include file="../nav.jsp"%>
	<div class="container px-1">
		<div class="border-rounded p-2 text-center">
			<p class="fs-3">
				<span class="warning">${board.writerId }</span> 의 글 <small><i
					class="fa-solid fa-pen"></i> ${board.writedAt }</small>
					조회수 ${board.readCnt + 1}
			</p>
			<h1>
				<span class="warning">${board.category }</span> ${board.title }
			</h1>
			<p class="fs-3">${board.body }</p>
			<p class="fs-3">
			</p>
		</div>
		<div class="my-2 text-right">
			<c:if test="${already == false }">
				<a
					href="}"><button
						class="p-2 fs-4 border-rounded">좋아요</button></a>
			</c:if>
		</div>
		<div style="text-align: center">
			<a
				href="${pageContext.servletContext.contextPath }/events/${board.id}"><button
					class="none-deco" style="cursor: pointer; color: gray">
					<i class="fa-regular fa-file-lines" style="font-size: 1.75em"></i>
				</button></a> | <a
				href="${pageContext.servletContext.contextPath }/events/${board.id}?tab=comments"><button
					class="none-deco" style="cursor: pointer; color: gray">
					<i class="fa-regular fa-comment" style="font-size: 1.75em"></i>
				</button></a>
		</div>
	</div>
</body>
</html>