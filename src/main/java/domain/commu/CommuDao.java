package domain.commu;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import domain.commu.dto.EditReqDto;
import domain.commu.dto.ListReqDto;
import domain.commu.dto.ViewReqDto;
import domain.commu.dto.WriteReqDto;

public class CommuDao {
	//필드
	Connection con;
	PreparedStatement psmt;
	Statement stmt;
	ResultSet rs;
	
	public int regiCommu(WriteReqDto dto) {
		con = DBConnection.getConnection();
		int result = 0;
		String query = "insert into community(num,communame,id,scontent,lcontent,ofile)"
				+ " values(together_community_seq.nextval, ?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getCommuName());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getScontent());
			psmt.setString(4, dto.getLcontent());
			psmt.setString(5, dto.getOfile());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (con!=null && psmt!=null) {
				DBConnection.close(con, psmt);
			}
		}
		return result;
	}
	
	//전체 게시글 목록 보기
	public List<ListReqDto> list(int page){
		System.out.println("게시글 목록 보기 요청");
		con = DBConnection.getConnection();
		List<ListReqDto> lists = new ArrayList<ListReqDto>();
		String query = "select * from ("
				+ " select td.*, rownum as rnum from (select * from community order by num desc) td)"
				+ " where rnum between ? and ? order by num desc"; 
		int start = 5*page+1;
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, start);
			psmt.setInt(2, start+4);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ListReqDto dto = new ListReqDto();
				dto = ListReqDto.builder()
						.num(rs.getInt("num"))
						.id(rs.getString("id"))
						.commuName(rs.getString("commuName"))
						.scontent(rs.getString("Scontent"))
						.lcontent(rs.getString("Lcontent"))
						.address(rs.getString("Address"))
						.likes(rs.getInt("Likes"))
						.views(rs.getInt("Views"))
						.stars(rs.getInt("Stars"))
						.regidate(rs.getDate("Regidate"))
						.Ofile(rs.getString("ofile"))
						.build();
				System.out.println(dto);
				lists.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (con!=null&&psmt!=null&&rs!=null) {
				DBConnection.close(con, psmt, rs);
			}
		}
		return lists;
	}
	//전체 게시글 수 찾는 메소드
	public int lastPage() {
		con = DBConnection.getConnection();
		int result = 0;
		String query = "select count(*) from community";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (con!=null && stmt!=null && rs!=null) {
				DBConnection.close(con, stmt, rs);
			}
		}
		return result;
	}
	
	//상세보기 메소드
	public ViewReqDto detail(int num) {
		ViewReqDto dto = new ViewReqDto();
		con = DBConnection.getConnection();
		String query = "select * from community where num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = ViewReqDto.builder()
						.num(rs.getInt("num"))
						.id(rs.getString("id"))
						.commuName(rs.getString("commuName"))
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
		} finally {
			if(con != null && psmt != null && rs != null) {
				DBConnection.close(con, psmt, rs);
			}
		}
		return dto;
	}
	
	//수정하기 페이지 요청
	public EditReqDto edit(int num) {
		EditReqDto dto = new EditReqDto();
		con = DBConnection.getConnection();
		String query = "select * from community where num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setCommuName(rs.getString("communame"));
				dto.setScontent(rs.getString("scontent"));
				dto.setLcontent(rs.getString("lcontent"));
				dto.setOfile(rs.getString("ofile"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con!=null && psmt!=null && rs!=null) {
				DBConnection.close(con, psmt, rs);
			}
		}		
		return dto;
	}
	//수정 포스트 요청
	public int edit_process (ViewReqDto dto) {
		int result=0;
		con = DBConnection.getConnection();
		String query = "update community set communame=?, scontent=?, lcontent=?, ofile=?"
				+ " where num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getCommuName());
			psmt.setString(2, dto.getScontent());
			psmt.setString(3, dto.getLcontent());
			psmt.setString(4, dto.getOfile());
			psmt.setInt(5, dto.getNum());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con!=null && psmt!=null) {
				DBConnection.close(con, stmt);
			}
		}
		return result;
	}
}
