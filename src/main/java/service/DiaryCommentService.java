package service;

import java.util.List;

import domain.diaryComment.DiaryCommentDAO;
import domain.diaryComment.DiaryFindRespDTO;
import domain.diaryComment.DiarySaveReqDTO;

public class DiaryCommentService {
	//field
	private DiaryCommentDAO dao;
	
	//constructor 
	public DiaryCommentService() {
		this.dao = new DiaryCommentDAO();
	}
	
	//method
	// 댓글 등록
	public int commentReg(DiarySaveReqDTO dto) {
		return dao.save(dto);
	}
	// 댓글 목록 조회
	public List<DiaryFindRespDTO> list(int diarynum) {
		return dao.findAll(diarynum);
	}
	// 댓글 목록 조회
	public DiaryFindRespDTO detailComment(int diaryNum) {
		this.dao = new DiaryCommentDAO();
		return dao.findId(diaryNum);
	}
	// 댓글 삭제
	public int delete(int num) {
		return dao.delete(num);
	}
	
}
