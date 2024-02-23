package domain.commuComment;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import domain.commuComment.dto.FindResDto;
import domain.commuComment.dto.SaveReqDto;

public class CommuCommentDao {
	Connection con;
	PreparedStatement psmt;
	Statement stmt;
	ResultSet rs;
	
	//댓글 저장하기
	public int save(SaveReqDto dto) {
		con = DBConnection.getConnection();
		int result = 0;
		String query = "insert into commucomment (id, communum, userid, content)"
				+" values(commu_com_seq.nextval, ?, ?, ?)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, dto.getCommuNum());
			psmt.setString(2, dto.getUserId());
			psmt.setString(3, dto.getContent());
			result = psmt.executeUpdate();
			//데이터 저장 되면
			if (result==1) {
				//현재 시퀀스 번호를 조회해서 result 변수에 할당
				String sql = "select commu_com_seq.currval from dual";
				psmt = con.prepareStatement(sql);
				rs = psmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con!=null && psmt!=null && rs!=null) {
				DBConnection.close(con, psmt, rs);
			}
		}
		return result;
	}
	//댓글에 id로 하나 조회할 수 있는 메소드
	public FindResDto findById(int id) {
		FindResDto dto = null;
		con = DBConnection.getConnection();
		String query = "select * from commucomment where id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new FindResDto();
				dto.setId(rs.getInt("id"));
				dto.setCommuNum(rs.getInt("communum"));
				dto.setContent(rs.getString("content"));
				dto.setCreateDate(rs.getDate("createdate"));
				dto.setUserId(rs.getString("userid"));
				System.out.println("여기!!"+dto);
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
	
	//댓글 db에서 삭제하는 메소드
	public int delete(int id) {
		int result = 0;
		con = DBConnection.getConnection();
		String query = "delete from commucomment where id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
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
	
	//댓글 전체 리스트 조회하는 메소드
	public List<FindResDto> findAll(int id){
		List<FindResDto> comments = new ArrayList<FindResDto>();
		con = DBConnection.getConnection();
		String query = "select * from commucomment where communum=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				FindResDto dto = new FindResDto();
				dto.setId(rs.getInt("id"));
				dto.setCommuNum(rs.getInt("communum"));
				dto.setContent(rs.getString("content"));
				dto.setCreateDate(rs.getDate("createdate"));
				dto.setUserId(rs.getString("userid"));
				comments.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con!=null && psmt!=null && rs!=null) {
				DBConnection.close(con, psmt, rs);
			}
		}
		
		return comments;
	}
}
