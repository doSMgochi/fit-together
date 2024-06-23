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
		<div class="border-rounded p-2 text-center container">
			<div>
				<span class="warning">[${board.category }]</span>
			</div>
			<div class="fs-1">${board.title }</div>
			<div
				style="display: flex; align-items: center; justify-content: space-between;">
				<div style="margin-left: 1%">
					<i class="fa-solid fa-user-large"></i> <span class="warning">${board.writerId }</span>
				</div>
				<div style="margin-right: 1%">
					<i class="fa-solid fa-pen"></i> ${board.writedAt } │ <i
						class="fa-solid fa-eye"></i> 조회수 ${board.readCnt + 1}
				</div>
			</div>
		</div>
		<div class="status-bar my-3">
			<div class="status-bar__column ">
				<c:if test="${already == false }">
					<a href=""><button
							class="p-2 fs-4 border-rounded badge-warning"
							style="border: 2px solid lightgray">
							<i class="fa-solid fa-heart"></i>
						</button></a>
				</c:if>
			</div>
			<div class="status-bar__column status-bar__column:nth-child(2)">
			</div>
			<div class="status-bar__column status-bar__column:last-child">
				<c:if test="${authorCheck == true }">
					<a
						href="${pageContext.servletContext.contextPath }/boards/edit?id=${board.id}"><button
							class="p-2 fs-4 border-rounded badge-warning"
							style="border: 2px solid lightgray">수정</button></a>
					<a href="${pageContext.servletContext.contextPath }/boards/delete-handle?id=${board.id}"><button
							class="p-2 fs-4 border-rounded badge-warning"
							style="border: 2px solid lightgray">삭제</button></a>
				</c:if>

			</div>
		</div>
		<div class="container" style="margin-bottom: 50px">
			<p class="fs-3">${board.body }</p>
		</div>
		<hr class="hr-1" />
		<div class="my-2">
			<h3>댓글쓰기</h3>
			<form
				action="${pageContext.servletContext.contextPath }/boards/comments">
				<input type="hidden" name="boardId" value="${board.id }" />
				<textarea class="w-100  p-2 border-rounded fs-5" name="comments"
					style="height: 70px; resize: none; margin-top: 10px"></textarea>
				<div class="my-4" style="margin-bottom: 50px">
					<button class="btn" type="submit"
						style="width: 181px; height: 55px; margin-top: 20px">
						<span>전송</span>
					</button>
				</div>
			</form>
			<h3>
				댓글목록 <span class="warning">${commentCount }</span>
			</h3>
			<c:forEach items="${printComment }" var="one">
				<div class="border-rounded p-1 item my-1" style="padding: 15px">
					<i class="fa-regular fa-comment"></i> <span class="comtit fs-3"
						style="font-weight: bold"> ${one.userId }</span><br /> <span
						class="fs-4">${one.body }</span>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>