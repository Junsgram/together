package domain.board.Diary.DTO;

import java.sql.Date;

import lombok.Data;




@Data
public class DwriteReqDTO {
	private int num;
	private String title;
	private String id;
	private String scontent;
	private String lcontent;
	private int likes;
	private int views;
	private int stars;
	private String ofile;
	private Date regidate;
}
