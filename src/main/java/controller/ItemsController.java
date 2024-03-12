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
public class ItemsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ItemsController() {
        super();
        // TODO Auto-generated constructor stub
    }
    //주문페이지를 만들자.
    protected void process(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException { 
    	String cmd = req.getParameter("cmd");
    	System.out.println(cmd);
    	ItemsService itemsService = new ItemsService();
    	// 결제화면-결제전 결제 정보를 보여주자.
    	if(cmd.equals("itemslist")) {
    		//전체 상품 뿌려주기.
    		List<Items> items = itemsService.items_list();
    		req.setAttribute("items", items);
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
    	}else if(cmd.equals("detail")) {
    		String num = req.getParameter("num");
    		Items item = itemsService.detail(num);
    		req.setAttribute("item", item);
    		req.getRequestDispatcher("item/detail.jsp")
    		.forward(req, res);
    	}else if(cmd.equals("delete")) {
    		int num = Integer.parseInt(req.getParameter("item_num"));
    		int result = itemsService.delete(num);
    		System.out.println(result);
		  if(result == 1) {
			  res.sendRedirect("items?cmd=itemslist");
		  }
		  else { Script.back("삭제실패", res); }
		 
    	}
    	
		
    	
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req,res);
	}

}

