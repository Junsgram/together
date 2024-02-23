package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.commuComment.CommonResDto;
import domain.commuComment.dto.FindResDto;
import domain.commuComment.dto.SaveReqDto;
import service.CommuCommentService;

@WebServlet("/commuComment")
public class commuCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public commuCommentController() {
        super();
    }

    protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String cmd = req.getParameter("cmd");
    	//서비스 객체 생성
    	CommuCommentService ccs = new CommuCommentService();
    	res.setCharacterEncoding("utf-8");
    	
    	//댓글 저장하기
    	if (cmd.equals("save")) {
    		BufferedReader br = req.getReader();
    		String data = br.readLine();
    		Gson gson = new Gson();
    		SaveReqDto dto = gson.fromJson(data, SaveReqDto.class);
    		//댓글 등록 성공하면 result 에는 추가된 댓글의 id값, 실패해면 0
    		int result = ccs.save(dto);
    		//응답객체 생성
    		CommonResDto<FindResDto> resDto = new CommonResDto<FindResDto>();
    		if (result>0) {
    			//마지막 댓글 id로 조회해서 comDto 변수에 FindResDto 객체 할당
    			FindResDto comDto = ccs.find(result);
    			System.out.println("comDto : "+comDto);
    			resDto.setStatusCode(1);
    			resDto.setData(comDto);
    		}else {
    			resDto.setStatusCode(0);
    		}
    		//응답객체 전송 ---> 출력 스트림 필요
    		PrintWriter out = res.getWriter();
    		out.print(gson.toJson(resDto));
    		out.flush();
    		out.close();
    	}
    	
    	//댓글 삭제하기
    	else if(cmd.equals("delete")) {
    		int id = Integer.parseInt(req.getParameter("id"));
    		int result = ccs.delete(id);
    		CommonResDto resDto = new CommonResDto<>();
    		resDto.setStatusCode(result);
    		Gson gson = new Gson();
    		PrintWriter out = res.getWriter();
    		out.print(gson.toJson(resDto));
    		out.flush();
    		out.close();
    	}
 
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
