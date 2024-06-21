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
	<%@ include file="../nav.jsp"%>
	<div class="container px-1 text-center">
		<div class="my-4">
			<a class="hover-em a"
				href="${pageContext.servletContext.contextPath }/events">체육시설</a>
			&gt; <span class="warning">체육시설 목록</span>
		</div>
		<div>
			<i class="fa-solid fa-splotch" style="color: gray; font-size: 0.5em"></i>
			<i class="fa-solid fa-splotch"
				style="color: rgb(151, 122, 102); font-size: 0.5em"></i> <i
				class="fa-solid fa-splotch" style="color: gray; font-size: 0.5em"></i>
		</div>
		<div>
			<h2 class="my-6">목록: 체육시설</h2>
		</div>
		<table style="width: 800px; margin: auto; border-collapse: collapse;">
			<tr>
				<th style="background-color: lightgray">시설명</th>
				<th style="background-color: lightgray">종류</th>
				<th style="background-color: lightgray">지역</th>
				<th style="background-color: lightgray">업체</th>
				<th style="background-color: lightgray">관리자</th>
			</tr>
			<c:forEach items="${gyms }" var="one">
				<tr style="border-bottom: 1px solid #ddd; height: 30px;">
					<td>${one.name }</td>
					<td>${one.type }</td>
					<td>${one.region }</td>
					<td>${one.agency }</td>
					<td>${one.manager }</td>
				</tr>
			</c:forEach>
		</table>
		<div style="text-align: center; margin-top: 30px">
			<c:forEach begin="1" end="${totalPages }" var="i">
				<c:choose>
					<c:when test="${i == currentPage }">
						<b style="color: red">${i }</b>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.servletContext.contextPath }/gyms?p=${i}">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>

	</div>
</body>
</html>
