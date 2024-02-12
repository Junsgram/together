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

import com.oreilly.servlet.MultipartRequest;

import domain.commu.dto.EditReqDto;
import domain.commu.dto.ListReqDto;
import domain.commu.dto.ViewReqDto;
import domain.commu.dto.WriteReqDto;
import service.commuService;


@WebServlet("/commu")
public class commuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public commuController() {
        super();
    }

    protected void process (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String cmd = req.getParameter("cmd");
    	commuService cs = new commuService();
    	//커뮤니티 글쓰기 페이지 요청
    	if (cmd.equals("write")) {
    		//filter로 페이지 close진행 예정으로 내부페이지 이동 가능한 getRequestDispatcher로 진행
    		req.getRequestDispatcher("/community/community_Write.jsp")
    		.forward(req, res);
    	}
    	//커뮤니티 글쓰기 포스트 요청
    	else if(cmd.equals("write_process")) {
    		System.out.println("등록 프로세스");
    		//저장경로
    		String savePoint = req.getServletContext().getRealPath("community/img");
    		//사진크기
    		int maxSize = 5*1024*1024;
    		//멀티파트리퀘스트 객체 생성
    		MultipartRequest mr = new MultipartRequest(req, savePoint, maxSize, "utf-8");
    		String fileName = mr.getFilesystemName("images");
    		String exe = fileName.substring(fileName.lastIndexOf("."));
    		String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    		String newFileName = now+exe;
    		
    		File ofile = new File(savePoint+File.separator+fileName);
    		File nfile = new File(savePoint+File.separator+newFileName);
    		ofile.renameTo(nfile);
    		//parameter 값 받기
    		String commuName = mr.getParameter("commuName");
    		String scontent = mr.getParameter("scontent");
    		String lcontent = mr.getParameter("lcontent");
    		String id = mr.getParameter("userId");
    		System.out.println(id);
    		WriteReqDto dto = new WriteReqDto();
    		dto.setCommuName(commuName);
    		dto.setScontent(scontent);
    		dto.setLcontent(lcontent);
    		dto.setOfile(newFileName);
    		dto.setId(id);
    		
    		int result = cs.regiCommu(dto);
    		if (result==1) {
    			System.out.println("등록완료");
    			req.getRequestDispatcher("commu?cmd=view&num="+dto.getNum()).forward(req, res);
    		}else {
    			System.out.println("등록실패");
    		}
    	}
    	//목록 페이지 
    	else if(cmd.equals("list")) {
    		System.out.println("리스트프로세스");
    		int page = Integer.parseInt(req.getParameter("page"));
    		req.setAttribute("page", page);
    		List<ListReqDto> lists = cs.list(page);
    		int lastPage = (cs.lastPage()-1)/5;
    		req.setAttribute("lists", lists);
    		req.setAttribute("lastPage", lastPage);
    		req.getRequestDispatcher("community/community_List.jsp").forward(req, res);
    	}
    	//상세보기 페이지
    	else if (cmd.equals("view")) {
    		int num = Integer.parseInt(req.getParameter("num"));
    		ViewReqDto dto = cs.detail(num);
    		req.setAttribute("dto", dto);
    		req.getRequestDispatcher("community/community_View.jsp").forward(req, res);
    	}
    	//수정하기 페이지 요청
    	else if(cmd.equals("edit")) {
    		System.out.println("수정페이지이동");
    		int num = Integer.parseInt(req.getParameter("num"));
    		EditReqDto dto = cs.edit(num);
    		req.setAttribute("dto", dto);
    		req.getRequestDispatcher("community/community_Edit.jsp").forward(req, res);
    	}
    	//수정하기 포스트 요청
    	else if(cmd.equals("edit_process")) {
    		System.out.println("수정프로세스 이동");
    		String savePoint = req.getServletContext().getRealPath("community/img");
    		int maxSize = 5*1024*1024;
    		MultipartRequest mr = new MultipartRequest(req, savePoint, maxSize, "utf-8");
    		String change_img = mr.getParameter("change_img");
    		File delete_img = new File(savePoint+File.separator+change_img);
    		if (delete_img.exists()) {
    			delete_img.delete();
    		}
    		String ofile = mr.getFilesystemName("ofile");
    		String exe = ofile.substring(ofile.lastIndexOf("."));
    		String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    		String newFileName = now+exe;
    		File oldFile = new File(savePoint + File.separator + ofile);
    		File newFile = new File(savePoint + File.separator + newFileName);
    		oldFile.renameTo(newFile);
    		
    		int num = Integer.parseInt(mr.getParameter("num"));
    		String commuName = mr.getParameter("commuName");
    		String scontent = mr.getParameter("scontent");
    		String lcontent = mr.getParameter("lcontent");
    		
    		ViewReqDto dto = new ViewReqDto();
    		dto.setNum(num);
    		dto.setCommuName(commuName);
    		dto.setScontent(scontent);
    		dto.setLcontent(lcontent);
    		dto.setOfile(ofile);
    		
    		int result = cs.edit_process(dto);
    		if (result==1) {
    			System.out.println("수정완료");
    			req.getRequestDispatcher("commu?cmd=view&num="+dto.getNum())
    			.forward(req, res);
    		}
    	}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
