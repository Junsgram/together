package service;

import java.util.List;

import domain.Jcomment.HouseCommentDAO;
import domain.Jcomment.HouseFindRespDTO;
import domain.Jcomment.HouseSaveReqDTO;

public class HouseCommentService {
	//필드 생성
	private HouseCommentDAO commentDAO;
	
	//생성자
	public HouseCommentService() {
		this.commentDAO = new HouseCommentDAO();
	}
	
	//메소드
	//댓글 등록
	public int commentReg(HouseSaveReqDTO dto) {
		return commentDAO.save(dto);
	}
	
	//댓글 목록 조회
	public List<HouseFindRespDTO> list(int id){
		return commentDAO.findAll(id);
	}
	
	//해당하는 댓글 조회
	public HouseFindRespDTO detailComment(int id) {
		this.commentDAO = new HouseCommentDAO();
		return commentDAO.findId(id);
	}
}
