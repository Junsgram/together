<%@page import="domain.items.Items"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
	
	function addcart(){
		//히든으로 아이템id를 받음.
		let itemId= $("#item_id").val();
		
		//네트워크요청
		$.ajax({
			type:"post",
			url:"/together/items?cmd=add_cart",
			data:itemId,
			contentType:"text/plain;charset=utf-8",
			dataType:"text"
		}).done(function(data){
			
			
		})
	}
		
	   

</script>

<%
	List<Items> items = (List<Items>) request.getAttribute("items");
%>
<!--헤더  -->

<h2>아이템목록</h2>
<br/>
<a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/items?cmd=save">상품등록</a>
<a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/items?cmd=cart">장바구니</a>
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
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/items?cmd=detail&id=${i.id }">상세보기</a></div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
      </div>
	




	  
	  
	  
	  

	  
	  
	</div>
	
<br/>
	<div class="card">
	  <div class="card-body">
	  <h3></h3>
	  <p>${i.scontent}</p>
	  <p></p>
	  <p>${i.likes}</p>
	  <p>${i.stars}</p>
	  <p>${i.regidate}</p>
	  <a href="<%=request.getContextPath()%>/items?cmd=detail&id=${i.id }" class="btn btn-primary">상세보기</a>
	  <form>
	  <p><input type="hidden" name="item_id" id="item_id" value="${i.id}"></p>
	  <button type="button" onclick="addcart()"  class="btn btn-primary">장바구니 담기</button>
	  </form>
	  <button type="button" id="pay"  class="btn btn-primary" onclick="requestPay()">결제</button>
	  <form action="<%=request.getContextPath()%>/items?cmd=delete" method="post">
	  	<input type="hidden" name="item_num" id="item_num" value="${i.num}">
	  <button type="submit" id="delete"  class="btn btn-primary">삭제</button>
		</form>
		</div>
<!--푸터  -->

<%@include file="../include/footer.jsp" %>
