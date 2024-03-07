package domain.diaryComment;

import lombok.Data;

@Data
public class DiarySaveReqDTO {
	private int diaryNum;
	private String userId;
	private String content;
}
