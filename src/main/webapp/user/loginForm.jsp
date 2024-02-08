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


<div id="loginForm">
  	<div class="container">
    	<div class="input-form-backgroud row">
      		<div class="input-form col-md-5 mx-auto">
        		<form class="validation-form" action="<%=request.getContextPath()%>/user?cmd=login" method="post">
					<div class="mb-3">
			            
			            <input type="text" name="userId" id="userId" placeholder="아이디를 입력하세요!" class="form-control"  value="<%=savedId%>" required>
			            <input type="checkbox" name="remember" value="true" id = "rememberBox"/> 아이디 기억하기
			        </div>
          			<div class="mb-3">
            			<input type="password" name="userPass" id="userPass" placeholder="비밀번호를 입력하세요!" class="form-control" required>
          			</div>
          			<div class="mb-4"></div>
          				<button class="btn btn-primary btn-lg btn-block" type="submit">로그인</button>
        			</form>
      		</div>
    	</div>
  	</div>
</div>  	
  	

<%@ include file="../include/footer.jsp" %>