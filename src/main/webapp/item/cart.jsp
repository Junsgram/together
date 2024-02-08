<%@page import="domain.cart.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//표현식은 전부사용가능하고 el은 reqㄴ채
	//List<Cart> incart = (List<Cart>) request.getAttribute("carts");
	
	
%>
<h2>장바구니 목록</h2>
<br/>
<br/>
<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" style = "border-bottom : 1px solid black; margin-top : 30px;">
                 <c:forEach var = "c" items = "${carts}">
                    <!--  main 부분 개별 블럭  -->
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="item/img/${c.ofile}" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${c.num}</h5>
                                    <h5 class="fw-bolder">${c.title}</h5>
                                    <!-- Product price-->
                                    ${c.price}
                   <form action="<%=request.getContextPath()%>/cart?cmd=delete" method="post">
	  				<input type="hidden" name="cart_num" value="${c.id}">
	  				<input type="hidden" name="cart_title" value="${c.title}">
	  				<button type="submit" id="delete"  class="btn btn-primary">삭제</button>
	  				</form>
                                </div>
                            </div>
						 </div>
                       </div>
                    
                    </c:forEach>

 </div>



<!--푸터  -->

<%@include file="../include/footer.jsp" %>