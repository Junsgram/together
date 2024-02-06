package service;

import java.util.List;

import domain.board.House.House;
import domain.board.House.HouseDAO;
import domain.board.House.DTO.EditReqDTO;
import domain.board.House.DTO.ListReqDTO;
import domain.board.House.DTO.ViewReqDTO;
import domain.board.House.DTO.WriteReqDTO;

public class HouseService {
	//field
	//dao객체 생성
	HouseDAO dao = new HouseDAO();
	
	//비즈니스 로직 작성
	//숙소 게시글 등록
	public int regihouse(WriteReqDTO dto) {
		return dao.regihouse(dto);
	}
	//상세보기
	public ViewReqDTO detail(int id) {
		return dao.detail(id);
	}
	//리스트 출력
	public List<ListReqDTO> list(int page) {
		return dao.list(page);
	}
	//페이지 값 출력 메소드
	public int lastPage() {
		return dao.lastPage();
	}
	//작성 후 상세보기 페이지로 이동 -- 수정 중
	public ViewReqDTO show(String id) {
		return dao.show(id);
	}
	//수정페이지 및 프로세스
	public EditReqDTO edit(int num) {
		return dao.edit(num);
	}
	public int edit_process(ViewReqDTO dto) {
		return dao.edit_process(dto);
	}
}

