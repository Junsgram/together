<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
	<h2>상품 등록</h2>
	<!-- 받아야 하는 데이터  -->
	<form action="<%=request.getContextPath()%>/items?cmd=saveprocess" method="post" enctype="multipart/form-data"> <!-- enctype으로 보낸건 request로 못받는다.  -->
		<input type="hidden" name="userId" value="${sessionScope.principal.id}">
		<p>상품명 <input type="text" name="title"/></p>
		<p>간단소개 <input type="text" name="scontent"/></p>
		<p>상세소개 <input type="text" name="lcontent"/></p>
		<p>가 격 <input type="number" name="price"/></p>
		<p>재고수량 <input type="number" name="quantity"/></p>
		<p>사진 <input type="file" name="ofile"/></p>
		

		<p><input type="submit" value="작성"/></p>
	
	</form>

<%@include file="../include/footer.jsp" %>