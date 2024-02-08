package domain.items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import domain.items.dto.SaveReqDto;

public class ItemsDao {

	//itemslist뿌리기,상세조회하기, 추가하기, 삭제하기, 조회하기
	//itemslist뿌리기
	
	public List<Items> findAll(){
	List<Items> items = new ArrayList<Items>();
	Connection conn = DBConnection.getConnection(); 
	System.out.println(items);
	String query = "select * from items";
	ResultSet rs = null;
	Statement stmt = null;
	try {
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(query);
		System.out.println("반복문 진입전");
		
		while(rs.next()) {
			Items item = new Items();
			item = Items.builder()
					.num(rs.getInt("num"))
					.id(rs.getString("id"))
					.title(rs.getString("title"))
					.scontent(rs.getString("scontent"))
					.lcontent(rs.getString("lcontent"))
					.price(rs.getInt("price"))
					.likes(rs.getInt("likes"))
					.views(rs.getInt("views"))
					.stars(rs.getInt("stars"))
					.ofile(rs.getString("ofile"))
					.regidate(rs.getDate("regidate"))
					.build();//객체리턴이 리턴이 안됨.
			System.out.println(item);
			//리스트에 객체 추가
			items.add(item);
			System.out.println(items);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		DBConnection.close(conn, stmt, rs);
	}
	
	return items;
	}
	public int insert(SaveReqDto dto) {
		int result =0;
		String query = "insert into items(num, id,title, scontent, lcontent,price,ofile,quantity)"
				+ " values(seq_save_items.nextval,'테스트용아이디',?,?,?,?,?,?)";
		PreparedStatement psmt=null;
		Connection conn = DBConnection.getConnection(); 
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getScontent());
			psmt.setString(3, dto.getLcontent());
			psmt.setInt(4, dto.getPrice());
			psmt.setString(5, dto.getOfile());
			psmt.setInt(6, dto.getQuantity());
			result =psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.close(conn,psmt);
		}
		return result;
	}
	//디테일
	public Items findById(String id) {
		Items item = null;
		String query = "Select * from Items where id=?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = DBConnection.getConnection(); 
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()){
				item = new Items();
				item = Items.builder()
						.num(rs.getInt("num"))
						.id(rs.getString("id"))
						.title(rs.getString("title"))
						.scontent(rs.getString("scontent"))
						.lcontent(rs.getString("lcontent"))
						.price(rs.getInt("price"))
						.likes(rs.getInt("likes"))
						.views(rs.getInt("views"))
						.stars(rs.getInt("stars"))
						.ofile(rs.getString("ofile"))
						.regidate(rs.getDate("regidate"))
						.build();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.close(conn, psmt,rs);
		}
		return item;
	}
	public int deleteById(int id) {
		int result=0;
		String query = "delete from items where num=?";
		PreparedStatement psmt=null;
	Connection conn = DBConnection.getConnection(); 
		try {
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, id);
			System.out.println(result);
			System.out.println(id);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.close(conn, psmt);
		}
		
		return result;
	}
	
	
	
	
	}
