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
				<span class="warning">${event.hostId }</span> 의 체육행사 <small><i
					class="fa-solid fa-pen"></i> ${event.registerAt }</small>
			</p>
			<h1>
				<span class="warning">${event.tag }</span> ${event.title }
			</h1>
			<p class="fs-3">${event.description }</p>
			<p class="fs-3">
				<span><i class="fa-regular fa-calendar"></i> ${event.openAt }</span>
				<span><i class="fa-solid fa-font-awesome"></i> ${gym.name }</span> <span><i
					class="fa-solid fa-user-large"></i> ${event.cur } /
					${event.capacity }</span>
			</p>
		</div>
		<div class="my-2 text-right">
			<c:if test="${already == false }">
				<a
					href="${pageContext.servletContext.contextPath }/events/join/${event.id }"><button
						class="p-2 fs-4 border-rounded">참가신청</button></a>
			</c:if>
		</div>
		<div style="text-align: right">
		<a href="${pageContext.servletContext.contextPath }/events/${event.id}"><button>본문보기</button></a>
		</div>
		<div class="my-4">
			댓글쓰기 ${commentCount }
			<form action="${pageContext.servletContext.contextPath }/events/comments">
			<input type="hidden" name="eventId" value="${event.id }"/>
				<textarea class="w-100  p-2 border-rounded fs-5" name="comments"
					style="height: 80px; resize: none"></textarea>
					<div class="my-1">
					<button class="btn" type="submit" style="width: 181px ; height:55px ;margin-top: 40px"><span>전송</span></button>
			</div>
			</form>
				<c:forEach items="${printComment }" var="one">
				<p>
				작성자 : ${one.userId }
				내용 : ${one.body }
				</p>
				</c:forEach>
		</div>
	</div>
</body>
</html>