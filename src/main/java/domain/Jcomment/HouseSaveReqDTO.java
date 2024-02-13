package domain.Jcomment;

import lombok.Data;

@Data
public class HouseSaveReqDTO {
	private int id;
	private int num;
	private String userId;
	private String comment;
}
