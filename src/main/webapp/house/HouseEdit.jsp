<%@page import="domain.board.House.DTO.WriteReqDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp"%>
	<h2> 게시글 수정 페이지입니다.</h2>
	<div>
		<form method = "post" action = "<%= request.getContextPath()%>/house?cmd=edit_process"
				enctype = "multipart/form-data">
			<input type ="hidden" name = "num" value ="${edit.num}" />
			<input type ="hidden" name = "change_img" value = "${edit.ofile}" />
			<p> 제목 : </p>
			<input type = "text" name ="houseName" value= "${edit.houseName }" />
			<p> 간략설명 : ${edit.scontent}</p>
			<input type = "text"  name = "scontent" value ="${edit.scontent}"/>
			<p> 세부설명 :${edit.lcontent} </p>
			<input type = "text" name = "lcontent" value ="${edit.lcontent}"/>
			<p> 사진 : <img src = "/together/house/img/${edit.ofile}" /></p>
			<input type ="file" name = "ofile" />
			<br/>
			<button type ="submit"> 수정하기 </button>
		</form>
		<form>
			<button> 삭제하기 </button>
		</form>
	</div>
	
<%@ include file = "/include/footer.jsp"%>
