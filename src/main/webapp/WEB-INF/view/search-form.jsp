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
</head>
<body>
	<%@ include file="./nav.jsp"%>
	<div class="conatiner text-center" style="margin-top : 100px">
		<h1 style="font-weight: bold">핏투게더</h1>
		<div>
			<form action="${pageContext.servletContext.contextPath }/search">
				<input name="q" type="text" class="p-3 fs-3 w-50 border-rounded"  />
			</form>
		</div>
		<div class="my-3">
		</div>
	</div>
</body>
</html>