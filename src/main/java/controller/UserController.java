package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.oreilly.servlet.MultipartRequest;

import domain.user.User;
import domain.user.dto.EditReqDto;
import domain.user.dto.JoinReqDto;
import domain.user.dto.LoginReqDto;
import service.UserService;
import util.Script;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// cmd값 받기
		String cmd = req.getParameter("cmd");
		UserService userService = new UserService();

		// 로그인 페이지 요청
		if (cmd.equals("loginForm")) {
			req.getRequestDispatcher("/user/loginForm.jsp").forward(req, res);
		}
		// 로그인 포스트 요청
		else if (cmd.equals("login")) {
			String userId = req.getParameter("userId");
			String userPass = req.getParameter("userPass");
			String remember = req.getParameter("remember");
			
			System.out.println(userId);
			System.out.println(userPass);
			
			LoginReqDto logDto = new LoginReqDto();
			logDto.setId(userId);
			logDto.setPw2(userPass);
			User user = userService.login(logDto);
			HttpSession session = req.getSession();
			if (user != null) {
				if (remember!=null&&remember.equals("true")) {
					//쿠키 생성 후 쿠키에 값 저장
					Cookie cookie = new Cookie("rememberId", userId);
					//유지 시간 일주일로 설정
					cookie.setMaxAge(60*60*24*7);
					res.addCookie(cookie);
				}else {
					Cookie cookie = new Cookie("rememberId", userId);
					cookie.setMaxAge(0);
					res.addCookie(cookie);
				}
			session.setAttribute("principal", user);
			session.setAttribute("userId", userId);
			System.out.println(session.getAttribute("principal"));
			req.getRequestDispatcher("main?cmd=page").forward(req, res);
			} else {
				Script.back("로그인실패", res);
			}
		}
		// 로그아웃 요청
		else if (cmd.equals("logout")) {
			HttpSession session = req.getSession();
			session.removeAttribute("principal");
			Script.alertMsg("로그아웃 되었습니다.", "main?cmd=page", res);
		}
		// 회원가입 페이지 요청
		else if (cmd.equals("joinForm")) {
			req.getRequestDispatcher("/user/joinForm.jsp").forward(req, res);
		}
		// 아이디 중복 확인 요청 (ajax요청)
		else if (cmd.equals("userIdCheck")) {
			BufferedReader br = req.getReader();
			String userId = br.readLine();
			int result = userService.idCheck(userId);
			PrintWriter out = res.getWriter();
			if (result == 0) {
				out.print("ok");
			} else {
				out.print("fail");
			}
			out.flush();
			out.close();
		}
		// 회원가입 포스트 요청
		else if (cmd.equals("join")) {
			System.out.println("회원가입요청");
			// 입력된 데이터 받기
			String saveDirectory = req.getServletContext().getRealPath("user/profile_img");
			System.out.println(saveDirectory);
			
			int maxProfileSize = 1024*1000; //MultipartRequest 객체 생성
			//enctype="multipart"인 요청의 값 받아오기할 때는 multipartRequest객체로 해야한다.
			MultipartRequest multiReq = new MultipartRequest(req, saveDirectory, maxProfileSize, "utf-8"); 
			 
			String newFileName = "default_profile_img.jpg";
			String fileName =multiReq.getFilesystemName("photo"); 
			
			if (fileName!=null) {
				String exe =fileName.substring(fileName.lastIndexOf(".")); 
				//서버 컴퓨터에 저장될 사진의 파일 이름 
				String now = new SimpleDateFormat("yyyyMMdd_Hmss").format(new Date()); 
				newFileName = now+exe; //파일 이름 변경 
				
			}
			File oldFile = new File(saveDirectory+File.separator+fileName); 
			File newFile = new File(saveDirectory+File.separator+newFileName); 
			oldFile.renameTo(newFile);

			 //다른 input값 받기 
			 String userId = multiReq.getParameter("userId"); 
			 String userPass1 = multiReq.getParameter("userPass1"); 
			 String userPass2 = multiReq.getParameter("userPass2"); 
			 String email = multiReq.getParameter("email"); 
			 String call1 = multiReq.getParameter("call1"); 
			 String call2 = multiReq.getParameter("call2"); 
			 String call3 = multiReq.getParameter("call3"); 
			 String zipcode = multiReq.getParameter("zipcode"); 
			 String addr1 = multiReq.getParameter("addr1");
			 String addr2 = multiReq.getParameter("addr2"); 
			 String bday = multiReq.getParameter("bday");
			 String dogName = multiReq.getParameter("dogName");
			  
			 //JoinReqDto객체 생성하기 
			 JoinReqDto joinDto = new JoinReqDto();
			 joinDto.setId(userId); 
			 joinDto.setPw1(userPass1); 
			 joinDto.setPw2(userPass2);
			 joinDto.setEmail(email); 
			 joinDto.setCall1(call1); 
			 joinDto.setCall2(call2);
			 joinDto.setCall3(call3); 
			 joinDto.setZipcode(zipcode);
			 joinDto.setAddr1(addr1);
			 joinDto.setAddr2(addr2);
			 joinDto.setOfile(newFileName); 
			 joinDto.setBirthday(bday);
			 joinDto.setDogname(dogName); 
			 
			 int result = userService.join(joinDto); 
			 if (result==1) { 
				 Script.alertMsg("회원 가입이 완료되었습니다.", "main?cmd=page", res);
			 }else { 
				 Script.back("회원가입에 실패했습니다.", res); 
			 }
		}
		//회원정보 수정 페이지 요청
		else if (cmd.equals("editForm")) {
			String userId = req.getParameter("userId");
			User user = userService.editValue(userId);
			HttpSession session = req.getSession();
			if (user!=null) {
				session.setAttribute("principal", user);
			}
			req.getRequestDispatcher("/user/editForm.jsp").forward(req, res);
		}
		
		//회원정보 수정 post 요청
		else if(cmd.equals("edit")) {
			/* System.out.println("회원정보수정요청"); */
			// 입력된 데이터 받기
			String saveDirectory = req.getServletContext().getRealPath("user/profile_img");
			/* System.out.println(saveDirectory); */
			
			int maxProfileSize = 1024*1000; //MultipartRequest 객체 생성
			//enctype="multipart"인 요청의 값 받아오기할 때는 multipartRequest객체로 해야한다.
			MultipartRequest multiReq = new MultipartRequest(req, saveDirectory, maxProfileSize, "utf-8"); 
			//기존 프로필 사진의 파일 이름 
			/*
			 * String originFileName = multiReq.getParameter("originPic");
			 * System.out.println("aaaaaaa" + originFileName);
			 */
			String newFileName="";
			//바꾼 프로필 사진의 파일 이름
			String changedFileName =multiReq.getFilesystemName("photo");
			System.out.println(changedFileName);
			if(changedFileName==null) {
				newFileName= multiReq.getParameter("originPic");
			}
			else {
				String change_img = multiReq.getParameter("originPic");
	    		File delete_img = new File(saveDirectory + File.separator + change_img);
	    		if(delete_img.exists()) {
	    			delete_img.delete();
	    		}
				String exe =changedFileName.substring(changedFileName.lastIndexOf(".")); 
				//서버 컴퓨터에 저장될 사진의 파일 이름 
				String now = new SimpleDateFormat("yyyyMMdd_Hmss").format(new Date()); 
				newFileName = now+exe; //파일 이름 변경 
				
				File oldFile = new File(saveDirectory+File.separator+changedFileName); 
				File newFile = new File(saveDirectory+File.separator+newFileName); 
				oldFile.renameTo(newFile);
			}
			
			System.out.println(newFileName);
			
			
			 //다른 input값 받기  
			 String userId = multiReq.getParameter("userId");
			 String userPass1 = multiReq.getParameter("userPass1"); 
			 String userPass2 = multiReq.getParameter("userPass2"); 
			 String email = multiReq.getParameter("email"); 
			 String call1 = multiReq.getParameter("call1"); 
			 String call2 = multiReq.getParameter("call2"); 
			 String call3 = multiReq.getParameter("call3"); 
			 String zipcode = multiReq.getParameter("zipcode"); 
			 String addr1 = multiReq.getParameter("addr1");
			 String addr2 = multiReq.getParameter("addr2"); 
			 String bday = multiReq.getParameter("bday");
			 String dogName = multiReq.getParameter("dogName");
			  
			 //EditReqDto객체 생성하기 
			 EditReqDto editDto = new EditReqDto();
			
			 editDto.setId(userId);
			 editDto.setPw1(userPass1); 
			 editDto.setPw2(userPass2);
			 editDto.setEmail(email); 
			 editDto.setCall1(call1); 
			 editDto.setCall2(call2);
			 editDto.setCall3(call3); 
			 editDto.setZipcode(zipcode);
			 editDto.setAddr1(addr1);
			 editDto.setAddr2(addr2);
			 editDto.setOfile(newFileName); 
			 editDto.setBirthday(bday);
			 editDto.setDogname(dogName); 
			 
			 int result = userService.edit(editDto); 
			
			 if (result==1) { 
				 Script.alertMsg("회원 정보 수정이 완료되었습니다.", "main?cmd=page", res);
				 
			 }else { 
				 Script.back("회원 정보 수정에 실패했습니다.", res); 
			 }
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
