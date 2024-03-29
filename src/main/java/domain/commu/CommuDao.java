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
		String query = "insert into community(num,title,id,scontent,lcontent,ofile,address)"
				+ " values(together_community_seq.nextval, ?,?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getScontent());
			psmt.setString(4, dto.getLcontent());
			psmt.setString(5, dto.getOfile());
			psmt.setString(6, dto.getAddress());
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
		con = DBConnection.getConnection();
		List<ListReqDto> lists = new ArrayList<ListReqDto>();
		String query = "select * from ("
				+ " select td.*, rownum as rnum from (select * from community order by num desc) td)"
				+ " where rnum between ? and ? order by num desc"; 
		int start = 8*page+1;
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, start);
			psmt.setInt(2, start+7);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ListReqDto dto = new ListReqDto();
				dto = ListReqDto.builder()
						.num(rs.getInt("num"))
						.id(rs.getString("id"))
						.title(rs.getString("title"))
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
		}finally {
			if (con!=null&&psmt!=null&&rs!=null) {
				DBConnection.close(con, psmt, rs);
			}
		}
		return lists;
	}
	
	//게시글 작성 후 상세보기로 페이지 이동
	public int detailNum() {
		int result = 0;
		con = DBConnection.getConnection();
		String sql = "select together_community_seq.currval from dual";
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
			if(con != null && psmt != null && rs != null) {
				DBConnection.close(con, stmt, rs);
			}
		}
		
		return result;
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
		String query = "select * from community where num = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = ViewReqDto.builder()
						.num(rs.getInt("num"))
						.id(rs.getString("id"))
						.title(rs.getString("title"))
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
	//조회수 메소드
	public int visitUpdate(int num)	{
		int result = 0;
		con = DBConnection.getConnection();
		String sql = "update community set views = views + 1 where num = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, num);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null && psmt != null) {
				DBConnection.close(con, psmt);
			}
		}
		return result;
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
				dto.setTitle(rs.getString("title"));
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
		String query = "update community set title=?, scontent=?, lcontent=?, ofile=?"
				+ " where num= ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
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
				DBConnection.close(con, psmt);
			}
		}
		return result;
	}
	
	//게시글 삭제
	public int delete(int num) {
		int result = 0;
		con = DBConnection.getConnection();
		String sql = "delete from community where num = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, num);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
