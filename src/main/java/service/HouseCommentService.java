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
	public List<HouseFindRespDTO> list(int housenum){
		return commentDAO.findAll(housenum);
	}
	
	//해당하는 댓글 조회
	public HouseFindRespDTO detailComment(int housenum) {
		this.commentDAO = new HouseCommentDAO();
		return commentDAO.findId(housenum);
	}
	
	//댓글 삭제
	public int delete(int num) {
		return commentDAO.delete(num);
	}
}
