package domain.diaryComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;

public class DiaryCommentDAO {
	// field
	Connection con = DBConnection.getConnection();
	PreparedStatement psmt = null;
	Statement stmt = null;
	ResultSet rs = null;

	// 댓글 등록
	public int save(DiarySaveReqDTO dto) {
		int result = 0;
		String sql = "insert into diary_comment (comnum,diarynum,userid,content)"
				+ " values(diary_com_seq.nextval,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, dto.getDiaryNum());
			psmt.setString(2, dto.getUserId());
			psmt.setString(3, dto.getContent());
			result = psmt.executeUpdate();
			if (result == 1) {
				String query = "select diary_com_seq.currval from dual";
				psmt = con.prepareStatement(query);
				rs = psmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt(1);
					System.out.println("diary Comment result의 값 : " + result);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null && psmt != null) {
				DBConnection.close(con, psmt);
			}
		}
		return result;
	}

	// 댓글 리스트
	public List<DiaryFindRespDTO> findAll(int diarynum) {
		List<DiaryFindRespDTO> comments = new ArrayList<DiaryFindRespDTO>();
		String sql = "select * from diary_comment where diarynum = ? order by comnum desc";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, diarynum);
			rs = psmt.executeQuery();
			while (rs.next()) {
				DiaryFindRespDTO dto = new DiaryFindRespDTO();
				dto.setComnum(rs.getInt(1));
				dto.setDiaryNum(rs.getInt(2));
				dto.setUserId(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setCreateDate(rs.getDate(5));
				comments.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null && psmt != null && rs != null) {
				DBConnection.close(con, psmt, rs);
			}
		}
		return comments;
	}

	// 댓글 아이디 찾기
	public DiaryFindRespDTO findId(int comnum) {
		DiaryFindRespDTO dto = null;
		String sql = "select * from diary_comment where comnum = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, comnum);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new DiaryFindRespDTO();
				dto.setComnum(rs.getInt("comnum"));
				dto.setDiaryNum(rs.getInt("diarynum"));
				dto.setUserId(rs.getString("userid"));
				dto.setContent(rs.getString("content"));
				dto.setCreateDate(rs.getDate("createDate"));
				System.out.println("아이디찾기 :" + dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null && psmt != null && rs !=null) {
				DBConnection.close(con, psmt, rs);
			}
		}
		return dto;
	}
	// 댓글 삭제
	public int delete(int num) {
		int result = 0;
		String sql = "delete from diary_comment where comnum = ?";
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
				DBConnection.close(con,psmt);
			}
		}
		return result;
	}
}
