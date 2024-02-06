<%@page import="domain.board.House.DTO.WriteReqDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp"%>
	<h2> 게시글 세부 페이지입니다.</h2>
	<div>
		<p> 제목 : ${views.houseName}</p>
		<p> 간략설명 : ${views.scontent}</p>
		<p> 세부설명 :${views.lcontent} </p>
		<p> 사진 : <img src = "/together/house/img/${views.ofile}" /></p>
		<form method = "post" action = "<%= request.getContextPath()%>/house?cmd=edit&num=${views.num}">
			<button> 수정하기 </button>
		</form>
		<form>
			<button> 삭제하기 </button>
		</form>
	</div>
	
<%@ include file = "/include/footer.jsp"%>
