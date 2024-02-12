<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
	<h2> 게시글 수정 페이지입니다.</h2>
	<div>
		<form method = "post" action = "<%= request.getContextPath()%>/commu?cmd=edit_process"
				enctype = "multipart/form-data">
			<input type ="hidden" name = "num" value ="${dto.num}" />
			<input type ="hidden" name = "change_img" value = "${dto.ofile}" />
			<p> 제목 : </p>
			<input type = "text" name ="commuName" value= "${dto.commuName }" />
			<p> 간략설명 : </p>
			<input type = "text"  name = "scontent" value ="${dto.scontent}"/>
			<p> 세부설명 : </p>
			<input type = "text" name = "lcontent" value ="${dto.lcontent}"/>
			<p> 사진 : <img src = "/together/community/img/${dto.ofile}" /></p>
			<input type ="file" name = "ofile" />
			<br/>
			<button type ="submit"> 수정하기 </button>
		</form>
		<form>
			<button> 삭제하기 </button>
		</form>
	</div>
<%@ include file="../include/footer.jsp" %>