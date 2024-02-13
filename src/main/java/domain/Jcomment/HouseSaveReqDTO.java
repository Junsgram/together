package domain.Jcomment;

import lombok.Data;

@Data
public class HouseSaveReqDTO {
	private int housenum;
	private String userId;
	private String content;
}
