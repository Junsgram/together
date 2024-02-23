package domain.commuComment;

import lombok.Data;

@Data
public class CommonResDto<T> {
	private int statusCode;
	private T data;
}
