<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
//카카오 초기화
	Kakao.init('5bdd749640e0f348f052ce93040a9667');
	Kakao.isInitialized();
//카카오 로그인 코드 확인
	
</script>



<%
	String savedId="";
	Cookie[] cookies = request.getCookies();
	if (cookies!=null && cookies.length>0){
		for (Cookie c : cookies){
			String cName = c.getName();
			String cValue = c.getValue();
			if (cName.equals("rememberId")){
				savedId = cValue;
			}
		}
	}
%>

	<h2>로그인 페이지 입니다.</h2>
	<div>
		<form action="<%=request.getContextPath()%>/user?cmd=login" method="post">
	  		<div>
	    		아이디
	    		<input type="text" name="userId" id="userId" placeholder="Enter Id" value="<%=savedId%>">
	  		</div>
	  		<div>
	        	<input type="checkbox" name="remember" value="true"> Remember me
	    	</div>
	  		<div>
	    		비밀번호
	    		<input type="password" name="userPass" id="userPass" placeholder="Enter Password">
	  		</div>
	  		<button type="submit">로그인</button>
	  	</form>
  	</div>

<%@ include file="../include/footer.jsp" %>