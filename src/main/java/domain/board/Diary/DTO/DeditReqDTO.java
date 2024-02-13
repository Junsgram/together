package domain.board.Diary.DTO;

import java.util.Date;

import lombok.Data;


@Data
public class DeditReqDTO {
	private int num;
	private String title;
	private String scontent;
	private String lcontent;
	private String ofile;
	private Date regidate;
}
