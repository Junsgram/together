<%@page import="domain.user.User"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
 
<script>
//비밀번호 확인하는 메소드
function passCheck(){
	let pass1 = $("#userPass1").val();
	let pass2 = $("#userPass2").val();
	
	//정규 표현식
	const passRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,}$/;
	
	if(passRegex.test(pass1)){
		if (pass1==pass2){
			return true;
		}else{
			resultDiv.innerHTML = "비밀번호가 일치하지 않습니다.";
			resultDiv.style.color = "red";
			return false;
		}
	}else{
		resultDiv.innerHTML = "비밀번호는 8자 이상, 영문자, 숫자 및 특수 문자를 포함해야 합니다.";
		resultDiv.style.color ="red";
		return false;
	}
}
</script>



	
	<div id="joinForm">
  	<div class="container">
    	<div class="input-form-backgroud row">
      		<div class="input-form col-md-5 mx-auto">
        		<h4 class="mb-3">회원 정보 수정</h4>
        		<form class="validation-form" action="<%=request.getContextPath()%>/user?cmd=edit" method="post" onsubmit="return passCheck()" enctype="multipart/form-data">
          			<div class="row" >
	          			<div class="" style="text-align: center; margin-left: 20px;">
	              			<br>
	              			반려견 사진
		    				<input type="file" name="photo" id="photo" accept = "image/*">
		    				<input type="hidden" name="originPic" value="${sessionScope.principal.ofile}"/>
	              			<c:choose>
								<c:when test="${sessionScope.principal.ofile == 'default_profile_img.jpg'}">
									<img id = "profile" src="/together/user/default_img/default_profile_img.jpg" width="150px" height="150px" class="img-circle" style="margin-bottom: 5px; border-radius: 15%;"/>
								</c:when>
								<c:otherwise>
									<img id = "profile" src="/together/user/profile_img/${sessionScope.principal.ofile}" width="150px" height="150px" class="img-circle" style="margin-bottom: 5px; border-radius: 15%;"/>
								</c:otherwise>
							</c:choose>
	              			<br>
	              			<button type="button" class="btn btn-primary btn-xs" id="deleteImage">삭제</button>
	            	 	</div>
            	 	</div>
          			<br>

					<div class="mb-3">
			            <label for="userId">아이디</label>
			            <input type="text" name="userId" id="userId" value="${sessionScope.principal.id}" class="form-control" readonly>
			        </div>
          
          			<div class="mb-3">
            			<label for="userPass1">비밀번호</label>
            			<input type="password" name="userPass1" id="userPass1" value="${sessionScope.principal.pw2}" class="form-control" required>
          			</div>
          			<div class="mb-3">
            			<label for="userPass2">비밀번호 확인</label>
            			<input type="password" name="userPass2" id="userPass2" value="${sessionScope.principal.pw2}" class="form-control" required> 
          			</div>
          			<div id = "resultDiv"> </div>
          			<div class="mb-3">
            			<label for="userPass1">이메일</label>
            			<input type="text" name="email" id="email" value="${sessionScope.principal.email}" class="form-control" required> 
          			</div>
          			<div class="mb-3">
	    				<label for="call1">전화번호</label>
	    				</br>
	    				<input type="text" name="call1" id="call1" value="${sessionScope.principal.call1}" class="form-control" style="width: 100px; display:inline-block" required>-
	    				<input type="text" name="call2" id="call2" value="${sessionScope.principal.call2}" class="form-control" style="width: 100px; display:inline-block" required>-
	    				<input type="text" name="call3" id="call3" value="${sessionScope.principal.call3}" class="form-control" style="width: 100px; display:inline-block" required>
	  				</div>
          		
			        <div class="mb-3">
			            <label for="address2">주소</label><br>
			            <input type="text" class="form-control"  id="zipcode" name="zipcode" value="${sessionScope.principal.zipcode}" style="width: 300px; position: absolute " readonly>
			            <input type="button" class="btn btn-primary" value="우편번호 찾기" id="zipbtn" style="margin-left: 320px;">
			            <input type="text" class="form-control" id="addr1" name="addr1"  value="${sessionScope.principal.addr1}" readonly>
			            <input type="text"  class="form-control" id="addr2" name="addr2" value="${sessionScope.principal.addr2}">
			        </div>
			        <div>
						<label for="bday">생년월일</label>
	    				<input type="date" name="bday" id="bday" class="form-control" value="${sessionScope.principal.birthday}">
	  				</div>
					<div class="mb-3">
            			<label for="dogName">반려견 이름</label>
            			<input type="text" name="dogName" id="dogName" value="${sessionScope.principal.dogname}" class="form-control" required>
          			</div>
          			<hr class="mb-4">
          			<div class="mb-4"></div>
          				<button class="btn btn-primary btn-lg btn-block" type="submit">수정</button>
        			</form>
      		</div>
    	</div>
  	</div>
</div>  	
	
	
	
	
	
<script>
	//미리보기 화면
	let profile = document.querySelector("#profile");
	let inputimage = document.querySelector("#photo");
	
	inputimage.addEventListener("change", (e)=>{
		let reader = new FileReader();
		console.log(e);
		reader.readAsDataURL(e.target.files[0]);
		reader.onload = function(e){
			console.log(e);
			profile.setAttribute("src", e.target.result);
			profile.setAttribute("style", "width:200px");
		};
	});
	
	
	
	//삭제하기 버튼 누르면 미리보기 화면에 디폴트 이미지 들어가게 해두기
	let deleteImgBtn = document.querySelector("#deleteImage");
	deleteImgBtn.addEventListener("click", function(){
	profile.setAttribute("src", "/together/user/default_img/default_profile_img.jpg");
	inputimage.value="";
	});
	
	
</script>
<script>
//우편번호 검색하기
	let zipbtn = document.querySelector("#zipbtn");
	zipbtn.addEventListener("click",()=>{
		new daum.Postcode({
	        oncomplete: function(data) {
	        	console.log(data);
	        	let fullAddr = "";
	        	let extraAddr = "";
	        	
	        	if (data.userSelectedType=='R'){
	        		fullAddr =  data.roadAddress;
	        	}else{
	        		fullAddr = data.jibunAddress;
	        	}
	        	
	        	//extraAddr에 값 대입하기
	       		if(data.bname !==''){
					extraAddr += data.bname;
				}
				if(data.buildingName !==''){
					extraAddr += (extraAddr !=='' ? ', '+data.buildingName : data.buildingName)
				}
	        	
				fullAddr += (extraAddr !=='' ? ' ('+extraAddr+')' : '');
	        	
	        	document.querySelector("#addr1").value = fullAddr;
	        	document.querySelector("#zipcode").value= data.zonecode;
	        	document.querySelector("#addr2").focus();
	        	}
	    }).open();
	});

</script>

<%@ include file="../include/footer.jsp" %>