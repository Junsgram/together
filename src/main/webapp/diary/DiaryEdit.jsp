<%@page import="domain.board.House.DTO.WriteReqDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp"%>
	<h2> 다이어리 게시글 수정 페이지입니다.</h2>
	<div>
		<form method = "post" action = "<%= request.getContextPath()%>/diary?cmd=edit_process"
				enctype = "multipart/form-data">
			<input type ="hidden" name = "num" value ="${edit.num}" />
			<input type ="hidden" name = "change_img" value = "${edit.ofile}" />
			<p> 제목 : </p>
			<input type = "text" name ="title" value= "${edit.title}" />
			<p> 간략설명 : ${edit.scontent}</p>
			<input type = "text"  name = "scontent" value ="${edit.scontent}"/>
			<p> 세부설명 :${edit.lcontent} </p>
			<input type = "text" name = "lcontent" value ="${edit.lcontent}"/>
			<p> 사진 : <img id = "photo" src = "/together/diary/img/${edit.ofile}" style ="width: 300px;" /></p>
			<input type ="file" id = "picture" name = "ofile" />
			<br/>
			<button type ="submit"> 수정하기 </button>
		</form>
		<form>
			<button> 삭제하기 </button>
		</form>
	</div>
	<script>
	//이미지 미리보기
		let inputimg = document.querySelector("#picture");
		let pic = document.querySelector("#photo");
		
		inputimg.addEventListener("change", (e) => {
			let reader = new FileReader();
			console.log(e);
			reader.readAsDataURL(e.target.files[0]);
			reader.onload = function(e) {
				console.log(e);
				pic.setAttribute("src",e.target.result);
				pic.setAttribute("style", "width: 300px;");
			};
		});
	</script>
	
<%@ include file = "/include/footer.jsp"%>
