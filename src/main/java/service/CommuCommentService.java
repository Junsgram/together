package service;

import java.util.List;

import domain.commuComment.CommuCommentDao;
import domain.commuComment.dto.FindResDto;
import domain.commuComment.dto.SaveReqDto;

public class CommuCommentService {
	//필드
	private CommuCommentDao dao;
	//생성자
	public CommuCommentService() {
		this.dao = new CommuCommentDao();
	}
	//댓글 저장하기 메소드
	public int save (SaveReqDto dto) {
		int result = dao.save(dto);
		return result;
	}
	
	//댓글 하나 조회하기
	public FindResDto find (int id) {
		return dao.findById(id);
	}
	
	//댓글 삭제하기
	public int delete(int id) {
		return dao.delete(id);
	}
	
	//댓글 전체 조회하기
	public List<FindResDto> findAll(int id){
		return dao.findAll(id);
	}
}
