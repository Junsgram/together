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

import domain.Jcomment.CommonRespDTO;
import domain.Jcomment.HouseFindRespDTO;
import domain.Jcomment.HouseSaveReqDTO;
import service.HouseCommentService;


@WebServlet("/housecomment")
public class HouseCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HouseCommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//cmd Parameter값 할당 및 출력
    	String cmd = request.getParameter("cmd");
    	
    	//서비스 객체 생성
    	HouseCommentService comService = new HouseCommentService();
    	response.setCharacterEncoding("utf-8");
    	//댓글프로세스
    	if(cmd.equals("save")) {
    		//외부 데이터 자바데이터로 읽어오기
    		BufferedReader br = request.getReader();
    		String data = br.readLine();
    		System.out.println(data);
    		//json을 자바 객체로 변경
    		Gson gson = new Gson();
    		HouseSaveReqDTO dto = gson.fromJson(data, HouseSaveReqDTO.class);
    		System.out.println(dto);
    		int result = comService.commentReg(dto);
    		System.out.println("컨트롤단 result 값 : " + result);
    		//응답 객체 생성
    		CommonRespDTO<HouseFindRespDTO> respDTO = new CommonRespDTO<HouseFindRespDTO>();
    		if(result > 0) {
    			HouseFindRespDTO comDTO = comService.detailComment(result);
    			System.out.println(comDTO);
    			respDTO.setStatusCode(1);
    			respDTO.setData(comDTO);
    		}
    		else {
    			respDTO.setStatusCode(0);
    		}
    		PrintWriter out = response.getWriter();
    		out.println(gson.toJson(respDTO));
    		out.flush();
    		out.close();
    	}
    	else if(cmd.equals("delete")) {
    		int num = Integer.parseInt(request.getParameter("num"));
    		int result = comService.delete(num);
    		CommonRespDTO commonDTO = new CommonRespDTO<>();
    		commonDTO.setStatusCode(1);
    		//자바 객체를 json으로 변환 
    		Gson gson = new Gson();
    		PrintWriter out = response.getWriter();
    		out.print(gson.toJson(commonDTO));
    		out.flush();
    		out.close();
    	}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

}
