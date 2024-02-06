package domain.board.House.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditReqDTO {
	private int num;
	private String houseName;
	private String scontent;
	private String lcontent;
	private String ofile;
}
