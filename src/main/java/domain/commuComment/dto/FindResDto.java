package domain.commuComment.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FindResDto {
	private int id;
	private int commuNum;
	private String userId;
	private String content;
	private Date createDate;
}
