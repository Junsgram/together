<%@page import="javax.websocket.SendResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp"%>
<%
	if(session.getAttribute("principal") == null) {
		response.sendRedirect("../user/loginForm.jsp");
	}
%>
	<h2> 첫번 째 페이지입니다.</h2>
	<div>
		<form method = "post" action ="/together/house?cmd=write_process" enctype = "multipart/form-data">
			<input type = "hidden" name ="userid" value ="${sessionScope.principal.id}">
			<p>숙소 제목 : 
				<input type = "text" name = "houseName" id ="houseName" placeholder= "숙소 제목을 입력해주세요.">
			</p>
			<p>숙소 간단설명 : 
				<input type = "text" name = "scontent" id ="scontent" placeholder= "숙소 제목을 입력해주세요.">
			</p>
			<p>숙소 세부설명 : 
				<input type = "text" name = "lcontent" id ="lcontent" placeholder= "숙소 제목을 입력해주세요.">
			</p>
			<div>이미지 등록 구간 
			<input type = "file" multiple = "multiple" accept = "image/*" 
				name = "images" id ="images" placeholder= "숙소 제목을 입력해주세요."/>
			</div>
				<!-- 이미지 미리보기  -->
				<br/>
				<img id = "image_container" />
				<!--  옆으로 넘기는 버튼 -->
				<button id ="previous"> 이전 </button>
				<button id= "next"> 다음 </button>
				<br/>
			<button type ="submit" >등록</button> 
		</form>
		<form method="post" action ="house?cmd=list&page=0">
			<button type = "submit"> 취소 </button>
		</form>
	</div>
	<script>
	//이미지 미리보기
	let image = document.querySelector("#images");
	image.addEventListener("change", (e) => {
		let reader = new FileReader();
		console.log(e);
		//파일을 읽어오며, url메모리에 저장
		reader.readAsDataURL(e.target.files[0]);
		
		reader.onload = function(e) {
			console.log(e);
			let img = document.querySelector("#image_container")
			img.setAttribute("src", e.target.result);
			img.setAttribute("style","width: 300px;" );
			
			//div에 자식요소 태그를 추가
			//document.querySelector("#image_container").appendChild(img);
		}
	})
	//이미지 이전 및 다음
	let previous = document.querySelector("#previous");
	let next = document.querySelector("#next");
	previous.addEventListener("click", function(e) {
		let img = document.querySelector("#preview_img < img");
		img.src = reader.readAsDataURL(e.target.files[1]);
	});
</script>
<%@ include file = "/include/footer.jsp"%>