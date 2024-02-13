package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import service.HouseCommentService;
import service.HouseService;
import util.Script;
import domain.board.House.DTO.ListReqDTO;
import domain.board.House.DTO.ViewReqDTO;
import domain.board.House.DTO.WriteReqDTO;
import domain.user.User;
import domain.Jcomment.HouseFindRespDTO;
import domain.board.House.DTO.EditReqDTO;


@WebServlet("/house")
public class HouseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HouseController() {
        super();
    }

    
    //process 메소드를 통해 get,post방식 처리
    protected void process (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String cmd = req.getParameter("cmd");
    	System.out.println(cmd);
    	
    	//session 저장 객체 생성
    	HttpSession session = req.getSession();
    	
    	//서비스 객체 생성
    	HouseService hs = new HouseService();
    
    	//게시글 등록 시 게시판 페이지로 이동
    	if(cmd.equals("write")) {
    		//filter로 페이지 close진행 예정으로 내부페이지 이동 가능한 getRequestDispatcher로 진행
    		if(session.getAttribute("principal") != null) {
    		req.getRequestDispatcher("/house/HouseWrite.jsp")
    		.forward(req, res);
    		}
    		else {
    			req.getRequestDispatcher("user/loginForm.jsp")
    			.forward(req, res);
    		}
    	}
    	
    	
    	//게시글 등록 post 요청
    	else if(cmd.equals("write_process")) {
    		System.out.println("하우스 등록 프로세스 접근");
    		//사진 객체 생성
    		//저장경로 getServletContext()가 application을 호출
    		String savePoint = req.getServletContext().getRealPath("house/img");
    		//사진크기
    		int maxSize = 5 * 1024 * 1024; 
    		//객체
    		MultipartRequest mr = new MultipartRequest(req,savePoint,maxSize,"utf-8");
    		String fileName = mr.getFilesystemName("images");
    		String exe = fileName.substring(fileName.lastIndexOf("."));
    		String now = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
    		String newFileName = now + exe;
    		
    		File ofile = new File(savePoint + File.separator + fileName); 
    		File nfile = new File(savePoint + File.separator + newFileName);
    		ofile.renameTo(nfile);
    		String path = ofile.getPath();
    		System.out.println(path);
    		
    		//parameter 값 받기
    		String houseName = mr.getParameter("houseName");
    		String scontent = mr.getParameter("scontent");
    		String lcontent = mr.getParameter("lcontent");
    		String userid = mr.getParameter("userid");
    		WriteReqDTO dto = new WriteReqDTO();
    		dto.setHouseName(houseName);
    		dto.setScontent(scontent);
    		dto.setLcontent(lcontent);
    		dto.setOfile(newFileName);
    		dto.setId(userid);
    		
    		//System.out.println(session.getAttribute("id"));
    		//insert구문으로 1행 추가 int값으로 리턴
    		int result = hs.regihouse(dto);
    		if(result == 1) {
    			int rownum = hs.rownum();
    			System.out.println("등록완료");
    			req.getRequestDispatcher("house?cmd=view&num="+rownum)
    			.forward(req, res);
    		}
    		else {
    			System.out.println("등록실패");
    		}
    	}
    	
    	//목록 페이지 이동 및 프로세스
    	else if(cmd.equals("list")) {
    		System.out.println("리스트 프로세스 접근");
    		int page  = Integer.parseInt(req.getParameter("page"));
    		req.setAttribute("page", page);
    		List<ListReqDTO> lists = hs.list(page);
    		int lastPage = (hs.lastPage()-1)/8;
    		req.setAttribute("lists",lists);
    		req.setAttribute("lastPage", lastPage);
    		req.getRequestDispatcher("house/HouseList.jsp")
    		.forward(req, res);
    	}
    	
  
    	//상세보기 페이지
    	else if(cmd.equals("view")) {
    		int num = Integer.parseInt(req.getParameter("num"));
    		ViewReqDTO views = hs.detail(num);
    		//조회수 증가 로직
    		if(session.getAttribute("principal") != null) {
    			User id = (User) session.getAttribute("principal");
    			if(!id.getId().equals(views.getId())) {
        			System.out.println(id.getId() +"," + views.getId());
        			views.setViews(hs.visitUpdate(num));
        		}
    		}
    		//댓글 리스트
    		HouseCommentService hcs = new HouseCommentService();
    		List<HouseFindRespDTO> comments = hcs.list(num);
    		if(comments.size() > 0) {
    			req.setAttribute("comments", comments);
    		}
    		req.setAttribute("views",views);
    		req.getRequestDispatcher("house/HouseView.jsp")
    		.forward(req,res);
    	}
    	
    	//수정하기 페이지 및 프로세스
    	else if(cmd.equals("edit")) {
    		System.out.println("수정페이지이동");
    		int num = Integer.parseInt(req.getParameter("num"));
    		EditReqDTO edit = hs.edit(num);
    		req.setAttribute("edit", edit);
    		req.getRequestDispatcher("house/HouseEdit.jsp")
    		.forward(req,res);
    	}
    	else if(cmd.equals("edit_process")) {
    		System.out.println("수정프로세스이동");
    		//이미지 업로드 부분 
    		//기존 이미지 값 변수에 할당
    	
    		String savePoint = req.getServletContext().getRealPath("house/img");
    		int maxPointSize = 5 * 1024 * 1024;
    		MultipartRequest mr = new MultipartRequest(req,savePoint,maxPointSize,"utf-8");
    		String newFileName = null;
    		String change_img = mr.getParameter("change_img");
    		
    		String ofile = mr.getFilesystemName("ofile");
    		if (ofile == null) {
    			newFileName = change_img;
    		}
    		else {
    			File delete_img = new File(savePoint + File.separator + change_img);
        		if(delete_img.exists()) {
        			delete_img.delete();
        		}

        		String exe = ofile.substring(ofile.lastIndexOf("."));
        		String now = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
        		newFileName = now + exe;
        		File oldFile = new File(savePoint + File.separator + ofile);
        		System.out.println(oldFile);
        		File newFile = new File(savePoint + File.separator + newFileName);
        		System.out.println(newFile);
        		oldFile.renameTo(newFile);
    		}

    		int num = Integer.parseInt(mr.getParameter("num"));
    		System.out.print(num);
    		
    		String houseName = mr.getParameter("houseName");
    		String scontent = mr.getParameter("scontent");
    		String lcontent = mr.getParameter("lcontent");
    		
    		ViewReqDTO view = new ViewReqDTO();
    		view.setNum(num);
    		view.setHouseName(houseName);
    		view.setScontent(scontent);
    		view.setLcontent(lcontent);
    		view.setOfile(newFileName);
    		int result = hs.edit_process(view);
    		if(result == 1) {
    			System.out.println("수정완료");
    			req.getRequestDispatcher("house?cmd=view&num="+view.getNum())
    			.forward(req, res);
    		}
    	}
    	
    	//게시글 삭제 진행
    	else if(cmd.equals("delete")) {
    		System.out.println("삭제프로세스");
    		int num = Integer.parseInt(req.getParameter("num"));
    		int result = hs.delete(num);
    		if(result == 1) {
    			req.getRequestDispatcher("house?cmd=list&page=0")
    			.forward(req, res);
    		}
    	}

    	
    	
    	
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

}
