package domain.user;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import config.DBConnection;
import domain.user.dto.EditReqDto;
import domain.user.dto.JoinReqDto;
import domain.user.dto.KakaoLoginReqDto;
import domain.user.dto.LoginReqDto;

public class UserDao {
	//필드
	Connection conn = DBConnection.getConnection();
	PreparedStatement psmt;
	Statement stmt;
	ResultSet rs;

	//로그인 dao
	public User findByIdandPass(LoginReqDto logDto) {
		User user = null;
		String query = "select * from member where email=? and pw2=?";
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, logDto.getEmail());
			psmt.setString(2, logDto.getPw2());
			rs = psmt.executeQuery();
			if (rs.next()) {
				user=User.builder()
						.id(rs.getString("id"))
						.pw1(rs.getString("pw1"))
						.pw2(rs.getString("pw2"))
						.email(rs.getString("email"))
						.username(rs.getString("username"))
						.call1(rs.getString("call1"))
						.call2(rs.getString("call2"))
						.call3(rs.getString("call3"))
						.zipcode(rs.getString("zipcode"))
						.addr1(rs.getString("addr1"))
						.addr2(rs.getString("addr2"))
						.ofile(rs.getString("ofile"))
						.dogname(rs.getString("dogname"))
						.userrole(rs.getString("userrole"))
						.userpoint(rs.getInt("userpoint"))
						.birthday(rs.getDate("birthday"))
						.build();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null && psmt!=null && rs!=null) {
				DBConnection.close(conn, psmt, rs);
			}
		}
		return user;
	}
	//카카로 로그인 dao
	public User findByUsernameAndEmail(KakaoLoginReqDto dto) {
		User user = null;
		String query = "select * from member where email=? and username=?";
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getEmail());
			psmt.setString(2, dto.getUsername());
			System.out.println("여기!!!!!!!"+dto.getEmail());
			System.out.println("여기!!!!!!!!!!!"+dto.getUsername());
			rs = psmt.executeQuery();
			if (rs.next()) {
				user = User.builder()
						.id(rs.getString("id"))
						.pw1(rs.getString("pw1"))
						.pw2(rs.getString("pw2"))
						.email(rs.getString("email"))
						.username(rs.getString("username"))
						.call1(rs.getString("call1"))
						.call2(rs.getString("call2"))
						.call3(rs.getString("call3"))
						.zipcode(rs.getString("zipcode"))
						.addr1(rs.getString("addr1"))
						.addr2(rs.getString("addr2"))
						.ofile(rs.getString("ofile"))
						.dogname(rs.getString("dogname"))
						.userrole(rs.getString("userrole"))
						.userpoint(rs.getInt("userpoint"))
						.birthday(rs.getDate("birthday"))
						.build();
				System.out.println("여기~~~~~~~~~"+user.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (conn!=null && psmt!=null && rs!=null) {
				DBConnection.close(conn, psmt, rs);
			}
		}
		return user;
	}
	
	
	//아이디 중복체크 dao
	public int findById(String email) {
		int result=0;
		String query = "select * from member where email=?";
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			if (rs.next()) {
				result=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null && psmt!=null && rs!=null) {
				DBConnection.close(conn, psmt, rs);
			}
		}
		return result;
	}
	
	//회원가입 dto
	public int saveMember(JoinReqDto joinDto) {
		int result = 0;
		String query = "insert into member (id, pw1, pw2, email, call1, call2, call3, zipcode, addr1, addr2, ofile, dogname, birthday, username)"
					+" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, joinDto.getId());
			psmt.setString(2, joinDto.getPw1());
			psmt.setString(3, joinDto.getPw2());
			psmt.setString(4, joinDto.getEmail());
			psmt.setString(5, joinDto.getCall1());
			psmt.setString(6, joinDto.getCall2());
			psmt.setString(7, joinDto.getCall3());
			psmt.setString(8, joinDto.getZipcode());
			psmt.setString(9, joinDto.getAddr1());
			psmt.setString(10, joinDto.getAddr2());
			psmt.setString(11, joinDto.getOfile());
			psmt.setString(12, joinDto.getDogname());
			psmt.setString(13, joinDto.getBirthday());
			psmt.setString(14, joinDto.getUsername());
			result = psmt.executeUpdate();
			
			
			System.out.println("---------------");
			System.out.println(joinDto.getOfile());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null && psmt!=null) {
				DBConnection.close(conn, psmt);
			}
		}
		return result;
	}
	
	//회원정보 수정 메소드
	public int editMember(EditReqDto editDto) {
		int result = 0;
		String query = "update member set pw1=?, pw2=?, email=?, call1=?, call2=?, call3=?, zipcode=?, addr1=?, addr2=?, ofile=?, dogname=?, birthday=?, username=? where id=?";
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, editDto.getPw1());
			psmt.setString(2, editDto.getPw2());
			psmt.setString(3, editDto.getEmail());
			psmt.setString(4, editDto.getCall1());
			psmt.setString(5, editDto.getCall2());
			psmt.setString(6, editDto.getCall3());
			psmt.setString(7, editDto.getZipcode());
			psmt.setString(8, editDto.getAddr1());
			psmt.setString(9, editDto.getAddr2());
			psmt.setString(10, editDto.getOfile());
			psmt.setString(11, editDto.getDogname());
			psmt.setString(12, editDto.getBirthday());
			psmt.setString(13, editDto.getUsername());
			psmt.setString(14, editDto.getId());
			System.out.println(query);
			result = psmt.executeUpdate();
			
			//user객체 생성하기
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null && psmt!=null) {
				DBConnection.close(conn, psmt);
			}
		}		
		return result;
	}
	
	public User findByIdReturnUser(String userId) {
		User user = null;
		String query = "select * from member where id=?";
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				user=User.builder()
						.id(rs.getString("id"))
						.pw1(rs.getString("pw1"))
						.pw2(rs.getString("pw2"))
						.email(rs.getString("email"))
						.call1(rs.getString("call1"))
						.call2(rs.getString("call2"))
						.call3(rs.getString("call3"))
						.zipcode(rs.getString("zipcode"))
						.addr1(rs.getString("addr1"))
						.addr2(rs.getString("addr2"))
						.ofile(rs.getString("ofile"))
						.dogname(rs.getString("dogname"))
						.userrole(rs.getString("userrole"))
						.userpoint(rs.getInt("userpoint"))
						.birthday(rs.getDate("birthday"))
						.build();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null && psmt!=null && rs!=null) {
				DBConnection.close(conn, psmt, rs);
			}
		}
		return user;
	}
}
