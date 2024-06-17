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
	href="${pageContext.servletContext.contextPath }/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/css/neon.css" />
</head>
<body>
	<hr class="hr-13" />
	<div style="padding: 8px;">
		<div class="status-bar">
			<%--banner  --%>
			<div class="status-bar__column "></div>
			<div class="status-bar__column status-bar__column:nth-child(2)">
				<a class="no-deco-link title-font"
					style="font-size: 60px; font-weight: bold;" href="">핏 투 게 더</a>
			</div>
			<c:choose>
				<c:when test="${sessionScope.authUser == null}">
					<div class="status-bar__column status-bar__column:last-child">
						<a href="${pageContext.servletContext.contextPath }/signup" class="no-deco-link fs-small">💡회원가입</a> | <a href="${pageContext.servletContext.contextPath }/login"
							class="no-deco-link fs-small">👥로그인</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="status-bar__column status-bar__column:last-child">
						<a href="${pageContext.servletContext.contextPath }/logout" class="no-deco-link fs-small">👥${sessionScope.authUser.name }님
							로그아웃</a> | <a href="" class="no-deco-link fs-small">📝정보수정</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<%-- shortcut  링크 --%>
		<hr class="hr-1" style="margin-top: 40px">
		<div
			style="display: flex; align-items: center; justify-content: space-around; margin-top: 15px; margin-bottom: 15px">
			<div>
				<a href="" class="shortcut">TYPE#1</a>
			</div>
			<div>
				<a href="" class="shortcut">TYPE#2</a>
			</div>
			<div>
				<a href="" class="shortcut">TYPE#3</a>
			</div>
		</div>


		<hr class="hr-1" />
		<div class="container"
			style="display: flex; justify-content: space-around; margin-top: 15px; margin-bottom: 15px">
			<div class="enlargement neon">
			        <span></span>
        <span></span>
        <span></span>
        <span></span>
				<b class="underscore">공지사항</b>
			</div>
			<div class="enlargement neon">
			        <span></span>
        <span></span>
        <span></span>
        <span></span>
				<b class="underscore">대회일정</b>
			</div>
			<div class="enlargement neon">
			        <span></span>
        <span></span>
        <span></span>
        <span></span>
				<b class="underscore">질문/답변</b>
			</div>
			<div class="enlargement neon">
			        <span></span>
        <span></span>
        <span></span>
        <span></span>
				<b class="underscore">자유게시판</b>
			</div>

		</div>
	</div>
</body>
</html>
