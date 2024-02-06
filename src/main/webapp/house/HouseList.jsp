<%@page import="java.util.ArrayList"%>
<%@page import="domain.board.House.DTO.ViewReqDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%-- <%
	List<ViewReqDTO> lists = (ViewReqDTO) new ArrayList<ViewReqDTO>("lists");
%> --%>
	<div>
		<h2> 숙소 전체 게시글 페이지 입니다.</h2>
		<!-- for 반복문을 돌리지 않을경우 index번호고 받아와야한다 ${lists[0].num} -->
		<c:forEach var ="dto" items ="${lists}">
		<div>
			<p>번호 :${dto.num}</p>
			<!-- lists.getNum() -->
			<p>아이디 : ${dto.id}</p>
			<p><a href = "<%= request.getContextPath()%>/house?cmd=view&num=${dto.num}">숙소이름 :${dto.houseName}</a> </p>
			<p>간략설명 :${dto.scontent} </p>
			<p>주소 : ${dto.address}</p>
			<p>좋아요 수:${dto.likes} </p>
			<p>조회수 : ${dto.views}</p> 
			<p>별점 : ${dto.stars}</p>
			<p>날짜 : ${dto.regidate}</p>
			<p>사진 : <img src = "/together/house/img/${dto.ofile}" style = "width: 300px;"/></p>
			<p>========================</p>
		</div>
		</c:forEach>
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