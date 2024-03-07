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
import domain.diaryComment.DiaryCommonRespDTO;
import domain.diaryComment.DiaryFindRespDTO;
import domain.diaryComment.DiarySaveReqDTO;
import service.DiaryCommentService;

@WebServlet("/diarycomment")
public class DiaryCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DiaryCommentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");

		// 서비스 객체 생성
		DiaryCommentService dcs = new DiaryCommentService();
		response.setCharacterEncoding("utf-8");

		if (cmd.equals("save")) {
			// 외부 데이터 자바데이터로 읽어오기
			BufferedReader br = request.getReader();
			String data = br.readLine();
			System.out.println(data);
			// json을 자바 객체로 변경
			Gson gson = new Gson();
			DiarySaveReqDTO dto = gson.fromJson(data, DiarySaveReqDTO.class);
			System.out.println(dto);
			int result = dcs.commentReg(dto);
			System.out.println("컨트롤단 result 값 : " + result);
			// 응답 객체 생성
			DiaryCommonRespDTO<DiaryFindRespDTO> respDTO = new DiaryCommonRespDTO<DiaryFindRespDTO>();
			if (result > 0) {
				DiaryFindRespDTO comDTO = dcs.detailComment(result);
				System.out.println(comDTO);
				respDTO.setStatusCode(1);
				respDTO.setData(comDTO);
			} else {
				respDTO.setStatusCode(0);
			}
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(respDTO));
			out.flush();
			out.close();
		}
    	else if(cmd.equals("delete")) {
    		int num = Integer.parseInt(request.getParameter("num"));
    		int result = dcs.delete(num);
    		DiaryCommonRespDTO commonDTO = new DiaryCommonRespDTO<>();
    		commonDTO.setStatusCode(1);
    		//자바 객체를 json으로 변환 
    		Gson gson = new Gson();
    		PrintWriter out = response.getWriter();
    		out.print(gson.toJson(commonDTO));
    		out.flush();
    		out.close();
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

}
