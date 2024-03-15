<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<style>
	#startBtn {
		margin : 200px 50px;
	}
</style>
		<header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">We are Together</h1>
                    <p class="lead fw-normal text-white-50 mb-0">With this shop Together homepage</p>
                    <form method = "post" action = "<%= request.getContextPath()%>/main?cmd=page">
                    	<button id = "startBtn" type="submit" class="btn btn-outline-primary">지금 시작하기</button>
                	</form>
                </div>
            </div>
        </header>
<%@ include file="../include/footer.jsp" %>
