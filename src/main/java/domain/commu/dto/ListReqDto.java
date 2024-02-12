package domain.commu.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListReqDto {
	private int num;
	private String id;
	private String commuName;
	private String scontent;
	private String lcontent;
	private String address;
	private int likes;
	private int views;
	private int stars;
	private Date regidate;
	private String Ofile;
}
