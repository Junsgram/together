package domain.commuComment.dto;

import lombok.Data;

@Data
public class SaveReqDto {
	private String userId;
	private int commuNum;
	private String content;
}
