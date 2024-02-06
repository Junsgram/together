package domain.board.House.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListReqDTO {
	//리스트페이지에 보여아할 내용들
	private int num;
	private String id;
	private String houseName;
	private String scontent;
	private String lcontent;
	private String address;
	private int likes;
	private int views;
	private int stars;
	private Date regidate;
	private String Ofile;
	
}

