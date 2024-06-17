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
<%@ include file="../nav.jsp" %>
	<div class="container px-1">
		<div class="my-4  text-center">
			<a class="hover-em a" href="${pageContext.servletContext.contextPath }/events/design">새 행사 등록</a> &gt; <span class="warning">행사 세부 정보 입력</span>
		</div>
		<div class="text-center">
		<i class="fa-solid fa-splotch" style="color: gray ; font-size: 0.5em"></i>
		<i class="fa-solid fa-splotch" style="color: rgb(151, 122, 102); font-size: 0.5em"></i>
		<i class="fa-solid fa-splotch" style="color: gray; font-size: 0.5em"></i>
		</div>
		<h2 class="my-6 text-center">계속하기: 행사 세부 정보 입력</h2>
		<p class="fs-4">행사의 세부 정보를 입력해 주세요. 필요한 정보로는 행사의 제목, 날짜, 최대 참가 인원
			수, 그리고 행사에 대한 간략한 설명이 포함됩니다. 이 정보들은 참가자들이 행사를 이해하고, 필요한 준비를 할 수 있도록
			도움을 줄 것입니다. 간단하게 몇 가지 정보만 입력하면, 행사 등록이 완료됩니다!</p>
		<p class="fs-4">※ 행사의 주제나 내용을 잘 나타낼 수 있는 태그를 설정하면 참가자들에게 효과적으로 알릴 수
			있습니다.</p>
		<form
			action="${pageContext.servletContext.contextPath }/events/new-handle" method="post">
			<div class="d-flex my-1" style="gap:10px;">
				<div>
					<small>태그</small><br /> <select class=" p-2 border-rounded fs-3 text-center" name="tag">
						<option selected disabled>태그..</option>
						<option>축구</option>
						<option>수영</option>
						<option>탁구</option>
						<option>농구</option>
						<option>자전거</option>
						<option>육상</option>
						<option>야구</option>
						<option>기타</option>
					</select>
				</div>
				<div >
					<small>행사개최일</small><br /> <input type="date" name="openAt"
						class="  p-2 border-rounded fs-3" />
				</div>
				
				<div>
					<small>최대참가인원</small><br /> <input type="number" name="capacity"
						class="  p-2 border-rounded fs-3 text-right" />
				</div>

				<div class="my-1">
					<small>행사제목</small><br /> <input type="text" name="title"
						class="p-2 border-rounded fs-3" style="width: 200%"/>
				</div>
			</div>
			<div class="my-1">
				<small>개최장소 - ${param.type }</small><br /> <select name="gymId"
					class="w-100 p-2 border-rounded fs-3">
					<option selected disabled>체육관(${param.type })을 선택해주세요.</option>
					<c:forEach var="one" items="${gyms }">
						<option value="${one.id }">${one.name }</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="my-1">
				<small>행사설명</small><br />
				<textarea class="w-100  p-2 border-rounded fs-3" name="description"
					style="height: 150px; resize: none"></textarea>
			</div>

			
			<div class="my-1">
					<button class="btn" type="submit" style="width: 181px ; height:55px ;margin-top: 40px"><span> 행사 등록</span></button>
			</div>
		</form>

	</div>
</body>
</html>