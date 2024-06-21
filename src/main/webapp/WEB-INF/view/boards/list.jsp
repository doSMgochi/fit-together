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
				href="${pageContext.servletContext.contextPath }/boards">게시판</a>
			&gt; <span class="warning">목록</span>
		</div>
		<div>
			<i class="fa-solid fa-splotch" style="color: gray; font-size: 0.5em"></i>
			<i class="fa-solid fa-splotch"
				style="color: rgb(151, 122, 102); font-size: 0.5em"></i> <i
				class="fa-solid fa-splotch" style="color: gray; font-size: 0.5em"></i>
		</div>
		<div>
			<h2 class="my-6">목록: 게시판</h2>
		</div>
		<div style="margin-bottom: 50px">
		<form action="${pageContext.servletContext.contextPath }/boards">
			<button class="badge-warning" style="border: 2px solid lightgray" type="submit" name="category" value="all">전체글보기</button>
			<button class="badge-warning" style="border: 2px solid lightgray" type="submit" name="category" value="announcement">공지사항</button>
			<button class="badge-warning" style="border: 2px solid lightgray" type="submit" name="category" value="schedule">대회일정</button>
			<button class="badge-warning" style="border: 2px solid lightgray" type="submit" name="category" value="qna">질문/답변</button>
			<button class="badge-warning" style="border: 2px solid lightgray" type="submit" name="category" value="freeboard">자유게시판</button>
		</form>
		</div>
		<div style="text-align: right ; margin-right: 180px ; margin-bottom: 10px">
		<a href="${pageContext.servletContext.contextPath }/boards/write"><button class="badge-warning"
					style="border: 2px solid lightgray; margin-top: 10px">글쓰기</button></a>
					</div>
		<table style="width: 800px; margin: auto; border-collapse: collapse;">
			<tr>
				<th style="background-color: lightgray ; width: 50px">번호</th>
				<th style="background-color: lightgray ; width: 120px">카테고리</th>
				<th style="background-color: lightgray; width: 70px">작성자</th>
				<th style="background-color: lightgray; width: 420px">제목</th>
				<th style="background-color: lightgray; width: 180px">작성일</th>
				<th style="background-color: lightgray; width: 80px">조회수</th>
			</tr>
			<c:forEach items="${boards }" var="one">
				<tr style="border-bottom: 1px solid #ddd; height: 30px;">
					<td>${one.id }</td>
					<td>${one.category }</td>
					<td>${one.writerId }</td>
					<td><a
						href="${pageContext.servletContext.contextPath }/boards/${one.id}">${one.title }</a></td>
					<td>${one.writedAt }</td>
					<td>${one.readCnt }</td>
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
						<a href="${pageContext.servletContext.contextPath }/boards?p=${i}">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<div style="margin-top: 50px">
			<form action="${pageContext.servletContext.contextPath }/search">
				<input name="q" type="text" class="p-2 fs-5 border-rounded"
					style="width: 240px; height: 30px" /><br />
				<button class="badge-warning"
					style="border: 2px solid lightgray; margin-top: 10px">검색</button>
				
			</form>
				
		</div>
	</div>
</body>
</html>
