<%@page import="domain.board.House.DTO.WriteReqDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<style>
#liketo {
	background-image: url("house/img/likes/emptyheart.png");
	background-size: cover;
	width: 50px;
	height: 50px;
}

#liketo.to {
	border: 1px solid black;
	background-image: url("house/img/likes/fullheart.png");
	width: 50px;
	height: 50px;
}
</style>
<script>
// 댓글 ajax 구현
// 댓글 버튼 클릭 시 함수 실행
function commentSave(userId, diaryNum) {
	let data = {
			userId : userId,
			diaryNum : diaryNum,
			content : $("#content").val()
	};
	
	$.ajax({
		type : "POST",
		url : "/together/diarycomment?cmd=save",
		data:JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType : "json"
	}).done(function(result) {
		if(result.StatusCode > 0) {
			console.log("여기");
			addComment(result.data);
		}else {
			alert("댓글 달기 실패");
		}
		// 댓글 창 공백으로 만들기 
	})
}
// 댓글 추가 함수
function addComment(data) {
	let item = "<li id= 'reply-"+data.comnum+"' class='media'>"
	 + "<div class='media-body'> "
	 + "<strong class='text-primary'>"+data.userId+"</strong> "
	 + "<p> "+data.content+" </p> "
	 + "<p>" +data.createDate +" </p> "
	 + "</div> <div class='m-2'> "
	 + "<i onclick='deleteComment("+data.comnum+")' class='material-icons'>delete</i>"
	 + "</div> </li>" ;
	// prepend메소드를 활용하여 댓글 작성 시 앞으로 이동
	$("#reply__list").prepend(item);
}

// 댓글 삭제하기
function deleteComment(num) {
	// 요청
	$.ajax({
		type: "POST",
		url : "/together/diarycomment?cmd=delete&num="+num,
		dataType: "json"
	}).done(function(result) {
		if(result.StatusCode==1) {
			$("#reply-"+num).remove();
		}else {
			alert("댓글 삭제 실패");
		}
	})
}
</script>

<h2>게시글 세부 페이지입니다.</h2>
<div>
	<p>제목 : ${diaryViews.title}</p>
	<p>간략설명 : ${diaryViews.scontent}</p>
	<p>세부설명 :${diaryViews.lcontent}</p>
	<p>조회수 : ${diaryViews.views}</p>
	<p>
		사진 : <img src="/together/diary/img/${diaryViews.ofile}"
			style="width: 300px; height: 100px;" />
	</p>
	<c:choose>
		<c:when test="${sessionScope.principal.id == diaryViews.id}">
			<form method="post"
				action="<%= request.getContextPath()%>/diary?cmd=edit&num=${diaryViews.num}">
				<button>수정하기</button>
			</form>
			<form method="post"
				action="<%= request.getContextPath() %>/diary?cmd=delete&num=${diaryViews.num}">
				<button>삭제하기</button>
			</form>
		</c:when>
		<c:otherwise>
			<button id="liketo"></button>
		</c:otherwise>
	</c:choose>
	<form method="post"
		action="<%=request.getContextPath()%>/diary?cmd=list&page=0">
		<button>목록페이지</button>
	</form>
</div>
	<!-- 댓글 박스 -->
	<div class="row bootstrap snippets">
		<div class="col-md-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2">
						<b>Comment</b>
					</div>
					<div class="panel-body">
						<input type ="hidden" name = "userId" value = "${sessionScope.principal.id}"/>
						<textarea name="content" id="content" class="form-control"
							placeholder="write a comment..." rows="2"></textarea>
						<br />
						<button type="button"
							onclick="commentSave('${sessionScope.principal.id}',${diaryViews.num})"
							class="btn btn-primary pull-right">댓글쓰기</button>
					</div>
					<div class="clearfix"></div>
					<hr />
					<!-- 댓글 리스트 시작-->
					<ul id="reply__list" class="media-list">

						<c:forEach var="c" items="${comments}">
							<!-- 댓글 아이템 -->
							<li id="reply-${c.comnum}" class="media">
								<div class="media-body">
									<strong class="text-primary">${c.userId}</strong>
									<p>${c.content}</p>
									<p>${c.createDate}</p>
								</div> <c:if test="${sessionScope.principal.id == c.userId}">
									<div class="m-2">

										<i onclick="deleteComment(${c.comnum})" class="material-icons">delete</i>

									</div>
								</c:if>
							</li>
						</c:forEach>
					</ul>
					<!-- 댓글 리스트 끝-->
				</div>
			</div>
		</div>
	</div>
	<!-- 댓글 박스 끝 -->
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
	<%@ include file="/include/footer.jsp"%>