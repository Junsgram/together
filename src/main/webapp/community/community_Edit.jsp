<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<style>
#pageTitle {
	text-align: center;
}
#editSector {
	width:100%;
	max-width : 1400px;
	margin: 0 auto;
}
#flexSector{
	display:flex;
}
#textSector{
	width:50%;
	padding: 20px;
}
#picSector{
	width:50%;
	text-align: center;
}
#form1 {
	float: right;
}
.file_button{
  padding: 6px 25px;
  background-color:#FF6600;
  border-radius: 4px;
  color: white;
  cursor: pointer;
}
  #photo {
 	width: 500px;
 	height: 500px;
 	background-size:cover;
 	}
 #submitBtn {
	text-align:center;
 }
}
</style>
<div id="editSector">
	<h2 id="pageTitle">다이어리 게시글 수정 페이지</h2>
	<div >
		<form method="post"
		action="<%= request.getContextPath()%>/commu?cmd=delete&num=${dto.num}">
			<button type = "submit" class="btn btn-primary pull-right" id="form1">삭제하기</button>
		</form>
		<form method="post"
			action="<%= request.getContextPath()%>/commu?cmd=edit_process"
			enctype="multipart/form-data">
			<input type="hidden" name="num" value="${dto.num}" />
			 <input type="hidden" name="change_img" value="${dto.ofile}" />
			 <div id="flexSector">
				 <div id="picSector">
				 	<input type="file" style="display:none;" id="picture" name="ofile" />
					<br/> <img id="photo" src="/together/community/img/${dto.ofile}"/> <br />
					<label class="file_button" for="picture">
				 		파일 업로드
				 	</label>
				</div>
				 <div id="textSector">
					<p> 제목 :</p>
					<input type="text" name="title" value="${dto.title}" />
					<p>간략설명 : </p>
					<textarea rows="3" cols="52.5"  name="scontent">${dto.scontent}</textarea> 
					<p>세부설명 :</p>
					<textarea rows="10" cols="52.5" name="lcontent">${dto.lcontent}</textarea> 
				</div>
			</div>
			<div id="submitBtn">
				<button class="btn btn-primary pull-right" type="submit">수정하기</button>
			</div>
		</form>
	</div>
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
			};
		});
	</script>
<%@ include file="../include/footer.jsp" %>