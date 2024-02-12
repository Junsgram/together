<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Shop in style</h1>
                    <p class="lead fw-normal text-white-50 mb-0">With this shop hompeage template</p>
                </div>
            </div>
        </header>
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
				<div style = "display: flex; justify-content: space-between;">
					<div></div>
					<h2>숙소 인기 게시글</h2>
					<button class="btn btn-dark" onclick ="location.href = '<%=request.getContextPath()%>/house?cmd=list&page=0'" style = "margin-right:50px;"> 더보기 </button>
				</div>
	              <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" style = "width:100%; border-bottom : 1px solid black; margin : 0 auto; margin-top : 30px;">
                    <!--  main 부분 개별 블럭  -->
         <!--  ## house  -->
                    <c:forEach var ="h" items ="${house}">
	                    <div class="col mb-5">
	                        <div class="card h-100" style = " width:300px; height: 300px;">
	                            <!-- Product image-->
	                            <img class="card-img-top"  style = "width:300px; height: 300px;" src="<%=request.getContextPath()%>/house/img/${h.ofile}" alt="..." />
	                            <!-- Product details-->
	                            <div class="card-body p-4">
	                                <div class="text-center">
	                                    <!-- Product name-->
	                                    <h5 class="fw-bolder">${h.title}</h5>
	                                    <!-- Product price-->
	                                   	<p>${h.scontent}</p>
	                                   	<p>${h.id}</p>
	                                </div>
	                            </div>
	                            <!-- Product actions-->
	                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
	                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%= request.getContextPath()%>/house?cmd=view&num=${h.num}">상세 보기 </a></div>
	                            </div>
	                       	 </div>
	                    	</div>
                    	</c:forEach>
                	</div>
        <!--  ## diary  -->
        <div style = "display: flex; justify-content: space-between; padding-top:30px;">
					<div></div>
					<h2>다이어리 인기 게시글</h2>
					<button class="btn btn-dark" onclick ="location.href = '<%=request.getContextPath()%>/diary?cmd=list&page=0'" style = "margin-right:50px;"> 더보기 </button>
				</div>
                	<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" style = "width:100%; border-bottom : 1px solid black; margin : 0 auto; margin-top : 30px;">
                    <!--  main 부분 개별 블럭  -->
                    <c:forEach var ="d" items ="${diary}">
	                    <div class="col mb-5">
	                        <div class="card h-100" style = " width:300px; height: 300px;" >
	                            <!-- Product image-->
	                            <img class="card-img-top" style = "width:300px; height: 300px;" src="<%=request.getContextPath()%>/diary/img/${d.ofile}" alt="..." />
	                            <!-- Product details-->
	                            <div class="card-body p-4">
	                                <div class="text-center">
	                                    <!-- Product name-->
	                                    <h5 class="fw-bolder">${d.title}</h5>
	                                    <!-- Product price-->
	                                   	<p>${d.scontent}</p>
	                                   	<p>${d.id}</p>
	                                </div>
	                            </div>
	                            <!-- Product actions-->
	                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
	                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">상세 보기 </a></div>
	                            </div>
	                       	 </div>
	                    	</div>
                    	</c:forEach>
                	</div>
                	
		<!--  ## communtiy  -->
		<div style = "display: flex; justify-content: space-between; padding-top:30px;">
					<div></div>
					<h2>커뮤니티 인기 게시글</h2>
					<button class="btn btn-dark" onclick ="location.href ='<%=request.getContextPath()%>/commu?cmd=list&page=0'" style = "margin-right:50px;"> 더보기 </button>
				</div>
                	<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" style = "width: 100%; border-bottom : 1px solid black; margin : 0 auto; margin-top : 30px;">
                    <!--  main 부분 개별 블럭  -->
                    <c:forEach var ="c" items ="${community}">
	                    <div class="col mb-5">
	                        <div class="card h-100" style = " width:300px; height: 300px;" >
	                            <!-- Product image-->
	                            <img class="card-img-top"  style = "width:300px; height: 300px;" src="<%=request.getContextPath()%>/community/img/${c.ofile}" alt="..." />
	                            <!-- Product details-->
	                            <div class="card-body p-4">
	                                <div class="text-center">
	                                    <!-- Product name-->
	                                    <h5 class="fw-bolder">${c.title}</h5>
	                                    <!-- Product price-->
	                                   	<p>${c.scontent}</p>
	                                   	<p>${c.id}</p>
	                                </div>
	                            </div>
	                            <!-- Product actions-->
	                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
	                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">상세 보기 </a></div>
	                            </div>
	                       	 </div>
	                    	</div>
                    	</c:forEach>
                	</div>
            </div>
            
      
        </section>
<%@ include file="../include/footer.jsp" %>
