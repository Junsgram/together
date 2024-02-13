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
		<p> 제목 : ${diaryViews.title}</p>
		<p> 간략설명 : ${diaryViews.scontent}</p>
		<p> 세부설명 :${diaryViews.lcontent} </p>
		<p> 조회수 : ${diaryViews.views}</p>
		<p> 사진 : <img src = "/together/diary/img/${diaryViews.ofile}" style ="width:300px; height:100px;" /></p>
		<c:choose>
			<c:when test = "${sessionScope.principal.id == diaryViews.id}">
			<form method = "post" action = "<%= request.getContextPath()%>/diary?cmd=edit&num=${diaryViews.num}">
				<button> 수정하기 </button>
			</form>
			<form method = "post" action = "<%= request.getContextPath() %>/diary?cmd=delete&num=${diaryViews.num}">
				<button> 삭제하기 </button>
			</form>
			</c:when>
			<c:otherwise>
					<button id="liketo"></button>
			</c:otherwise>
		</c:choose>
		<form method="post" action ="<%= request.getContextPath()%>/diary?cmd=list&page=0"> 
			<button>목록페이지</button>
		</form>
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
