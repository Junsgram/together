package service;

import java.util.List;

import domain.board.Diary.DiaryDAO;
import domain.board.Diary.DTO.DwriteReqDTO;
import domain.board.Diary.DTO.DListReqDTO;
import domain.board.Diary.DTO.DeditReqDTO;
import domain.board.Diary.DTO.DviewReqDTO;

public class DiaryService {

	DiaryDAO dao = new DiaryDAO();
	//등록메소드
	public int regidiary(DwriteReqDTO dto) {
		return dao.regidiary(dto);
	}
	//목록메소드
	public List<DListReqDTO> list(int page) {
		return dao.list(page);
	}
	//상세보기 메소드
	public DviewReqDTO views(int num) {
		return dao.views(num);
	}
	//조회수 증가
	public int visitcount(int num) {
		return dao.visitcount(num);
	}
	//lastPaging
	public int lastPage() {
		return dao.lastPage();
	}
	//게시글 번호 받기
	public int detailNum() {
		return dao.detailNum();
	}
	//게시글 수정
	public DeditReqDTO edit(int num) {
		return dao.edit(num);
	}
	//수정 프로세스
	public int edit_process(DviewReqDTO dto) {
		return dao.edit_process(dto);
	}
	//삭제 프로세스
	public int delete(int num) {
		return dao.delete(num);
	}
}
