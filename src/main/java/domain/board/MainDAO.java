package domain.board;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import domain.board.House.Main.CommunityMainDTO;
import domain.board.House.Main.DiaryMainDTO;
import domain.board.House.Main.HouseMainDTO;


public class MainDAO {
	//각 테이블의 데이터값 받아오기
	public List<DiaryMainDTO> diary() {
		List<DiaryMainDTO> lists = new ArrayList<DiaryMainDTO>();
		Connection con = DBConnection.getConnection();
		String sql = "select num,title,id,scontent,ofile from diary order by views desc fetch first 4 rows only";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				DiaryMainDTO dto = new DiaryMainDTO();
				dto = DiaryMainDTO.builder()
						.num(rs.getInt("num"))
						.title(rs.getString("title"))
						.id(rs.getString("id"))
						.scontent(rs.getString("scontent"))
						.ofile(rs.getString("ofile"))
						.build();
				lists.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null && stmt != null && rs != null) {
				DBConnection.close(con, stmt,rs);
			}
		}
		return lists;
	}
	public List<CommunityMainDTO> community() {
		List<CommunityMainDTO> lists = new ArrayList<CommunityMainDTO>();
		Connection con = DBConnection.getConnection();
		String sql = "select num,title,id,scontent,ofile from community order by views desc fetch first 4 rows only";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				CommunityMainDTO dto = new CommunityMainDTO();
				dto = CommunityMainDTO.builder()
						.num(rs.getInt("num"))
						.title(rs.getString("title"))
						.id(rs.getString("id"))
						.scontent(rs.getString("scontent"))
						.ofile(rs.getString("ofile"))
						.build();
				lists.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null && stmt != null && rs != null) {
				DBConnection.close(con, stmt,rs);
			}
		}
		return lists;
	}
	public List<HouseMainDTO> house() {
		List<HouseMainDTO> lists = new ArrayList<HouseMainDTO>();
		Connection con = DBConnection.getConnection();
		String sql = "select num,houseName,id,scontent,ofile from house order by views desc fetch first 4 rows only";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				HouseMainDTO dto = new HouseMainDTO();
				dto = HouseMainDTO.builder()
						.num(rs.getInt("num"))
						.title(rs.getString("houseName"))
						.id(rs.getString("id"))
						.scontent(rs.getString("scontent"))
						.ofile(rs.getString("ofile"))
						.build();
				lists.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null && stmt != null && rs != null) {
				DBConnection.close(con, stmt,rs);
			}
		}
		return lists;
	}
}
