<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Cookie[] cart = request.getCookies();
if (cart == null && cart.length == 0) {
    out.print("장바구니가 비어있습니다..");
}
List<Cookie> cookieList = new ArrayList<>();
if (cart != null) {
    for (Cookie cookie : cart) {
        cookieList.add(cookie);
    }
}
request.setAttribute("cart", cookieList);

%>
}
<h2>장바구니 목록</h2>
<br/>
<a href="<%=request.getContextPath()%>/items?cmd=save" class="btn btn-primary">상품등록</a>

<br/>
<c:forEach var = "c" items = "${cart}">
	<div class="card">
	  <div class="card-body">
	  <h3> 상품번호 : ${c.value}</h3>
	  
	  <!-- 콘텍스트 경로 불러오기 ex> /greenbooks -->
	  <a href="<%=request.getContextPath()%>/items?cmd=detail&id=${c.value }" class="btn btn-primary">상세보기</a>

	  <form>
	  <p><input type="hidden" name="item_id" id="item_id" value="${i.id}"></p>
	  </form>
	  </div>
	</div>
<br/>
</c:forEach>





<!--푸터  -->

<%@include file="../include/footer.jsp" %>