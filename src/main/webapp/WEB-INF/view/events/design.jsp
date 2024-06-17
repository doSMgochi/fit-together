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
<%@ include file="../nav.jsp" %>
	<div class="container px-1 text-center">
		<div class="my-4">
			<a class="hover-em a" href="${pageContext.servletContext.contextPath }/events/design">새 행사 등록</a> &gt; <span class="warning">체육시설 선택</span>
		</div>
		<div>
		<i class="fa-solid fa-splotch" style="color: gray ; font-size: 0.5em"></i>
		<i class="fa-solid fa-splotch" style="color: rgb(151, 122, 102); font-size: 0.5em"></i>
		<i class="fa-solid fa-splotch" style="color: gray; font-size: 0.5em"></i>
		</div>
		<h2 class="my-6">시작하기: 체육시설 선택</h2>
		<p>
			원하는 <span class="warning">체육시설</span>을 선택하고 자세한 정보를 입력하세요.
		</p>
		<form action="${pageContext.servletContext.contextPath }/events/new">
			<select name="type" class="w-50 text-center p-2 border-rounded fs-3">
				<c:forEach var="one" items="${types }">
					<option>${one }</option>
				</c:forEach>			
			</select>
			<br/>
			<button class="btn" type="submit" style="width: 181px ; height:55px ;margin-top: 40px"><span><i class="fa-solid fa-basketball fa-pulse"></i> 선택</span></button>
		</form>
		
	</div>
</body>
</html>