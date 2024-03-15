package domain.board.Diary.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DListReqDTO {
	private int num;
	private String title;
	private String id;
	private String scontent;
	private String lcontent;
	private String ofile;
	private int likes;
	private int views;
	private int stars;
	private Date regidate;
}
