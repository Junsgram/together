<%@page import="domain.board.House.DTO.WriteReqDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<style>
#liketo {
	background-image: url("house/img/likes/emptyheart.png");
	border: none;
	background-size: cover;
	width: 30px;
	height: 30px;
	margin-top: 10px;
}

#liketo.to {
	border: none;
	background-image: url("house/img/likes/fullheart.png");
	width: 30px;
	height: 30px;
}
.main_text1{
  width: 100%;
  height:659px;
  /* margin-top:-132px; */
  /* background: #2F7AF4 */
}
.main_text1 > h1{
  padding-top:50px;
  text-align: center;
}
.main_text1 > .contents1 {
  text-align: center;
}
.service {
  width:1280px;
  display: flex;
  margin: 49px auto;
  height: 427px;
}
.diary_photo {
	width: 50%;
}
.diary_photo img {
	width: 100%;
	height: 427px;
}

.service > .contents2 {
	width: 50%;
  padding: 20px;
}
.service > .contents2 > h2{
  margin-bottom: 27px;
}
.styleForm {
	text-align: center;
}
.styleForm > form  {
	width: 100px;
	display:inline-block;
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
		$("#content").val("");
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
<div class="main_text1">
	<h1>${diaryViews.title}</h1>
	<div class="contents1">${diaryViews.scontent}</div>
	<div class="service">
		<div class="diary_photo">
			<img src="/together/diary/img/${diaryViews.ofile}"/>
		</div>
		<div class="contents2">
			<p>세부설명: ${diaryViews.lcontent}</p>
		</div>
	</div>
</div>
<div class="styleForm">
	<c:choose>
		<c:when test="${sessionScope.principal.id == diaryViews.id}">
			<form method="post"
				action="<%= request.getContextPath()%>/diary?cmd=edit&num=${diaryViews.num}">
				<button class="btn btn-primary pull-right">수정하기</button>
			</form>
			<form method="post"
				action="<%= request.getContextPath() %>/diary?cmd=delete&num=${diaryViews.num}">
				<button class="btn btn-primary pull-right">삭제하기</button>
			</form>
		</c:when>
		<c:otherwise>
			<button id="liketo"></button>
		</c:otherwise>
	</c:choose>
	<form method="post"
		action="<%=request.getContextPath()%>/diary?cmd=list&page=0">
		<button class="btn btn-primary pull-right">목록페이지</button>
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