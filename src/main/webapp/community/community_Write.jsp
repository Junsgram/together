<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
	<h2>동아리 글쓰기 페이지 입니다.</h2>
	<div>
		<form method = "post" action ="/together/commu?cmd=write_process" enctype = "multipart/form-data">
			<input type = "hidden" name = "userId" id ="userId" value="${sessionScope.principal.id}"/>
			<p>동아리 제목 : 
				<input type = "text" name = "commuName" id ="commuName" placeholder= "동아리 제목을 입력해주세요.">
			</p>
			<p>동아리 간단설명 : 
				<input type = "text" name = "scontent" id ="scontent" placeholder= "동아리에 대한 간단한 소개글을 입력해주세요.">
			</p>
			<p>동아리 세부설명 : 
				<input type = "text" name = "lcontent" id ="lcontent" placeholder= "동아리에 대한 세부 설명을 작성해주세요.">
			</p>
			<div>이미지 등록 구간 
			<input type = "file" multiple = "multiple" accept = "image/*" name = "images" id ="images"/>
			</div>
				<!-- 이미지 미리보기  -->
				<div id = "image_container"></div>
			<button type ="submit" >등록</button> 
		</form>
		<form method="post" action ="house?commu=list&page=0">
			<button type = "submit"> 취소 </button>
		</form>
	</div>
	<script>
	//여러 이미지 미리보기
	let images = document.querySelector("#images");
	images.addEventListener("change", (e) => {
		for (let image of e.target.files){
			let reader = new FileReader();
			console.log(e);
			reader.onload = function(e){
				let img = document.createElement("img");
				img.setAttribute("src", e.target.result);
 				img.setAttribute("width", 300);
				img.setAttribute("height", 300);
				document.querySelector("div#image_container").appendChild(img);
			};
			//파일을 읽어오며, url메모리에 저장
			reader.readAsDataURL(image);
		};
	});
	
	//미리보기 삭제하기
</script>
<%@ include file="../include/footer.jsp" %>