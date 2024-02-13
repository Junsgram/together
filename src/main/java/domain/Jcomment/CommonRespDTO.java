package domain.Jcomment;

import lombok.Data;

@Data
public class CommonRespDTO<T> {
	private int StatusCode;
	private T data;
}
