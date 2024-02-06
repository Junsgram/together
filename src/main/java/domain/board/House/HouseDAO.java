package domain.board.House;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import domain.board.House.DTO.EditReqDTO;
import domain.board.House.DTO.ListReqDTO;
import domain.board.House.DTO.ViewReqDTO;
import domain.board.House.DTO.WriteReqDTO;

public class HouseDAO {

	//method
	//게시글 등록
	public int regihouse(WriteReqDTO dto) {
		int result = 0;
		//1.오라클 접속
		Connection con = DBConnection.getConnection();
		//2.쿼리문 작성 및 객체 생성
		String sql = "insert into house(num,housename,id,scontent,lcontent,ofile)"
				+ " values(together_house_seq.nextval, ?,?,?,?,?)";
		//동적쿼리문
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1,dto.getHouseName());
			psmt.setString(2,dto.getId());
			psmt.setString(3,dto.getScontent());
			psmt.setString(4,dto.getLcontent());
			psmt.setString(5,dto.getOfile());
			//3.쿼리 실행
			result = psmt.executeUpdate();
//			if(result == 1) {
////				//String query = "select together_house_seq.currval from dual";
////				//Statement stmt = con.createStatement();
//				//rs = stmt.executeQuery(sql);
//				//if(rs.next()) {
//				}
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null && psmt != null) {
				DBConnection.close(con,psmt);
			}
		}
		return result;
	}
	//목록보기 로직
	public List<ListReqDTO> list(int page) {
		List<ListReqDTO> lists = new ArrayList<ListReqDTO>();
		//DB연결
		Connection con = DBConnection.getConnection();
		//쿼리 작성 및 객체 생성
		String sql = "select * from ("
				+ " select td.*, rownum as rnum from (select * from house order by num desc) td)"
				+ " where rnum between ? and ? order by num desc"; 
		Statement stmt = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int start = 5*page +1; 
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2,start+4);
			//실행
			rs = psmt.executeQuery();
			while(rs.next()) {
				ListReqDTO dto = new ListReqDTO();
				dto = ListReqDTO.builder()
						.num(rs.getInt("num"))
						.id(rs.getString("id"))
						.houseName(rs.getString("HouseName"))
						.scontent(rs.getString("Scontent"))
						.lcontent(rs.getString("Lcontent"))
						.address(rs.getString("Address"))
						.likes(rs.getInt("Likes"))
						.views(rs.getInt("Views"))
						.stars(rs.getInt("Stars"))
						.regidate(rs.getDate("Regidate"))
						.Ofile(rs.getString("ofile"))
						.build();
				lists.add(dto);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null && psmt != null && rs != null) {
				DBConnection.close(con, psmt, rs);
			}
		}
		return lists;
	}
	//상세보기 로직
	public ViewReqDTO detail(int num) {
		ViewReqDTO house = new ViewReqDTO();
		//DB연결
		Connection con = DBConnection.getConnection();
		//쿼리 작성 및 객체 생성
		String sql = "select * from house where num=?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1,num);
			//실행
			rs = psmt.executeQuery();
			if(rs.next()) {
				house = ViewReqDTO.builder()
						.num(rs.getInt("num"))
						.id(rs.getString("id"))
						.houseName(rs.getString("houseName"))
						.address(rs.getString("address"))
						.scontent(rs.getString("scontent"))
						.lcontent(rs.getString("lcontent"))
						.likes(rs.getInt("likes"))
						.views(rs.getInt("views"))
						.stars(rs.getInt("stars"))
						.regidate(rs.getDate("regidate"))
						.ofile(rs.getString("ofile"))
						.build();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null && psmt != null && rs != null) {
				DBConnection.close(con, psmt, rs);
			}
		}
		return house;
	}
	
	//마지막 페이지
	public int lastPage() {
		int result = 0;
		Connection con = DBConnection.getConnection();
		String sql = "select count(*) from house";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null && stmt != null && rs != null) {
				DBConnection.close(con, stmt, rs);
			}
		}
		return result;
	}
	
	//작성 후 상세페이지로 이동
	public ViewReqDTO show(String id) {
		ViewReqDTO show = new ViewReqDTO();
		//DB연결
		Connection con = DBConnection.getConnection();
		//쿼리 작성
		String sql = "select * from house where id = ?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//객체 생성
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			//실행
			rs = psmt.executeQuery();
			if(rs.next()) {
					show =  ViewReqDTO.builder()
							.num(rs.getInt("num"))
							.id(rs.getString("id"))
							.houseName(rs.getString("houseName"))
							.address(rs.getString("address"))
							.scontent(rs.getString("scontent"))
							.lcontent(rs.getString("lcontent"))
							.likes(rs.getInt("likes"))
							.views(rs.getInt("views"))
							.stars(rs.getInt("stars"))
							.regidate(rs.getDate("regidate"))
							.ofile(rs.getString("ofile"))
							.build();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return show;
	}
	
	//수정 페이지 이동 및 프로세스
	public EditReqDTO edit(int num) {
		EditReqDTO edit = new EditReqDTO();
		//DB연결
		Connection con = DBConnection.getConnection();
		//쿼리문 작성 및 객체 생성
		String sql = "select * from house where num = ?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, num);
			//실행
			rs = psmt.executeQuery();
			if(rs.next()) {
				edit.setNum(rs.getInt("num"));
				edit.setHouseName(rs.getString("houseName"));
				edit.setScontent(rs.getString("scontent"));
				edit.setLcontent(rs.getString("lcontent"));
				edit.setOfile(rs.getString("ofile"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null && psmt != null && rs != null) {
				DBConnection.close(con, psmt, rs);
			}
		}
		return edit;
	}
	public int edit_process(ViewReqDTO dto) {
		int result = 0;
		//DB연결
		Connection con = DBConnection.getConnection();
		//쿼리문 작성 및 객체 생성
		String sql = "update house set houseName = ?, scontent = ?, lcontent = ?, ofile = ? "
				+ " where num = ?";
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getHouseName());
			psmt.setString(2, dto.getScontent());
			psmt.setString(3, dto.getLcontent());
			psmt.setString(4, dto.getOfile());
			psmt.setInt(5, dto.getNum());
			//쿼리 실행
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	

	
	
}
