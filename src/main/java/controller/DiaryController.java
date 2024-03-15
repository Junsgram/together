package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import domain.board.Diary.DTO.DListReqDTO;
import domain.board.Diary.DTO.DeditReqDTO;
import domain.board.Diary.DTO.DviewReqDTO;
import domain.board.Diary.DTO.DwriteReqDTO;
import domain.board.House.DTO.ViewReqDTO;
import domain.diaryComment.DiaryFindRespDTO;
import domain.user.User;
import service.DiaryCommentService;
import service.DiaryService;


@WebServlet("/diary")
public class DiaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DiaryController() {
        super();
    }

    
    //process 메소드를 통해 get,post방식 처리
    protected void process (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String cmd = req.getParameter("cmd");
    	System.out.println(cmd);
    	
    	//session 저장 객체 생성
    	HttpSession session = req.getSession();
    
    	
    	//서비스 객체 생성
    	DiaryService ds = new DiaryService();
    
    	//게시글 페이지 및 프로세스
    	if(cmd.equals("write")) {
    		if(session.getAttribute("principal") != null) {
    		req.getRequestDispatcher("/diary/DiaryWrite.jsp")
    		.forward(req, res);
    		}
    		else {
    			req.getRequestDispatcher("user/loginForm.jsp")
    			.forward(req, res);
    		}
    	}
    	
    	
    	//게시글 등록 post 요청
    	else if(cmd.equals("write_process")) {
    		System.out.println("다이어리 등록 프로세스 접근");
    		//사진 객체 생성
    		//저장경로 getServletContext()가 application을 호출
    		String savePoint = req.getServletContext().getRealPath("diary/img");
    		System.out.println(savePoint);
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
    		String title = mr.getParameter("title");
    		String scontent = mr.getParameter("scontent");
    		String lcontent = mr.getParameter("lcontent");
    		String userid = mr.getParameter("userid");
    		DwriteReqDTO dto = new DwriteReqDTO();
    		dto.setTitle(title);
    		dto.setScontent(scontent);
    		dto.setLcontent(lcontent);
    		dto.setOfile(newFileName);
    		dto.setId(userid);
    		
    		//System.out.println(session.getAttribute("id"));
    		//insert구문으로 1행 추가 int값으로 리턴
    		int result = ds.regidiary(dto);
    		if(result == 1) {
    			System.out.println("등록완료");
    			int detailNum = ds.detailNum();
    			req.getRequestDispatcher("diary?cmd=view&num="+detailNum)
    			.forward(req, res);
    		}
    		else {
    			System.out.println("등록실패");
    		}
    	}
    	
    	//목록 페이지 이동 및 프로세스
    	else if(cmd.equals("list")) {
    		int page  = Integer.parseInt(req.getParameter("page"));
    		req.setAttribute("page", page);
    		List<DListReqDTO> lists = ds.list(page);
    		int lastPage = (ds.lastPage()-1)/8;
    		req.setAttribute("lists",lists);
    		req.setAttribute("lastPage", lastPage);
    		req.getRequestDispatcher("diary/DiaryList.jsp")
    		.forward(req, res);
    	}
    	
    	//상세보기 페이지 이동 및 프로세스
    	else if(cmd.equals("view")) {
    		int num = Integer.parseInt(req.getParameter("num"));
    		User id = (User) session.getAttribute("principal");
    		DviewReqDTO diaryDTO = ds.views(num);
    		if(id != null) {
    			if(!id.getId().equals(diaryDTO.getId())) {
    				ds.visitcount(num);
    			}
    		}
    		//댓글 리스트
    		DiaryCommentService dcs = new DiaryCommentService();
    		List<DiaryFindRespDTO> comments = dcs.list(num);
    		if(comments.size() > 0) {
    			req.setAttribute("comments", comments);
    		}
    		DviewReqDTO dto = ds.views(num);
    		req.setAttribute("diaryViews",dto);
    		req.getRequestDispatcher("diary/DiaryView.jsp")
    		.forward(req, res);
    	}
    	
    	//수정하기 페이지 이동 및 프로세스
    	else if(cmd.equals("edit")) {
    		int num = Integer.parseInt(req.getParameter("num"));
    		DeditReqDTO dto = ds.edit(num);
    		req.setAttribute("edit",dto);
    		req.getRequestDispatcher("diary/DiaryEdit.jsp")
    		.forward(req, res);
    	}
    	
    	else if(cmd.equals("edit_process")) {
    		//이미지 업로드 부분 
    		//기존 이미지 값 변수에 할당
    	
    		String savePoint = req.getServletContext().getRealPath("diary/img");
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
    		
    		String title = mr.getParameter("title");
    		String scontent = mr.getParameter("scontent");
    		String lcontent = mr.getParameter("lcontent");
    		
    		DviewReqDTO view = new DviewReqDTO();
    		view.setNum(num);
    		view.setTitle(title);
    		view.setScontent(scontent);
    		view.setLcontent(lcontent);
    		view.setOfile(newFileName);
    		int result = ds.edit_process(view);
    		if(result == 1) {
    			System.out.println("수정완료");
    			req.getRequestDispatcher("diary?cmd=view&num="+view.getNum())
    			.forward(req, res);
    		}
    	}
    	
    	//삭제 프로세스
    	else if(cmd.equals("delete")) {
    		int num = Integer.parseInt(req.getParameter("num"));
    		int result = ds.delete(num);
    		if(result == 1 ) {
    			System.out.println("삭제 완료");
    			req.getRequestDispatcher("diary?cmd=list&page=0")
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
