<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
 

<script>
	//아이디 중복 체크
 	let isChecking = false;
 
	//form submit할 때 호출되는 함수
	function valid(){
		if(!isChecking){
			alert("이메일 중복 체크를 해주세요.");
		}
		return isChecking;
	} 
	
	//아이디 중복체크 버튼 클릭할 때 호출되는 함수
	function emailCheck(){
		let email = $("#email").val();
		console.log("이메일 : "+email);
		if (email==""){
			alert("아이디를 입력해주세요!");
		}else{
			$.ajax({
				type : "post",
				url : "/together/user?cmd=userIdCheck",
				data : email,
				contentType : "text/plain; charset=utf-8",
				dataType : "text"
				}).done(function(data){
	 				if(data=="ok"){
					isChecking = true;
					alert("사용이 가능한 이메일입니다.");
					}else{
					isChecking = false;
					alert("이미 사용하고 있는 이메일입니다.");
					} 
				})
			}
		}
	
</script>

<div id="joinForm">
  	<div class="container">
    	<div class="input-form-backgroud row">
      		<div class="input-form col-md-5 mx-auto">
        		<h4 class="mb-3">회원가입</h4>
        		<form class="validation-form" action="<%=request.getContextPath()%>/user?cmd=join" method="post" onsubmit="return valid()" enctype="multipart/form-data">
          			<div class="row" >
	          			<div class="" style="text-align: center; margin-left: 20px;">
	              			<br>
	              			반려견 사진
		    				<input type="file" name="photo" id="photo" accept = "image/*">
	              			<img id = "profile" src="/together/user/default_img/default_profile_img.jpg" width="150px" height="150px" class="img-circle" style="margin-bottom: 5px; border-radius: 15%;" >
	              			<br>
	              			<button type="button" class="btn btn-primary btn-xs" id="deleteImage">삭제</button>
	            	 	</div>
            	 	</div>
          			<br>

					<div class="mb-3">
			            <label for="email">이메일</label>
			            <input type="email" name="email" id="email" placeholder="사용할 이메일을 입력해주세요." class="form-control" required>
			            <button type="button" onclick = "emailCheck()" class="btn btn-primary btn-xs"> 이메일 중복 체크</button>
			        </div>
          
          			<div class="mb-3">
            			<label for="userPass1">비밀번호</label>
            			<input type="password" name="userPass1" id="userPass1" placeholder="8자 이상, 영문자, 숫자 및 특수 문자 포함." class="form-control" required>
          			</div>
          			<div id = "resultDiv1"></div>
          			</br>
          			<div class="mb-3">
            			<label for="userPass2">비밀번호 확인</label>
            			<input type="password" name="userPass2" id="userPass2" placeholder="비밀번호 확인을 위해 한번더 입력해 주세요." class="form-control" required> 
          			</div>
          			<div id = "resultDiv2"></div>
          			<div class="mb-3">
            			<label for="username">이름</label>
            			<input type="text" name="username" id="username" placeholder="이름을 입력해주세요." class="form-control" required> 
          			</div>
          			</br>
          			<div class="mb-3">
	    				<label for="call1">전화번호</label>
	    				</br>
	    				<input type="text" name="call1" id="call1" placeholder="010" class="form-control" style="width: 100px; display:inline-block" required>-
	    				<input type="text" name="call2" id="call2" placeholder="0000" class="form-control" style="width: 100px; display:inline-block" required>-
	    				<input type="text" name="call3" id="call3" placeholder="0000" class="form-control" style="width: 100px; display:inline-block" required>
	  				</div>
          		
			        <div class="mb-3">
			            <label for="address2">주소</label><br>
			            <input type="text" class="form-control"  id="zipcode" name="zipcode" placeholder="우편번호 찾기 버튼을 클릭해주세요." style="width: 300px; position: absolute " readonly>
			            <input type="button" class="btn btn-primary" value="우편번호 찾기" id="zipbtn" style="margin-left: 320px;">
			            <input type="text" class="form-control" id="addr1" name="addr1" placeholder="주소" readonly>
			            <input type="text"  class="form-control" id="addr2" name="addr2" placeholder="상세주소">
			        </div>
			        <div>
						<label for="bday">생년월일</label>
	    				<input type="date" name="bday" id="bday" class="form-control">
	  				</div>
					<div class="mb-3">
            			<label for="dogName">반려견 이름</label>
            			<input type="text" name="dogName" id="dogName" placeholder="반려견의 이름을 입력해주세요." class="form-control" required>
          			</div>
          			<hr class="mb-4">
          			<div class="custom-control custom-checkbox">
            			<input type="checkbox" class="custom-control-input" id="aggrement" required>
            			<label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
          			</div>
          			<div class="mb-4"></div>
          				<button class="btn btn-primary btn-lg btn-block" type="submit">가입하기</button>
          				<button class="btn btn-primary btn-lg btn-block" onClick="window.location.reload()">새로고침</button>
        			</form>
      		</div>
    	</div>
  	</div>
</div>  	
<script>
	let email = "${sessionScope.kakao.email}";
	let username = "${sessionScope.kakao.username}";
	console.log(email);
	console.log(username);
	if (email){
		isChecking = true;
		$("#email").val(email);
		$("#email").attr("readonly", true);
		$("#username").val(username);
		$("#username").attr("readonly", true);
	}
	
</script>

  	
  	
  	
  	
<script>
//우편번호 검색 기능
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
<script>
	//이미지 미리보기
	let inputimage = document.querySelector("#photo");
	let profile = document.querySelector("#profile");
	
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
	
	//삭제하기
	let deleteImgBtn = document.querySelector("#deleteImage");
	deleteImgBtn.addEventListener("click", function(){
		profile.setAttribute("src", "default_img/default_profile_img.jpg");
		inputimage.value="";
	});
		
</script>

<script>
	//실시간으로 비밀번호 체크해주는 함수
	const pass1 = document.querySelector("#userPass1");
	const pass2 = document.querySelector("#userPass2");
	const resultDiv1 = document.querySelector("#resultDiv1");
	const resultDiv2 = document.querySelector("#resultDiv2");

	pass1.addEventListener('input', () => {
		const pass1value = pass1.value;
	  	if (pass1value !== '') {
	      	if (!isValidPassword(pass1value)) {
				resultDiv1.innerHTML = "비밀번호는 8자 이상, 영문자, 숫자 및 특수 문자를 포함해야 합니다.";
				resultDiv1.style.color ="red";
	      	} else {
	      		resultDiv1.innerHTML = "유효한 비밀번호입니다.";
				resultDiv1.style.color ="green";
	      	}
	  	} 
	});

	pass2.addEventListener('input', () => {
	  	const pass1value = pass1.value;
	  	const pass2value = pass2.value;
	  	if (pass1value !== '' && pass2value !== '') {
	    	if (pass1value !== pass2value) {
				resultDiv2.innerHTML = "비밀번호가 일치하지 않습니다.";
				resultDiv2.style.color = "red";
	    	} else {
	    		resultDiv2.innerHTML = '비밀번호가 일치합니다.';
	    		resultDiv2.style.color = "green";
	    	}
	  	}
	});

	function isValidPassword(password) {
	  	// 최소 8자리 이상, 영문자, 숫자, 특수문자 포함 여부 확인
	  	const regex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
	  	return regex.test(password);
	}

</script>

<%@ include file="../include/footer.jsp" %>