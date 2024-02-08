package domain.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import domain.cart.dto.CartSaveReqDto;

public class CartDao {
	Connection conn = DBConnection.getConnection();
	public int insert(CartSaveReqDto dto) {
		int result = 0;
		String query = "insert into item_cart(num, id, title,price,order_quantity,ofile)"
						+" values((select COALESCE(MAX(num), 0) from item_cart)+1,?,?,?,?,?)";
		//Coalese(MAX(num),0) -> coalesece함수는 차례차례 인수를 검사하면서 null이 아닌 첫번쨰 인수를 반환한다.
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setInt(3, dto.getPrice());
			psmt.setInt(4, dto.getOrder_quantity());
			psmt.setString(5, dto.getOfile());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public List<Cart> findAll(){
		List<Cart> carts = new ArrayList<Cart>();
		String query = "select * from item_cart";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Cart cart = new Cart();
				cart = Cart.builder()
						.num(rs.getInt("num"))
						.id(rs.getString("id"))
						.title(rs.getString("title"))
						.price(rs.getInt("price"))
						.ofile(rs.getString("ofile"))
						.order_quantity(rs.getInt("order_quantity"))
						.build();
				carts.add(cart);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.close(conn, stmt, rs);
		}
	return carts;
		
	}
}
