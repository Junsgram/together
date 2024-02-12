package domain.board.Diary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import domain.board.Diary.DTO.DListReqDTO;
import domain.board.Diary.DTO.DeditReqDTO;
import domain.board.Diary.DTO.DviewReqDTO;
import domain.board.Diary.DTO.DwriteReqDTO;
import domain.board.House.DTO.ViewReqDTO;

public class DiaryDAO {

	//method
	//게시글 등록
	public int regidiary(DwriteReqDTO dto) {
		int result = 0;
		//1.오라클 접속
		Connection con = DBConnection.getConnection();
		//2.쿼리문 작성 및 객체 생성
		String sql = "insert into diary(num,title,id,scontent,lcontent,ofile)"
				+ " values(together_diary_seq.nextval, ?,?,?,?,?)";
		//동적쿼리문
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1,dto.getTitle());
			psmt.setString(2,dto.getId());
			psmt.setString(3,dto.getScontent());
			psmt.setString(4,dto.getLcontent());
			psmt.setString(5,dto.getOfile());
			//3.쿼리 실행
			result = psmt.executeUpdate();
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
	
	//게시글 list
	public List<DListReqDTO> list(int page) {
		List<DListReqDTO> lists = new ArrayList<DListReqDTO>();
		Connection con = DBConnection.getConnection();
		String sql = "select * from (select td.*, rownum as rnum from (select * from diary order by num desc) td)"
				+ " where rnum between ? and ? order by num desc";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int start = 8 * page + 1;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1,start);
			psmt.setInt(2, start+7);
			rs = psmt.executeQuery();
			while(rs.next()) {
				DListReqDTO dto = new DListReqDTO();
				dto = DListReqDTO.builder()
						.title(rs.getString("title"))
						.id(rs.getString("id"))
						.num(rs.getInt("num"))
						.scontent(rs.getString("scontent"))
						.lcontent(rs.getString("lcontent"))
						.ofile(rs.getString("ofile"))
						.likes(rs.getInt("likes"))
						.views(rs.getInt("views"))
						.stars(rs.getInt("stars"))
						.regidate(rs.getDate("regidate"))
						.build();
				lists.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null && psmt != null && rs != null) {
				DBConnection.close(con, psmt,rs);
			}
		}
		return lists;
	}
	//상세보기 
	public DviewReqDTO views(int num) {
		DviewReqDTO diaryDto = new DviewReqDTO();
		Connection con = DBConnection.getConnection();
		String sql = "select * from diary where num = ?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();
			if(rs.next()) {
				diaryDto = DviewReqDTO.builder()
						.num(rs.getInt("num"))
						.title(rs.getString("title"))
						.id(rs.getString("id"))
						.scontent(rs.getString("scontent"))
						.lcontent(rs.getString("scontent"))
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
		}
		finally {
			if(con != null && psmt != null && rs != null) {
				DBConnection.close(con, psmt,rs);
			}
		}
		return diaryDto;
	}
	//작성 후 상세페이지 이동
	public int detailNum() {
		int result = 0;
		Connection con = DBConnection.getConnection();
		String sql = "select together_diary_seq.currval from dual";
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
		return result;
	}
	
	//조회수 증가
	public int visitcount(int num) {
		int result = 0;
		Connection con = DBConnection.getConnection();
		PreparedStatement psmt = null;
		String sql = "update diary set views = views+1 where num = ?";
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
	
	//lastPaging
	public int lastPage() {
		int result = 0;
		Connection con = DBConnection.getConnection();
		String sql = "select count(*) from diary";
		Statement stmt =null;
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
	//게시글 수정 페이지
	public DeditReqDTO edit(int num) {
		DeditReqDTO edit = new DeditReqDTO();
		Connection con = DBConnection.getConnection();
		String sql = "select * from diary where num = ?";
		PreparedStatement psmt =  null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();
			if(rs.next()) {
				edit.setNum(rs.getInt("num"));
				edit.setTitle(rs.getString("title"));
				edit.setScontent(rs.getString("scontent"));
				edit.setLcontent(rs.getString("lcontent"));
				edit.setOfile(rs.getString("ofile"));
				edit.setRegidate(rs.getDate("regidate"));
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
	
	
	//게시글 수정 프로세스
	public int edit_process(DviewReqDTO dto) {
		int result = 0;
		//DB연결
		Connection con = DBConnection.getConnection();
		//쿼리문 작성 및 객체 생성
		String sql = "update diary set title= ?, scontent = ?, lcontent = ?, ofile = ? "
				+ " where num = ?";
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
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
		finally {
			if(con != null && psmt != null) {
				DBConnection.close(con, psmt);
			}
		}
		return result;
	}
	
	//게시글 삭제
	public int delete(int num) {
		int result = 0;
		Connection con = DBConnection.getConnection();
		String sql = "delete from diary where num = ? ";
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1,num);
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
	
	
	
}
