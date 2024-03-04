package domain.diaryComment;

import java.util.Date;

import lombok.Data;

@Data
public class DiaryFindRespDTO {
	private int comnum;
	private int diaryNum;
	private String userId;
	private String content;
	private Date createDate;
}
