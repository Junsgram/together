package domain.board.House.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class WriteReqDTO {
	//게시글 등록 시 필요한 필드
	private int num;
	private String id;
	private String houseName;
	private String scontent;
	private String lcontent;
	private int likes;
	private int views;
	private int starts;
	private Date regidate;
	private String Ofile;
	
	//image field
}
