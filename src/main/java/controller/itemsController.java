package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.items.Items;
import service.ItemsService;
import util.Script;


@WebServlet("/items")
public class itemsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public itemsController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void process(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException { 
    	String cmd = req.getParameter("cmd");
    	System.out.println(cmd);
    	ItemsService itemsService = new ItemsService();
    	//삭제, 결제화면, 리스트-사진, 장바구니(세션? db? 미리 추가해둬야함.)
    	if(cmd.equals("itemslist")) {
    		//전체 상품 뿌려주기.
    		List<Items> items = itemsService.items_list();
    		req.setAttribute("items", items);
    		System.out.println("aaaaaaaaa");
    		System.out.println(req.getAttribute("items"));
    		req.getRequestDispatcher("item/itemslist.jsp")
    		.forward(req, res);
    	}else if(cmd.equals("save")) {
    		System.out.println("세이브폼진입");
    		req.getRequestDispatcher("item/itemsaveForm.jsp")
    		.forward(req, res);
    	}else if(cmd.equals("saveprocess")) {
    		System.out.println("세이브프로세스 집입");
    		req.getRequestDispatcher("item/uploadProcess.jsp")
    		.forward(req, res);
    	}else if(cmd.equals("cart")) {
    		System.out.println("장바구니 진입");
    		req.getRequestDispatcher("item/cart.jsp")
    		.forward(req, res);
    	}else if(cmd.equals("add_cart")) {
    		System.out.println("장바구니에 추가");
    		//입력스트림생성
    		BufferedReader br = req.getReader();
    		String itemId = br.readLine();
    		Cookie add_item = new Cookie("itemId",itemId);
    		add_item.setMaxAge(60*60*24);// 쿠키 하루 유지
    		System.out.println(add_item);
    		res.addCookie(add_item);
    	}else if(cmd.equals("detail")) {
    		String id = req.getParameter("id");
    		Items item = itemsService.detail(id);
    		req.setAttribute("item", item);
    		req.getRequestDispatcher("item/detail.jsp")
    		.forward(req, res);
    	}else if(cmd.equals("delete")) {
    		String id = req.getParameter("item_id");
    		int result = itemsService.delete(id);
		
		  if(result == 1) { res.sendRedirect(id); }else { System.out.println(""); }
		 
    	}
    	
		
    	
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req,res);
	}

}

