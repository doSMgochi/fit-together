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
	<%@ include file="../nav.jsp"%>
	<div class="wrap-l px-1 text-center">
		<div class="my-4">
			<a class="hover-em a"
				href="${pageContext.servletContext.contextPath }/events/design">새
				행사 등록</a> &gt; <span class="warning">체육시설 선택</span>
		</div>
		<div>
			<i class="fa-solid fa-splotch" style="color: gray; font-size: 0.5em"></i>
			<i class="fa-solid fa-splotch"
				style="color: rgb(151, 122, 102); font-size: 0.5em"></i> <i
				class="fa-solid fa-splotch" style="color: gray; font-size: 0.5em"></i>
		</div>
		<h2 class="my-6">목록: 등록된 체육행사</h2>

		<div style="display: flex; flex-wrap: wrap;">
			<c:forEach items="${events }" var="one">
				<div style="width: 50%; padding: 4px;">
					<div class="border-rounded p-3 item" style="height: 150px;">
						<div style="display: flex; justify-content: space-between;">
							<span class="badge-warning"># ${one.event.tag }</span> <span
								class="badge-dark"> ${one.event.cur } /
								${one.event.capacity }</span>
						</div>
						<div>
							<c:url value="/events/${one.event.id }" var="link" />
							<h2 class="my-2">
								<a href="${link }">${one.event.title }</a>
							</h2>
						</div>
						<hr />
						<div style="display: flex; justify-content: flex-end; gap: 2px;">
							<c:if test="${one.joined }">
								<span class="badge-info">참여중</span>
							</c:if>
							<span class="badge-dark">${one.gym.name }</span>
							<c:choose>
								<c:when test="${one.dday > 0 }">
									<span class="badge-dark">D-${one.dday }</span>
								</c:when>
								<c:when test="${one.dday == 0 }">
									<span class="badge-dark">D-Day</span>
								</c:when>
								<c:otherwise>
									<span class="badge-dark">마감</span>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>


	</div>
</body>
</html>