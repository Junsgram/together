package domain.diaryComment;

import lombok.Data;

@Data
public class DiaryCommonRespDTO<T> {
	private int StatusCode;
	private T data;
}
