<%@page import="domain.board.House.DTO.WriteReqDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
<script>
	//댓글 들록 시 페이지 이동 없이 댓글 리스트 추가
	function commentSave(userId,housenum) {
		console.log(userId);
		console.log(housenum);
		let data = {
				userId : userId,
				housenum : housenum,
				content : $("#content").val()
		};
		
		$.ajax({
			type : "post",
			url : "/together/housecomment?cmd=save",
			//data의 매개변수는 위에 선언한 data 변수
			data : JSON.stringify(data),
			//JSON 데이터 전송 타입은 application/json
			contentType: "application/json; charset=utf-8",
			//응답 받을 데이터 타입을 json으로 지정하여 객체로 변환 JSON.parse()
			dataType: "json"
		}).done(function(result) {
			console.log(result);
			console.log(result.StatusCode);
			if(result.StatusCode > 0) {
				console.log(result);
				addComment(result.data);
			}
			else {
				alert("댓글 달기 실패");
			}
			$("#content").val("");
		})
	}
	
	//댓글 추가하기
	//data 변수는 아작스로 전달받은 data 변수
	function addComment(data) {
		let item = "<li id= 'reply-"+data.comnum+"' class='media'>"
		 + "<div class='media-body'> "
		 + "<strong class='text-primary'>"+data.userId+"</strong> "
		 + "<p> "+data.content+" </p> "
		 + "<p>" +data.createDate +" </p> "
		 + "</div> <div class='m-2'> "
		 + "<i onclick='deleteComment("+data.comnum+")' class='material-icons'>delete</i>"
		 + "</div> </li>" ;
		$("#reply__list").prepend(item);
	}
	//댓글 삭제하기
	function deleteComment(num) {
		///요청
		console.log($("#reply-"+num));
		$.ajax({
				type: "POST",
				url: "/together/housecomment?cmd=delete&num="+num,
				dataType: "json"
		}).done(function(result) {
			console.log(result);
			if(result.StatusCode == 1) {
				console.log(result);
				//선택한 id를 삭제 (#reply-?)는 li의 id번호
				$("#reply-"+num).remove();
				
			}
			else {
				alert("데이터 삭제 실패");
			}
		})
	}
</script>

	<h2>게시글 세부 페이지입니다.</h2>
	<div>
		<p>사진:
		<br/>
		 <img src="/together/house/img/${views.ofile}" /></p>
	    <p>제목: ${views.houseName}</p>
	    <p>간략설명: ${views.scontent}</p>
	    <p>세부설명: ${views.lcontent}</p>
	    
	</div>
	
	<div>
	    <c:choose>
	        <c:when test="${sessionScope.principal.id == views.id}">
	            <form method="post" action="<%= request.getContextPath() %>/house?cmd=edit&num=${views.num}">
	                <button>수정하기</button>
	            </form>
	            <form method="post" action="<%= request.getContextPath() %>/house?cmd=delete&num=${views.num}">
	                <button>삭제하기</button>
	            </form>
	        </c:when>
	        <c:otherwise>
	            <button id="liketo"></button>
	        </c:otherwise>
	    </c:choose>
	</div>
	
	
		<!-- 댓글 박스 -->
	<div class="row bootstrap snippets">
		<div class="col-md-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2"><b>Comment</b></div>
					<div class="panel-body">
							<!--  form으로 처리하지 않고 아작스로 처리 예정으로 form태그가 없다
							<input type ="hidden" name = "bookId" value = "${book.bookId}"/>
							<!-- id 값은 세션 스코프의 principal 속성명에 저장되어있어 해당값을 호출하여 값을 활용한다 
							<input type ="hidden" name = "userId" value = "${sessionScope.principal.id}"/> -->
							<textarea  name="content" id="content" class="form-control" placeholder="write a comment..." rows="2"></textarea>
							<br />
							<!--  타입을 버튼타입 및 onclick이벤트로 commentSave()메소드를 활용하여  -->
							<!--  commentSave메소드 매개변수로 bookid와 userid를 el구문으로 작성 -->
							<button  type = "button" 
							onclick = "commentSave('${sessionScope.principal.id}',${views.num})" 
							class="btn btn-primary pull-right">댓글쓰기</button>
						</div>
						
						<div class="clearfix"></div>
						<hr />
						
						<!-- 댓글 리스트 시작-->
						<ul id="reply__list" class="media-list">
						
								<c:forEach var ="c" items ="${comments}">
								<!-- 댓글 아이템 -->
								<li id="reply-${c.comnum}" class="media">		
									<div class="media-body">
										<strong class="text-primary">${c.userId}</strong>
										<p>
											${c.content}
										</p>
										<p>
											${c.createDate}
										</p>
									</div>
									<c:if test = "${sessionScope.principal.id == c.userId}">
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
<%@ include file = "/include/footer.jsp"%>
