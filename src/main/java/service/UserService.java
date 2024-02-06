package service;

import domain.user.User;
import domain.user.UserDao;
import domain.user.dto.EditReqDto;
import domain.user.dto.JoinReqDto;
import domain.user.dto.LoginReqDto;

public class UserService {
	//필드
	private UserDao userDao;
	
	//생성자
	public UserService() {
		this.userDao = new UserDao();
	}
	
	//메소드
	//1. 로그인 메소드
	public User login(LoginReqDto logDto) {
		return userDao.findByIdandPass(logDto);
	}
	
	//2. 아이디 중복 확인 메소드
	public int idCheck(String userId) {
		return userDao.findById(userId);
	}
	
	//3. 회원가입 메소드
	public int join(JoinReqDto joinDto) {
		return userDao.saveMember(joinDto);
	}
	
	//4. 회원 정보 수정 메소드
	public int edit(EditReqDto editDto) {
		return userDao.editMember(editDto);
	}
}
