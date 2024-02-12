package domain.commu.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class WriteReqDto {
	private int num;
	private String id;
	private String commuName;
	private String scontent;
	private String lcontent;
	private int likes;
	private int views;
	private int starts;
	private Date regidate;
	private String Ofile;
}
