package service;

import java.util.List;

import domain.cart.Cart;
import domain.cart.CartDao;
import domain.cart.dto.CartSaveReqDto;

public class CartService {
	private CartDao cartDao;
	//생성자에 객체생성 정의
	public CartService() {
		this.cartDao = new CartDao();
	}
	
	public int add_cart(CartSaveReqDto dto) {
		int result = cartDao.insert(dto);
		return result;
	}
	public List<Cart> cart_list(){
		return cartDao.findAll();
	}

}
