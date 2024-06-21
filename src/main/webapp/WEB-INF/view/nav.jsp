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
		<title>${title }:: 핏투게더</title>
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
	<hr class="hr-13" />
	<div style="padding: 8px;">
		<div class="status-bar">
			<%--banner  --%>
			<div class="status-bar__column "></div>
			<div class="status-bar__column status-bar__column:nth-child(2)">
				<a class="no-deco-link title-font"
					style="font-size: 60px; font-weight: bold;" href="${pageContext.servletContext.contextPath }/index">핏 투 게 더</a>
			</div>
			<c:choose>
				<c:when test="${sessionScope.authUser == null}">
					<div class="status-bar__column status-bar__column:last-child">
						<a href="${pageContext.servletContext.contextPath }/signup"
							class="no-deco-link fs-small"><i class="fa-solid fa-seedling"
							style="color: gray"></i> 회원가입</a> | <a
							href="${pageContext.servletContext.contextPath }/login"
							class="no-deco-link fs-small"><i
							class="fa-solid fa-user-large" style="color: gray"></i> 로그인</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="status-bar__column status-bar__column:last-child">
						<a href="${pageContext.servletContext.contextPath }/logout"
							class="no-deco-link fs-small"><i
							class="fa-solid fa-user-large" style="color: gray"></i>
							${sessionScope.authUser.name }님 로그아웃</a> | <a href=""
							class="no-deco-link fs-small"><i class="fa-solid fa-file-pen"
							style="color: gray"></i> 정보수정</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<%-- shortcut  링크 --%>
		<hr class="hr-1" style="margin-top: 40px">
		<div
			style="display: flex; align-items: center; justify-content: space-around; margin-top: 15px; margin-bottom: 15px">
			<div>
				<a href="${pageContext.servletContext.contextPath }/events/design"
					class="a">새 행사 등록</a>
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath }/events" class="a">행사 목록</a>
			</div>
			<div>
			<!-- 게시판 들어갈 자리 -->
				<a href="${pageContext.servletContext.contextPath }/gyms" class="a">체육시설</a>
			</div>
			<div>
			<!-- 게시판 들어갈 자리 -->
				<a href="${pageContext.servletContext.contextPath }/boards" class="a">게시판</a>
			</div>
		</div>


		<hr class="hr-1" />
		</div>
		<!-- 아마 gap? 을 이용해서 처리할 수 있을 것 같은데 반응형으로 위치가 달라질 수 있게 변경 -->
		<div class="wrap-sm text-center my-5 p-5" style="position: absolute; bottom: -55% ; width: 100% ;">
		<a style="cursor: default;"
			href="${pageContext.servletContext.contextPath }/index"> <img
			src="${pageContext.servletContext.contextPath }/image/footer.jpg"
			width="500" />
		</a>
	<hr class="hr-1" style="margin-top: 10px">
	</div>
</body>
</html>