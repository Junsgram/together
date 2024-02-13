<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %><%-- 
	<h2>동아리 글쓰기 페이지 입니다.</h2>
	<div>
		<form method = "post" action ="/together/commu?cmd=write_process" enctype = "multipart/form-data">
			<input type = "hidden" name = "userId" id ="userId" value="${sessionScope.principal.id}"/>
			<p>동아리 제목 : 
				<input type = "text" name = "title" id ="title" placeholder= "동아리 제목을 입력해주세요.">
			</p>
			<p>동아리 간단설명 : 
				<input type = "text" name = "scontent" id ="scontent" placeholder= "동아리에 대한 간단한 소개글을 입력해주세요.">
			</p>
			<p>동아리 세부설명 : 
				<input type = "text" name = "lcontent" id ="lcontent" placeholder= "동아리에 대한 세부 설명을 작성해주세요.">
			</p>
			<p>동아리 모집 장소 : 
				<input type = "text" name = "address" id ="address" placeholder= "동아리 모집 장소를 작성해주세요.">
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
	
</script>
 --%>
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
		<form method = "post" action ="/together/commu?cmd=write_process" enctype = "multipart/form-data" class= "writeForm">
		<h2> 동아리 모집하기</h2>
			<input type = "hidden" name ="userId" value ="${sessionScope.principal.id}">
			<p>동아리의 제목 : 
				<input type = "text" name = "title" id ="title" placeholder= "동아리 제목을 입력해주세요." />
			</p>
			<p>동아리 간단설명 : 
				<input type = "text" name = "scontent" id ="scontent" placeholder= "동아리에 대한 간단한 소개글을 입력해주세요." />
			</p>
			<p>동아리 세부설명 :
			<br/>
				<textarea rows = "10" cols = "52.5" name = "lcontent" id ="lcontent" placeholder="동아리에 대한 세부 설명을 작성해주세요." > </textarea>
			</p>
			<p>동아리 모집 장소 : 
				<input type = "text" name = "address" id ="address" placeholder= "동아리 모집 장소를 작성해주세요.">
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
			<button type = "button" onclick = "location.href='community?cmd=list&page=0'"> 취소 </button>
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
	
<%@ include file="../include/footer.jsp" %>