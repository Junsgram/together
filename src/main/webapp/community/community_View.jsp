<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
	<h2> 커뮤니티 게시글 세부 페이지입니다.</h2>
	<div>
		<p> 제목 : ${dto.commuName}</p>
		<p> 간략설명 : ${dto.scontent}</p>
		<p> 세부설명 :${dto.lcontent} </p>
		<p> 사진 : <img src = "/together/community/img/${dto.ofile}" /></p>
		<form method = "post" action = "<%= request.getContextPath()%>/commu?cmd=edit&num=${dto.num}">
			<button> 수정하기 </button>
		</form>
		<form>
			<button> 삭제하기 </button>
		</form>
	</div>
	
<%@ include file="../include/footer.jsp" %>