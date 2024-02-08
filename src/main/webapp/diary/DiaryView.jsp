<%@page import="domain.board.House.DTO.WriteReqDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp"%>
<style>
	#liketo{
		background-image :url("house/img/likes/emptyheart.png");
		background-size: cover;
		width : 50px;
		height : 50px;
	}
	#liketo.to{
		border : 1px solid black;
		background-image :url("house/img/likes/fullheart.png");
		width: 50px;
		height: 50px;
		
	} 
</style>

	<h2> 게시글 세부 페이지입니다.</h2>
	<div>
		<p> 제목 : ${views.houseName}</p>
		<p> 간략설명 : ${views.scontent}</p>
		<p> 세부설명 :${views.lcontent} </p>
		<p> 사진 : <img src = "/together/house/img/${views.ofile}" /></p>
		<c:choose>
			<c:when test = "${sessionScope.principal.id == views.id}">
			<form method = "post" action = "<%= request.getContextPath()%>/house?cmd=edit&num=${views.num}">
				<button> 수정하기 </button>
			</form>
			<form method = "post" action = "<%= request.getContextPath() %>/house?cmd=delete&num=${views.num}">
				<button> 삭제하기 </button>
			</form>
			</c:when>
			<c:otherwise>
					<button id="liketo"></button>
			</c:otherwise>
		</c:choose>
	</div>
	<script>
	//좋아요 버튼 로직	
	let liketo = $("#liketo");
	liketo.click(function(){
		if(liketo.hasClass()) {
			liketo.toggleClass("to");
		}
		else{
			liketo.toggleClass("to");
		}
	})
	
	
</script>
<%@ include file = "/include/footer.jsp"%>
