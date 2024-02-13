package domain.Jcomment;

import lombok.Data;

@Data
public class HouseSaveReqDTO {
	private int comnum;
	private int num;
	private String userId;
	private String content;
}
