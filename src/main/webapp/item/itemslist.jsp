<%@page import="domain.items.Items"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 아임포트(결제시스템) sdk라이브러리 다운 -->
<!-- 결제api는 전부 js로 설정됩니다. 향후 js파일로 뺴서 해봐야합니다. -->
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
	function addcart(){
		//히든으로 아이템id를 받음.
		let itemId= $("#item_id").val();
		
		//네트워크요청
		$.ajax({
			type:"post",
			url:"/together/items?cmd=add_cart",
			data:itemId,
			contentType:"text/plain;charset=utf-8",
			dataType:"text"
		}).done(function(data){
			
			
		})
	}
		
	   

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

<%
	List<Items> items = (List<Items>) request.getAttribute("items");
%>
<!--헤더  -->

<h2>아이템목록</h2>
<br/>
<a href="<%=request.getContextPath()%>/items?cmd=save" class="btn btn-primary">상품등록</a>
<a href="<%=request.getContextPath()%>/items?cmd=cart" class="btn btn-primary">장바구니</a>
<br/>
<c:forEach var = "i" items = "${items}">
	<div class="card">
	  <div class="card-body">
	  <h3>${i.title}</h3>
	  <p>${i.scontent}</p>
	  <p>${i.price}</p>
	  <p>${i.likes}</p>
	  <p>${i.stars}</p>
	  <p>${i.regidate}</p>
	  <p>이미지 : <img src="./uploading/${i.ofile}" /></p>
	  <a href="<%=request.getContextPath()%>/items?cmd=detail&id=${i.id }" class="btn btn-primary">상세보기</a>
	  <form>
	  <p><input type="hidden" name="item_id" id="item_id" value="${i.id}"></p>
	  <button type="button" onclick="addcart()"  class="btn btn-primary">장바구니 담기</button>
	  </form>
	  <button type="button" id="pay"  class="btn btn-primary" onclick="requestPay()">결제</button>
	  <form action="<%=request.getContextPath()%>/items?cmd=delete" method="post">
	  	<input type="hidden" name="item_num" id="item_num" value="${i.num}">
	  <button type="submit" id="delete"  class="btn btn-primary">삭제</button>
	  
	  
	  </form>

	  </div>
	</div>
<br/>
</c:forEach>

<!--푸터  -->

<%@include file="../include/footer.jsp" %>
