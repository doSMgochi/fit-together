<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
	<c:when test="${empty title}">
		<title>핏투게더</title>
	</c:when>
	<c:otherwise>
		<title>${title }::핏투게더</title>
	</c:otherwise>
</c:choose>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/css/neon.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined"
	rel="stylesheet">
</head>
<body>
	<%@ include file="./nav.jsp"%>
	<!-- 작업이 끝난 뒤, 메인 레이아웃 구성 -->
	<div class="container"
		style="display: flex; justify-content: space-around; margin-top: 15px; margin-bottom: 15px">

		<div class="enlargement neon">
			<span></span> <span></span> <span></span> <span></span> <b
				class="underscore"><a
				href="${pageContext.servletContext.contextPath }/boards?category=announcement"><b
					class="a" style="font-weight: bold">공지사항</b></a></b>
			<ul style="list-style: none; padding-left: 0px">
				<c:forEach items="${announcement }" var="one">
					<li class="fs-7"><a
						href="${pageContext.servletContext.contextPath }/boards/${one.id}">${one.title }</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="enlargement neon">
			<span></span> <span></span> <span></span> <span></span> <b
				class="underscore"><a
				href="${pageContext.servletContext.contextPath }/boards?category=schedule"><b
					class="a" style="font-weight: bold">대회일정</b></a></b>
			<ul style="list-style: none; padding-left: 0px">
				<c:forEach items="${schedule }" var="one">
					<li class="fs-7"><a
						href="${pageContext.servletContext.contextPath }/boards/${one.id}">${one.title }</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="enlargement neon">
			<span></span> <span></span> <span></span> <span></span> <b
				class="underscore"><a
				href="${pageContext.servletContext.contextPath }/boards?category=qna"><b
					class="a" style="font-weight: bold">질문/답변</b></a></b>
			<ul style="list-style: none; padding-left: 0px">
				<c:forEach items="${qna }" var="one">
					<li class="fs-7"><a
						href="${pageContext.servletContext.contextPath }/boards/${one.id}">${one.title }</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="enlargement neon">
			<span></span> <span></span> <span></span> <span></span> <b
				class="underscore"><a
				href="${pageContext.servletContext.contextPath }/boards?category=freeboard"><b
					class="a" style="font-weight: bold">자유게시판</b></a></b>
			<ul style="list-style: none; padding-left: 0px">
				<c:forEach items="${freeboard }" var="one">
					<li class="fs-7"><a
						href="${pageContext.servletContext.contextPath }/boards/${one.id}">${one.title }</a></li>
				</c:forEach>
			</ul>
		</div>

	</div>

</body>
</html>
