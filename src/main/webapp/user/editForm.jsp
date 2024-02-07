<%@page import="domain.user.User"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
	<h2>회원정보 수정 페이지 입니다.</h2>
	<form action="<%=request.getContextPath()%>/user?cmd=edit" method="post" enctype="multipart/form-data" onsubmit="return passCheck()">
		<div>
			아이디
			<input type="text" name="userId" id="userId" value="${sessionScope.principal.id}" readonly>
		</div>
		<div>
	 		비밀번호 *
	 		<input type="password" name="userPass1" id="userPass1" value="${sessionScope.principal.pw2}" required>
		</div>
		<div>
	 		비밀번호 확인 *
	 		<input type="password" name="userPass2" id="userPass2" value="${sessionScope.principal.pw2}" required>
		</div>
		<div id = "resultDiv"> </div>
		<div>
	 		이메일 *
	 		<input type="email" name="email" id="email" value="${sessionScope.principal.email}" required>
		</div>
		<div>
	 		전화번호 *
	 		<input type="text" name="call1" id="call1" value="${sessionScope.principal.call1}" required>
	 		<input type="text" name="call2" id="call2" value="${sessionScope.principal.call2}" required>
	 		<input type="text" name="call3" id="call3" value="${sessionScope.principal.call3}" required>
		</div>
		<div>
	 		우편번호 *
	 		<input type="text" name="zipcode" id="zipcode" value="${sessionScope.principal.zipcode}" readonly>
			<button type="button" value="우편번호검색" id="zipbtn">우편번호 검색</button><br/>
	 		<input type="text" name="addr1" id="addr1" value="${sessionScope.principal.addr1}" readonly>
	 		<input type="text" name="addr2" id="addr2" value="${sessionScope.principal.addr2}">
		</div>
		<div>
	 		강아지 사진 
	 		<input type="file" name="photo" id="photo" accept = "image/*">
	 		
		</div>
		<!-- 등록할 강아지 사진 미리보기 -->
		<c:choose>
			<c:when test="${sessionScope.principal.ofile == 'default_profile_img.jpg'}">
				<img id = "profile" src="/together/user/default_img/default_profile_img.jpg" style="width:200px"/>
			</c:when>
			<c:otherwise>
				<img id = "profile" src="/together/user/profile_img/${sessionScope.principal.ofile}" style="width:200px"/>
			</c:otherwise>
		</c:choose>
		<button type="button" id="deleteImage">삭제하기</button>
		<div>
	 		강아지 이름
	 		<input type="text" name="dogName" id="dogName" value="${sessionScope.principal.dogname}">
		</div>
		<div>
	 		생일 *
	 		<input type="date" name="bday" id="bday" value="${sessionScope.principal.birthday}">
		</div>
		<button type="submit">수정하기</button>
	</form>
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
	
	
	
	//삭제하기 버튼 누르면 디폴트 이미지 들어가게 해두기
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