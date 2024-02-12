package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.board.MainDAO;
import domain.board.House.Main.CommunityMainDTO;
import domain.board.House.Main.DiaryMainDTO;
import domain.board.House.Main.HouseMainDTO;


@WebServlet("/main")
public class MainPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//메인페이지 값 출력하기
    	String cmd = request.getParameter("cmd");
    	MainDAO dao = new MainDAO();
    	if(cmd.equals("page")) {
    		System.out.println("메인페이지 접속");
    		List<DiaryMainDTO> diary = dao.diary();
    		List<HouseMainDTO> house= dao.house();
    		List<CommunityMainDTO> community = dao.community();
    		
    		request.setAttribute("diary", diary);
    		request.setAttribute("community", community);
    		request.setAttribute("house", house);
    		System.out.println("메인페이지 데이터 전송");
    		request.getRequestDispatcher("/main.jsp")
    		.forward(request,response);

    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
