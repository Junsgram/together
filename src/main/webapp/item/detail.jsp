<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<div class="container">
	<c:if test="${sessionScope.istodo!=null}">
	<form style="display:inline-block" action="<%=request.getContextPath()%>/items?cmd=delete" method="post">
	<input type="hidden" name ="id" value=${item.id}>
	<button type="submit" class="btn btn-danger">삭제</button>
	</form>
	<button class="btn btn-primary">수정</button>
	</c:if>
	<br />
	<br />
	
	<h3>상품상세보기</h3>
	<br />
	<br />
	<h3 class="m-2">
		<b>${item.title}</b>
	</h3>
	<hr />
	<div class="m-2" style="display:flex;">
		<div style="width:50%"> stars : <i>${item.stars}</i></div>
		<div style="width:50%"> 사진 : <i><img src="./uploading/${item.ofile}" /></i></div>
	</div>
	<hr />
	<div class="m-2" style="display:flex;">
		<div style="width:50%"> 가격 : <i>${item.price}</i></div>
		<div style="width:50%"> 간단설명 : <i>${item.scontent}</i></div>
	</div>
	<hr />
	<div class="form-group">
		<div class="m-2">${item.lcontent}</div>
	</div>

	<hr />



<%@include file="../include/footer.jsp" %>