<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<!-- 아임포트(결제시스템) sdk라이브러리 다운 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script>
var IMP = window.IMP;
//IMP변수 상점id로 초기화
IMP.init("imp38155525");

var today = new Date();   
var hours = today.getHours(); // 시
var minutes = today.getMinutes();  // 분
var seconds = today.getSeconds();  // 초
var milliseconds = today.getMilliseconds();
var makeMerchantUid = hours +  minutes + seconds + milliseconds;
	
   

 function requestPay() {
	 console.log("payin");
    IMP.request_pay(
      { //필수
    	
        pg: "kakaopay",
        pay_method: "card",
        merchant_uid: "IMP"+makeMerchantUid, 
        name: "따끈따끈개밥",
        amount: "1", //요청금액
        //선택
        buyer_email: "Iamport@chai.finance",
        buyer_name: "포트원 기술지원팀",
        buyer_tel: "010-1234-5678",
        buyer_addr: "서울특별시 강남구 삼성동",
        buyer_postcode: "123-456",
       
        // 모바일로 결제시 필수 결제완료후 이동될 endpointURL
        //m_redirect_url: ""
      },
      function (rsp) { //  함수성공시 호출
        // callback
        //rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
        if (rsp.success) {
                console.log(rsp);
            } else {
                console.log(rsp);
            }
});
}
</script>
<div class="container">
	<c:if test="${sessionScope.istodo!=null}">
	<form style="display:inline-block" action="<%=request.getContextPath()%>/items?cmd=delete" method="post">
	<input type="hidden" name ="id" value=${item.id}>
	<button type="submit" class="btn btn-danger">삭제</button>
	</form>
	<button class="btn btn-primary">수정</button>
	</c:if>
	<br />
	<br />
	
	<h3>상품상세보기</h3>
	<br />
	<br />
	<div style="display: flex; justify-content: flex-end; gap: 10px;">
	<form action="<%=request.getContextPath()%>/items?cmd=delete" method="post">
	  	<input type="hidden" name="item_num" id="item_num" value="${item.num}">
	  <button type="submit" id="delete"  class="btn btn-dark">삭제</button>
		</form>
	<button type="button" id="pay"  class="btn btn-dark" onclick="requestPay()">결제</button>
	</div>
	<h3 class="m-2">
		<b>${item.title}</b>
	</h3>
	<hr />
	<div class="m-2" style="display:flex;">
		<div style="width:50%"> 사진 : <i><img src="item/img/${item.ofile}" /></i></div>
	</div>
	<hr />
	<div class="m-2" style="display:flex;">
		<div style="width:50%"> 가격 : <i>${item.price}</i></div>
		<div style="width:50%"> 분류 : <i>${item.scontent}</i></div>
	</div>
	<hr />
	<div class="form-group">
		<div class="m-2">${item.lcontent}</div>
	</div>

	<hr />



<%@include file="../include/footer.jsp" %>