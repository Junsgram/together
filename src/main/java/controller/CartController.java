package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.cart.Cart;
import domain.cart.dto.CartSaveReqDto;
import service.CartService;
import util.Script;


@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }
    //장바구니 추가, 장바구니
   protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
	   String cmd = req.getParameter("cmd");
	   System.out.println("cmd값은 :" +cmd);
	   CartService cartService = new CartService();
	   //장바구니추가
	   if(cmd.equals("add_cart")) {
		   
		   //ajax로 보낸 json을 받자
		   BufferedReader br = req.getReader();
		   String data = br.readLine(); //다음줄을 읽어서 data에 저장
		   //json을 자바객체로 받아주기위한 Gson 생성
		   Gson gson = new Gson();
		   //data를 CartSaveReqDto클래스로 만들어라
		   CartSaveReqDto dto = gson.fromJson(data, CartSaveReqDto.class);
		   System.out.println(dto);
		   int result = cartService.add_cart(dto);
		   System.out.println("장바구니등록."+result);
		   if(result==1) {
			   System.out.println("여기진입");
			   Script.alertMsg("장바구니에 등록되었습니다.","<%=request.getContextPath()%>/items?cmd=itemlist", res);
		   }else {
			   Script.alertMsg("장바구니에 등록 실패.","<%=request.getContextPath()%>/items?cmd=itemlist", res);
		   }
	   }
	   //장바구니 버튼 눌렀을때
	   else if(cmd.equals("in_cart")) {
		   System.out.println("장바구니 진입");
		   List<Cart> cart =cartService.cart_list();
		   System.out.println(cart);
		   req.setAttribute("carts", cart);
		   req.getRequestDispatcher("item/cart.jsp")
		   .forward(req,res);
	   }else if(cmd.equals("delete")) {
		   System.out.println("장바구니 단일 삭제");
		   String id = req.getParameter("");
	   }
		   
			   
   }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req,res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req,res);
	}

}
