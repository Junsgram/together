<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<style>

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
.house_photo {
	width: 50%;
}
.house_photo img {
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
	width: 95px;
	display:inline-block;
}
</style>
<script>
	function commentSave(userId, commuNum){
		let data = {
				userId : userId,
				commuNum : commuNum,
				content:$("#content").val()
		};
		$.ajax({
			type : "post",
			url : "/together/commuComment?cmd=save",
			data : JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			dataType : "json"
		}).done(function(result){
			if(result.statusCode>0){
				console.log(result);
				addComment(result.data);
			}else{
				alert("실패")
			}
			//textarea에 있는 값 초기화
			$("#content").val("");
		})
	}
	
	//댓글 추가하기
	function addComment(data){
		let item="<li id='reply-"+data.id+"' class='media'>"		
		+"<div class='media-body'>"
		+"<strong class='text-primary'>"+data.userId+"</strong>"
		+"<p>"+data.content+"</p></div>"
		+"<div class='m-2'>"
		+"<i onclick='deleteComment("+data.id+")' class='material-icon'>delete</i>"
		+"</div></li>";
		$("#reply__list").prepend(item);
	}
	
	//댓글 삭제하기
	function deleteComment(id){
		$.ajax({
			type:"post",
			url:"/together/commuComment?cmd=delete&id="+id,
			dataType:"json"
		}).done(function(result){
			console.log(result);
			if (result.statusCode==1){
				$("#reply-"+id).remove();
			}else{
				alert("댓글 삭제 실패");
			}
		})
	}

</script>
<div class="main_text1">
	<h1>${dto.title}</h1>
	<div class="contents1">${dto.scontent}</div>
	<div class="service">
		<div class="house_photo">
			<img src="/together/community/img/${dto.ofile}" />
		</div>
		<div class="contents2">
			<p>세부설명: :${dto.lcontent}</p>
		</div>
	</div>
</div>
<div class ="styleForm">
	<form method="post"
		action="<%= request.getContextPath()%>/commu?cmd=edit&num=${dto.num}">
		<button class="btn btn-primary pull-right">수정하기</button>
	</form>
	<form method="post"
		action="<%= request.getContextPath()%>/commu?cmd=delete&num=${dto.num}">
		<button class="btn btn-primary pull-right">삭제하기</button>
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
					<!--  form으로 처리하지 않고 아작스로 처리 예정으로 form태그가 없다
							<input type ="hidden" name = "bookId" value = "${book.bookId}"/>
							<!-- id 값은 세션 스코프의 principal 속성명에 저장되어있어 해당값을 호출하여 값을 활용한다 
							<input type ="hidden" name = "userId" value = "${sessionScope.principal.id}"/> -->
					<textarea name="content" id="content" class="form-control"
						placeholder="write a comment..." rows="2"></textarea>
					<br />
					<!--  타입을 버튼타입 및 onclick이벤트로 commentSave()메소드를 활용하여  -->
					<!--  commentSave메소드 매개변수로 bookid와 userid를 el구문으로 작성 -->
					<button type="button"
						onclick="commentSave('${sessionScope.principal.id}',${dto.num})"
						class="btn btn-primary pull-right">댓글쓰기</button>
				</div>

				<div class="clearfix"></div>
				<hr />
				<!-- 댓글 리스트 시작-->
				<ul id="reply__list" class="media-list">
					<c:forEach var="c" items="${comments}">
						<!-- 댓글 아이템 -->
						<li id="reply-${c.id}" class="media">
							<div class="media-body">
								<strong class="text-primary">${c.userId}</strong>
								<p>${c.content}</p>
								<p>${c.createDate}</p>
							</div> <c:if test="${sessionScope.principal.id == c.userId}">
								<div class="m-2">
									<i onclick="deleteComment(${c.id})" class="material-icons">delete</i>
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
<%@ include file="../include/footer.jsp"%>