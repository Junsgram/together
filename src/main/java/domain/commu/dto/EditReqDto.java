package domain.commu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EditReqDto {
	private int num;
	private String commuName;
	private String scontent;
	private String lcontent;
	private String ofile;
}
