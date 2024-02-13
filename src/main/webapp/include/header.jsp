<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

 <!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Together Shop</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </head>
    <body>
        <!-- Navigation-->˛
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="/together">투개더</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                    	<c:choose>
                    		<c:when test ="${sessionScope.principal!=null}">
                    			<li class="nav-item"><a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/user?cmd=logout">로그아웃</a></li>
                    			<li class="nav-item"><a class="nav-link" href="javascript:transEditForm('${sessionScope.principal.id}')">내정보 수정</a></li>
                    		</c:when>
                    		<c:otherwise>
	                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/user?cmd=loginForm">로그인</a></li>
	                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/user?cmd=joinForm">회원가입</a></li>
	                        </c:otherwise>
	                    </c:choose>
	   
	                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/items?cmd=itemslist">상품</a></li>
	                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/house?cmd=list&page=0">숙소</a></li>
	                        <li class="nav-item dropdown">
	                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
	                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	                                <li><a class="dropdown-item" href="#!">All Products</a></li>
	                                <li><hr class="dropdown-divider" /></li>
	                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
	                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
	                            </ul>
	                        </li>
                        
                    </ul>
                    <!-- form태그에 action속성으로 설정시 주소가 이상하게 입력되어 a태그로 바꿈. -->
                    <a class="d-flex" href="/together/cart?cmd=in_cart" style="text-decoration: none;">
                        <button class="btn btn-outline-dark" type="submit">
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                            <span id="cartCount" class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                        </button>
                    </a>
                </div>
            </div>
        </nav>
        <!-- Header-->

        <!-- Section-->
        <!--  main 부분  -->
        
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <script>
			function transEditForm(id){
				let form = document.createElement('form');
				
				let input = document.createElement('input');
				input.setAttribute('type', 'hidden');
				input.setAttribute('name', 'userId');
				input.setAttribute('value', id);
				
				form.appendChild(input);
				form.setAttribute('method', 'post');
				form.setAttribute('action', '<%=request.getContextPath()%>/user?cmd=editForm');
				document.body.appendChild(form);
				form.submit();
			}
			//페이지가 로드 완료되면 장바구니 업데이트함.
			//현재 itemlist에 해당 스크립트가 있어 itemlist페이지 집입시 작동함
			//향후 헤더나 index 페이지로 이동 필요함.
			$(document).ready(function() {
			  getCartItemCount();
			});
			function getCartItemCount() {
			    // AJAX를 사용하여 서버로 요청을 보내고, 장바구니 리스트의 개수를 받아옵니다.
			    // 서버의 API 엔드포인트를 호출하여 장바구니 리스트의 개수를 가져오는 예시입니다.
			    // 실제로는 서버의 API 엔드포인트를 사용해야 합니다.
			    $.ajax({
			        url: '/together/cart?cmd=count_cart',
			        method: 'GET',
			        success: function(data) {
			            // 서버로부터 받아온 장바구니 리스트의 개수를 업데이트합니다.
			            updateCartCount(data.count);
			        },
			        error: function(error) {
			        	alert('오류가 발생했습니다');
			            console.error('장바구니 리스트의 개수를 가져오는 동안 오류가 발생했습니다:', error);
			        }
			    });
			}
			function updateCartCount(count) {
			    var cartCountElement = document.getElementById("cartCount");
			    cartCountElement.innerText = count;
			}
		
		</script>
       
 