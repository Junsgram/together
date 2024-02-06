<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	//아이디 중복 체크
 	let isChecking = false;
 
	//form submit할 때 호출되는 함수
	function valid(){
		if(!isChecking){
			alert("아이디 중복 체크를 해주세요.");
		}else{
			passCheck();
		}
		return isChecking;
	} 
	
	//아이디 중복체크 버튼 클릭할 때 호출되는 함수
	function idCheck(){
		let userId = $("#userId").val();
		$.ajax({
			type : "post",
			url : "/together/user?cmd=userIdCheck",
			data : userId,
			contentType : "text/plain; charset=utf-8",
			dataType : "text"
		}).done(function(data){
 			if(data=="ok"){
				isChecking = true;
				alert("사용이 가능한 아이디입니다.");
			}else{
				isChecking = false;
				alert("이미 사용하고 있는 아이디입니다.");
			} 
		})
	}
	
		
	//비밀번호 확인하는 메소드
	function passCheck(){
		let pass1 = $("#userPass1").val();
		let pass2 = $("#userPass2").val();
		
		//정규 표현식
		const passRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,}$/;
		
		if(passRegex.test(pass1)){
			if (pass1==pass2){
				isChecking = true;
			}else{
				resultDiv.innerHTML = "비밀번호가 일치하지 않습니다.";
				resultDiv.style.color = "red";
				isChecking = false;
			}
		}else{
			resultDiv.innerHTML = "비밀번호는 8자 이상, 영문자, 숫자 및 특수 문자를 포함해야 합니다.";
			resultDiv.style.color ="red";
			isChecking = false;
		}
	}
	



</script>


	<h2>회원가입 페이지 입니다.</h2>
	<p>(*)가 표시된 항목은 필수 항목입니다.
	<div>
		<form action="<%=request.getContextPath()%>/user?cmd=join" method="post" onsubmit="return valid()" enctype="multipart/form-data">
	  		<div>
	    		아이디 *
	    		<input type="text" name="userId" id="userId" placeholder="사용할 아이디를 입력해주세요." required>
	  			<button type="button" onclick = "idCheck()"> 아이디 중복 체크</button>
	  		</div>
	  		<div>
	    		비밀번호 *
	    		<input type="password" name="userPass1" id="userPass1" placeholder="8자 이상, 영문자, 숫자 및 특수 문자를 포함하도록 작성해주세요." required>
	  		</div>
	  		<div>
	    		비밀번호 확인 *
	    		<input type="password" name="userPass2" id="userPass2" placeholder="비밀번호 확인을 위해 한번더 입력해 주세요." required>
	  		</div>
	  		<div id = "resultDiv"> </div>
	  		<div>
	    		이메일 *
	    		<input type="email" name="email" id="email" placeholder="이메일을 입력해 주세요." required>
	  		</div>
	  		<div>
	    		전화번호 *
	    		<input type="text" name="call1" id="call1" placeholder="010" required>
	    		<input type="text" name="call2" id="call2" placeholder="0000" required>
	    		<input type="text" name="call3" id="call3" placeholder="0000" required>
	  		</div>
	  		<div>
	    		우편번호 *
	    		<input type="text" name="zipcode" id="zipcode" placeholder="우편번호 검색 버튼을 클릭해주세요." readonly>
	  			<button type="button" value="우편번호검색" id="zipbtn">우편번호 검색</button><br/>
	    		<input type="text" name="addr1" id="addr1" readonly>
	    		<input type="text" name="addr2" id="addr2">
	  		</div>
	  		<div>
	    		강아지 사진 
	    		<input type="file" name="photo" id="photo" accept = "image/*">
	  		</div>
	  		<!-- 등록할 강아지 사진 미리보기 div -->
	  		<img id = "profile" src="/together/user/default_img/default_profile_img.jpg" style="width:200px"/>
	  		<button type="button" id="deleteImage">삭제하기</button>
	  		<div>
	    		강아지 이름
	    		<input type="text" name="dogName" id="dogName" placeholder="강아지의 이름을 입력해주세요.">
	  		</div>
	  		<div>
	    		생일 *
	    		<input type="date" name="bday" id="bday">
	  		</div>
	  		<button type="submit">가입하기</button>
	  		<button onClick="window.location.reload()">새로고침</button>
	  	</form>
  	</div>
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
<%@ include file="../include/footer.jsp" %>