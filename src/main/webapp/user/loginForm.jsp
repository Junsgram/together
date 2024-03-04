<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
	Kakao.init('1496fbe7f822538136096e43d2d60c95');
	//카카오 로그인 접속해서 데이터 받아오기
	function kakaoLogin(){
		Kakao.Auth.login({
			success : function(response){
				Kakao.API.request({
					url : '/v2/user/me',
					success : function(response){
						console.log(response)
						let data = {
							email : response.kakao_account.email,
							username : response.kakao_account.profile.nickname
						}
						kakaoSiteLogin(data)
					},
					fail : function(error){
						console.log(error)
					}
				})
			},
			fail: function(error){
				console.log(error)
			}
		})
	}
	//받은 데이터로 회원 정보 확인
	function kakaoSiteLogin(result){
		console.log(result);
		//result 객체---> json으로 변경 후 서버에 전송
		$.ajax({
			type : "POST",
			url : "/together/user?cmd=kakaologin",
			data : JSON.stringify(result),
			contentType : "application/json; charset=utf-8",
			dataType : "text"
		}).done(function(logindata){
			if(logindata=="fail"){
				alert("회원가입을 해 주세요!")
				location.href="/together/user?cmd=joinForm";
			}else if(logindata=="ok"){
				alert("로그인 되었습니다.")
				location.href="/together/index.jsp";
			}
		})
	}
</script>



<%
	String savedEmail="";
	Cookie[] cookies = request.getCookies();
	if (cookies!=null && cookies.length>0){
		for (Cookie c : cookies){
			String cName = c.getName();
			String cValue = c.getValue();
			if (cName.equals("rememberId")){
				savedEmail = cValue;
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
			            
			            <input type="email" name="email" id="email" placeholder="이메일을 입력하세요!" class="form-control"  value="<%=savedEmail%>" required>
			            <input type="checkbox" name="remember" value="true" id = "rememberBox"/> 이메일 기억하기
			        </div>
          			<div class="mb-3">
            			<input type="password" name="userPass" id="userPass" placeholder="비밀번호를 입력하세요!" class="form-control" required>
          			</div>
          			<div class="mb-4"></div>
          				<button class="btn btn-primary btn-lg btn-block" type="submit">로그인</button>
        		</form>
      			<br/>
      			<!-- 카카오 로그인 아이콘 -->
      			<div>
      				<p onclick="kakaoLogin()">
      					<a href="javascript:void(0)">
      						<img src="/together/kakao_login_medium_wide.png" width="230"/>
      					</a>
      				</p>
      			</div>
      		</div>
    	</div>
  	</div>
</div>  	
  	

<%@ include file="../include/footer.jsp" %>