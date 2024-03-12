<%@page import="java.util.ArrayList"%>
<%@page import="domain.board.House.DTO.ViewReqDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">We are Together</h1>
                    <p class="lead fw-normal text-white-50 mb-0">With this shop Together hompage</p>
                </div>
            </div>
        </header>
		<div style = "width: 1400px; max-width:100%; margin : 0 auto;">
        <div style = "display: flex; justify-content: space-between; padding-top:30px;">
        	<div></div>
        	<h2> 오늘의 강아지 일기</h2>
        	<button class="btn btn-dark" onclick ="location.href = 'diary?cmd=write'"> 글 등록 </button>
        </div>
		<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center"
		style = "width:100%; margin : 0 auto; padding-top: 50px;">
		<!-- for 반복문을 돌리지 않을경우 index번호고 받아와야한다 ${lists[0].num} -->
			<c:forEach var = "dto" items = "${lists}">
			<!--  main 부분 개별 블럭  -->
				<div class="col mb-5">
					<div class="card h-100" style = " width:300px; height: 300px; margin: 5px 50px;">
						<!-- House image-->
						<img class="card-img-top" src="/together/diary/img/${dto.ofile}" alt="diaryImg" 
						style = "width:300px; height: 300px;"/>
						<!-- House details-->
						<div class="card-body p-4">
							<div class="text-center">
							    <!-- House name-->
								<h5 class="fw-bolder">${dto.title}</h5>
								<p>${dto.scontent}</p>
								<h6>작성자 : ${sessionScope.principal.dogname }</h6>
								<span>좋아요 : ${dto.likes}</span><span> 조회수 : ${dto.views}</span><span> 별점 : ${dto.stars}</span>
							</div>
						</div>
						<!-- Product actions-->
						<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
						<div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%= request.getContextPath()%>/diary?cmd=view&num=${dto.num}">상세 보기</a></div>
						</div>
					</div>
				</div>
			</c:forEach>
        </div>
        
        <!--  paging  -->
        <div style = "text-align : center; padding-bottom : 30px;">
        	<button class="btn btn-dark" onclick ="location.href = 'diary?cmd=list&page=0'"> 처 음</button>
			<c:choose>
				<c:when test = "${page==0}">
					<button  class="btn btn-dark" onclick ="location.href ='diary?cmd=list&page=${page-1}'" disabled> 이 전</button>
				</c:when>
				<c:otherwise>
					<button class="btn btn-dark" onclick ="location.href ='diary?cmd=list&page=${page-1}'" > 이 전</button>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test ="${page==lastPage}">
					<button class="btn btn-dark" disabled onclick ="location.href = 'diary?cmd=list&page=${page+1}'" >다 음</button>
				</c:when>
				<c:otherwise>
					<button class="btn btn-dark" onclick ="location.href ='diary?cmd=list&page=${page+1}'" >다 음</button>
				</c:otherwise>
			</c:choose>
			<button  class="btn btn-dark" onclick ="location.href ='diary?cmd=list&page=${lastPage}'" > 마 지 막</button>
		</div>
	</div>
<%@ include file = "/include/footer.jsp"%>