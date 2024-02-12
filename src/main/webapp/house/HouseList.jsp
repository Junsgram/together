<%@page import="java.util.ArrayList"%>
<%@page import="domain.board.House.DTO.ViewReqDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

		<h2> 숙소 전체 게시글 페이지 입니다.</h2>
		<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" style = "width:1400px; margin : 0 auto;" >
			<c:forEach var = "dto" items = "${lists}">
			<!--  main 부분 개별 블럭  -->
				<div class="col mb-5" style = "maring: 0 30px;">
					<div class="card h-100" style = "width:300px; height: 300px; ">
						<!-- House image-->
						<img class="card-img-top" src="/together/house/img/${dto.ofile}" alt="houseImg" 
						style = "width:300px; height: 300px;"/>
						<!-- House details-->
						<div class="card-body p-4">
							<div class="text-center">
							    <!-- House name-->
								<h5 class="fw-bolder">${dto.houseName}</h5>
								<p>${dto.scontent}</p>
								<h6>작성자 : ${dto.id }</h6>
								<p>주소 : ${dto.address}</p>
								<span>좋아요 : ${dto.likes}</span><span> 조회수 : ${dto.views}</span><span> 별점 : ${dto.stars}</span>
							</div>
						</div>
						<!-- Product actions-->
						<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
						<div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%= request.getContextPath()%>/house?cmd=view&num=${dto.num}">상세 보기</a></div>
						</div>
					</div>
				</div>
			</c:forEach>
        </div>
        
        <!--  paging  -->
        <div style = "text-align : center;">
        	<button><a href= "house?cmd=list&page=0"> 처 음</a></button>
			<c:choose>
				<c:when test = "${page==0}">
					<button disabled><a href= "house?cmd=list&page=${page-1}"> 이 전</a></button>
				</c:when>
				<c:otherwise>
					<button><a href= "house?cmd=list&page=${page-1}"> 이 전</a></button>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test ="${page==lastPage}">
					<button disabled><a href= "house?cmd=list&page=${page+1}"> 다 음</a></button>
				</c:when>
				<c:otherwise>
					<button><a href= "house?cmd=list&page=${page+1}"> 다 음</a></button>
				</c:otherwise>
			</c:choose>
	
			<button><a href= "house?cmd=list&page=${lastPage}"> 마 지 막</a></button>
			<button><a href= "house?cmd=write"> 글 등록 </a></button>
		</div>
<%@ include file = "/include/footer.jsp"%>