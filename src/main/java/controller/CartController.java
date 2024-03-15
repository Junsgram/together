package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.google.gson.Gson;

import domain.cart.Cart;
import domain.cart.dto.CartSaveReqDto;
import domain.user.User;
import service.CartService;
import util.Script;


@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }
    // 장바구니 갯수 새기
   protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
	   String cmd = req.getParameter("cmd");
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
		   int result = cartService.add_cart(dto);
		   if (result == 1) {
			    // 장바구니 등록 성공
			    res.setContentType("application/json");
			    res.setCharacterEncoding("UTF-8");
			    res.getWriter().write("{\"message\":\"장바구니에 등록되었습니다.\"}");
			    res.getWriter().flush();
			} else {
			    // 장바구니 등록 실패
			    res.setContentType("application/json");
			    res.setCharacterEncoding("UTF-8");
			    res.getWriter().write("{\"message\":\"장바구니 등록에 실패했습니다.\"}");
			    res.getWriter().flush();
			}
	   }
	   else if(cmd.equals("count_cart")) {
		   // 장바구니 리스트의 개수를 가져오는 로직을 구현합니다.
		   	HttpSession session = req.getSession();
		   			   	User id = (User) session.getAttribute("principal");
		   
		    int cartCount = cartService.getCartItemCount(id.getId());

		    res.setContentType("application/json");
		    res.setCharacterEncoding("UTF-8");
		    // 장바구니 리스트의 개수를 JSON 형식으로 응답합니다.
		    res.getWriter().write("{\"count\":" + cartCount + "}");
		    res.getWriter().flush();
	   }
	   //장바구니 버튼 눌렀을때
	   else if(cmd.equals("in_cart")) {
		   System.out.println("장바구니 진입");
		   HttpSession session = req.getSession();
		   User id = (User) session.getAttribute("principal");
		   System.out.println("세션ㅇ 아이디 여기입니다 :" + session);
		   List<Cart> cart =cartService.cart_list(id.getId());
		   System.out.println(cart);
		   req.setAttribute("carts", cart);
		   req.getRequestDispatcher("item/cart.jsp")
		   .forward(req,res);
	   }else if(cmd.equals("delete")) {
		   System.out.println("장바구니 단일 삭제");
		   String id = req.getParameter("cart_id");
		   int num = Integer.parseInt(req.getParameter("cart_num"));
		   System.out.println(id);
		   System.out.println(num);
		   int result = cartService.deleteone(num);
		   if(result == 1) {
			   res.sendRedirect("cart?cmd=in_cart");
		   }
		   else { Script.back("삭제실패", res);}
	   }
		   
			   
   }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req,res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req,res);
	}

}
