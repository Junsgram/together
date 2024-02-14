package domain.Jcomment;

import java.util.Date;

import lombok.Data;

@Data
public class HouseFindRespDTO {
	private int comnum;
	private int houseNum;
	private String userId;
	private String content;
	private Date createDate;
}
