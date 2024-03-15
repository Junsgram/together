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
<script>
    function changeQuantity(btn, amount) {
        var quantitySpan = btn.parentNode.querySelector('.quantity');
        var quantity = parseInt(quantitySpan.innerText);
        quantity += amount;
        
        if (quantity >= 0) {
            quantitySpan.innerText = quantity;
            updateTotalPrice(); // 총 결제금액 업데이트 호출
        }
    }
    function calculateTotalPrice() {
        var priceElements = document.querySelectorAll('.price');
        var quantityElements = document.querySelectorAll('.quantity');
        var totalPrice = 0;
        console.log(priceElements);
        console.log(quantityElements);
        
        for (var i = 0; i < priceElements.length; i++) {
            var price = parseFloat(priceElements[i].innerText);
            var quantity = parseInt(quantityElements[i].innerText);
            
            totalPrice += price * quantity;
        }
        console.log(totalPrice);
        return totalPrice;
    }
    function updateTotalPrice() {
    	console.log("여기	");
    	
        var totalElement = document.querySelector('.total-price');
        totalElement.innerText = calculateTotalPrice();
    }
    // 페이지 로드 시 총 결제금액 표시
    document.addEventListener('DOMContentLoaded', function() {
        var totalElement = document.querySelector('.total-price');
        totalElement.innerText = calculateTotalPrice();
    });
</script>
<h2>장바구니 목록</h2>
<br/>
<br/>
<div>
	<h2> 총 결제금액 : <span class="total-price">0</span> </h2>           	
</div>
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
                
                                    <h5 class="fw-bolder">${c.title}</h5>
                                    <!-- Product price-->
                                    <span class="price">${c.price}</span>
                                    
                                    <!-- Order quantity -->
                        <div >
                            <button class="btn btn-outline-dark mt-auto" onclick="changeQuantity(this, -1)">-</button>
                            <span class="quantity">1</span>
                            <button class="btn btn-outline-dark mt-auto" onclick="changeQuantity(this, 1)">+</button>
                        </div>
                   <form action="<%=request.getContextPath()%>/cart?cmd=delete" method="post">
	  				<input type="hidden" name="cart_num" value="${c.num}">
	  				<input type="hidden" name ="cart_id" value="${c.id}">
	  				<input type="hidden" name="cart_title" value="${c.title}">
	  				<button type="submit" id="delete"  class="btn btn-outline-dark mt-auto">삭제</button>
	  				</form>
                                </div>
                            </div>
						 </div>
                       </div>
                    
                    </c:forEach>
                    

 </div>



<!--푸터  -->

<%@include file="../include/footer.jsp" %>