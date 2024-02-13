package domain.Jcomment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;

public class HouseCommentDAO {
	//field
	Connection con = DBConnection.getConnection();
	PreparedStatement psmt;
	Statement stmt;
	ResultSet rs;
	
	//method
	//댓글 등록
	public int save(HouseSaveReqDTO dto) {
		int result = 0;
		String sql = "insert into housecomment (comnum,houseNum,userid,content)"
				+ " values(house_com_seq.nextval,?,?,?)";
		psmt = null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, dto.getNum());
			psmt.setString(2, dto.getUserId());
			psmt.setString(3, dto.getContent());
			result = psmt.executeUpdate();
			if(result ==1 ) {
				//현재 시퀀스 번호를 조회해서 result변수에 할당
				String query = "select house_com_seq.currval from dual";
				psmt = con.prepareStatement(query);
				rs = psmt.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1);
				}
			}
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
	
	//댓글 리스트
	public List<HouseFindRespDTO> findAll(int housenum) {
		List<HouseFindRespDTO> comments = new ArrayList<HouseFindRespDTO>();
		String sql = "select m.id,c.comnum,c.userId,c.houseNum,c.content,c.createdate"
				+ " from member m inner join housecomment c "
				+ " on m.id = c.userid "
				+ " where c.housenum = ? order by c.id desc";
		psmt = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, housenum);
			rs = psmt.executeQuery();
			while(rs.next()) {
				HouseFindRespDTO dto = new HouseFindRespDTO();
				dto.setUserId(rs.getString(1));
				dto.setId(rs.getInt(2));
				dto.setHouseNum(rs.getInt(3));
				dto.setContent(rs.getString(4));
				dto.setCreateDate(rs.getDate(5));
				comments.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null && psmt != null && rs!= null) {
				DBConnection.close(con, psmt,rs);
			}
		}
		return comments;	
	}
	
	//댓글 아이디 찾기
	public HouseFindRespDTO findId(int id) {
		HouseFindRespDTO dto = null;
		String sql = "select m.id, c.comnum, c.houseNum, c.content"
				+ " from member m inner join housecomment c "
				+ " on m.id = c.userid"
				+ " where c.id = ?";
		psmt = null;
		rs = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new HouseFindRespDTO();
				dto.setId(rs.getInt(2));
				dto.setUserId(rs.getString(1));
				dto.setHouseNum(rs.getInt(3));
				dto.setContent(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null && psmt != null && rs!= null) {
				DBConnection.close(con, psmt,rs);
			}
		}
		return dto;
	}
	
	
	
}
