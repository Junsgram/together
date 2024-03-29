<%@page import="domain.items.Items"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
	//변수로 ${i.id},${i.title},${i.price},${i.ofile},${i.quantity}를 받음.
	function addcart(id,title,price,ofile,quantity){
		//히든으로 아이템id를 받음.
		let data = {
				//id는폼에서 히든으로 같이 보냄.
				id:id,
				title: title,
				price: price,
				ofile: ofile,
				quantity: quantity
		};
		console.log(id);
		//네트워크요청
		$.ajax({
			type:"post",
			url:"/together/cart?cmd=add_cart",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8",
			dataType:"json"
		}).done(function(data){
			//장바구니 추가 끝난상태 
			//장바구니 추가 버튼의 css를 변화시키고 싶음.
			 // 원하는 CSS 클래스를 추가
			console.log('Ajax 요청 성공');
			console.log(data.message);
			getCartItemCount();
			alert(data.message);
		})
	}
</script>

<%
// 필요없는 구문
	List<Items> items = (List<Items>) request.getAttribute("items");
%>
<!--헤더  -->
<header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">We are Together</h1>
                    <p class="lead fw-normal text-white-50 mb-0">With this shop Together hompage</p>
                </div>
            </div>
        </header>
<h2>아이템목록</h2>
<br/>
<a class="btn btn-dark" href="<%=request.getContextPath()%>/items?cmd=save">상품등록</a>
<a class="btn btn-dark" href="<%=request.getContextPath()%>/cart?cmd=in_cart">장바구니</a>
<br/>

	<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" style = "border-bottom : 1px solid black; margin-top : 30px;">
                    <c:forEach var = "i" items = "${items}">
                    <!--  main 부분 개별 블럭  -->
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="item/img/${i.ofile}" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${i.title}</h5>
                                    <!-- Product price-->
                                    ${i.price}
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/items?cmd=detail&num=${i.num}">상세보기</a></div>
                            </div>
								  <button type="button" onclick="addcart('${sessionScope.principal.id}','${i.title}','${i.price}','${i.ofile}','${i.quantity}')"  class="btn btn-dark">장바구니 담기</button>
						 </div>
                       
                     </div>
                    </c:forEach>
	</div>
	
<br/>
<!--푸터  -->

<%@include file="../include/footer.jsp" %>
