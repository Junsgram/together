	//댓글 들록 시 페이지 이동 없이 댓글 리스트 추가
	function commentSave(userId,housenum) {
		console.log(userId);
		console.log(housenum);
		let data = {
				userId : userId,
				housenum : housenum,
				content : $("#content").val()
		};
		
		$.ajax({
			type : "post",
			url : "/together/housecomment?cmd=save",
			//data의 매개변수는 위에 선언한 data 변수
			data : JSON.stringify(data),
			//JSON 데이터 전송 타입은 application/json
			contentType: "application/json; charset=utf-8",
			//응답 받을 데이터 타입을 json으로 지정하여 객체로 변환 JSON.parse()
			dataType: "json"
		}).done(function(result) {
			console.log(result);
			console.log(result.StatusCode);
			if(result.StatusCode > 0) {
				console.log(result);
				addComment(result.data);
			}
			else {
				alert("댓글 달기 실패");
			}
			$("#content").val("");
		})
	}
	
	//댓글 추가하기
	//data 변수는 아작스로 전달받은 data 변수
	function addComment(data) {
		let item = "<li id= 'reply-"+data.comnum+"' class='media'>"
		 + "<div class='media-body'> "
		 + "<strong class='text-primary'>"+data.userId+"</strong> "
		 + "<p> "+data.content+" </p> "
		 + "<p>" +data.createDate +" </p> "
		 + "</div> <div class='m-2'> "
		 + "<i onclick='deleteComment("+data.comnum+")' class='material-icons'>delete</i>"
		 + "</div> </li>" ;
		$("#reply__list").prepend(item);
	}
	//댓글 삭제하기
	function deleteComment(num) {
		///요청
		console.log($("#reply-"+num));
		$.ajax({
				type: "POST",
				url: "/together/housecomment?cmd=delete&num="+num,
				dataType: "json"
		}).done(function(result) {
			console.log(result);
			if(result.StatusCode == 1) {
				console.log(result);
				//선택한 id를 삭제 (#reply-?)는 li의 id번호
				$("#reply-"+num).remove();
				
			}
			else {
				alert("데이터 삭제 실패");
			}
		})
	}
	
	//좋아요 버튼 로직	
	let liketo = $("#liketo");
	liketo.click(function(){
		if(liketo.hasClass()) {
			liketo.toggleClass("to");
		}
		else{
			liketo.toggleClass("to");
		}
	})

	