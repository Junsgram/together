package service;

import java.util.List;

import domain.commu.CommuDao;
import domain.commu.dto.EditReqDto;
import domain.commu.dto.ListReqDto;
import domain.commu.dto.ViewReqDto;
import domain.commu.dto.WriteReqDto;

public class commuService {
	//필드
	CommuDao dao = new CommuDao();
	
	//커뮤니티 게시글 등록
	public int regiCommu(WriteReqDto dto) {
		return dao.regiCommu(dto);
	}
	//게시글 등록 후 상세페이지로 이동
	public int detailNum() {
		return dao.detailNum();
	}
	
	//리스트 
	public List<ListReqDto> list(int page){
		return dao.list(page);
	}
	//전체 게시글 수 구하는 메소드
	public int lastPage() {
		return dao.lastPage();
	}
	//상세보기 메소드
	public ViewReqDto detail(int num) {
		return dao.detail(num);
	}
	//수정페이지 요청
	public EditReqDto edit(int num) {
		return dao.edit(num);
	}
	//수정프로세스 요청
	public int edit_process(ViewReqDto dto) {
		return dao.edit_process(dto);
	}
	//조회수
	public int visitUpdate(int num) {
		return dao.visitUpdate(num);
	}
	//게시글 삭제
	public int delete(int num) {
		return dao.delete(num);
	}
}
