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
<<<<<<< HEAD
	                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/diary?cmd=list&page=0">Diary</a></li>
=======
	                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/commu?cmd=list&page=0">커뮤니티</a></li>
>>>>>>> dev
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
                    <form class="d-flex">
                        <button class="btn btn-outline-dark" type="submit">
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                            <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                        </button>
                    </form>
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
		
		</script>
       
 