package domain.board.House.Main;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseMainDTO {
	private int num;
	private String title;
	private String id;
	private String scontent;
	private String ofile;
		
}
