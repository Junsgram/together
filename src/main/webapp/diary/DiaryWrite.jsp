<%@page import="javax.websocket.SendResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp"%>
<%
	if(session.getAttribute("principal") == null) {
		response.sendRedirect("../user/loginForm.jsp");
	}
%>
<style>
   /* 전체 폼 스타일 */
    .writeForm {
        max-width: 500px;
        margin: 0 auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #f9f9f9;
    }

    /* 입력 필드 스타일 */
    input[type="text"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    /* 이미지 등록 구간 스타일 */
    #images {
        margin-bottom: 10px;
    }

    /* 이미지 미리보기 스타일 */
    #image_container {
        max-width: 100%;
        height: auto;
        margin-bottom: 10px;
    }

    /* 버튼 스타일 */
    button {
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    /* 이전, 다음 버튼 스타일 */
    #previous,
    #next {
        margin-top: 10px;
    }
    #image_container {
    	width : 458px;
    	height: 300px;
    	background-size: cover;
    }


</style>

	
	<div style = "padding-top: 30px;">
		<form method = "post" action ="/together/diary?cmd=write_process" enctype = "multipart/form-data" class= "writeForm">
		<h2> 강아지 일기 작성하기</h2>
			<input type = "hidden" name ="userid" value ="${sessionScope.principal.id}">
			<p>오늘 일기의 제목 : 
				<input type = "text" name = "title" id ="title" placeholder= "오늘의 제목을 입력해주세요." />
			</p>
			<p>일기 내용 간단설명 : 
				<input type = "text" name = "scontent" id ="scontent" placeholder= "일기의 간단한 설명을 작성해주세요" />
			</p>
			<p>일기 내용 세부설명 :
			<br/>
				<textarea rows = "10" cols = "52.5" name = "lcontent" id ="lcontent" placeholder="우리 강아지의 하루를 자세히 설명해주세요" > </textarea>
			</p>
			
			<div>강아지 이미지 추가
				<br/>
				<div id="img_box">
				<!-- 이미지 미리보기  -->
					<img id = "image_container" />
				</div>
				<input type = "file" multiple = "multiple" accept = "image/*" 
					name = "images" id ="images" placeholder= "숙소 제목을 입력해주세요."/>
			</div>
				
				<br/>
			
			<button type ="submit" >등록</button> 
			<button type = "button" onclick = "location.href='diary?cmd=list&page=0'"> 취소 </button>
		</form>
		<form method="post" action ="house?cmd=list&page=0">
			
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
			img.setAttribute("style","width: 458px; height : 300px;" );
			
			//div에 자식요소 태그를 추가
			//document.querySelector("#image_container").appendChild(img);
		}
	})

	</script>
	
<%@ include file = "/include/footer.jsp"%>