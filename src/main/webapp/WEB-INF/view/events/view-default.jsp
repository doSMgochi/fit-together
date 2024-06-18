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
		<a href="${pageContext.servletContext.contextPath }/events/${event.id}?tab=comments"><button>댓글보기</button></a>
		</div>
		<h3 class="my-2">참가자들(${event.cur })</h3>
		<div>
			<ul style="list-style: none">
				<li><c:forEach items="${participants }" var="one">
						<c:choose>
							<c:when test="${one.userId == event.hostId }">
								<div>
									<i class="fa-solid fa-user-tie"></i> <span class="warning"
										style="font-weight: bold">${one.userId } (주최자)</span> -
									${one.joinAt } 에 참가신청
								</div>
							</c:when>
							<c:otherwise>
								<div>
									<i class="fa-solid fa-user-large"></i> ${one.userId } -
									${one.joinAt } 에 참가신청
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach></li>
			</ul>
		</div>
	</div>
</body>
</html>