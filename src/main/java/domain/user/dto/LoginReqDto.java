package domain.user.dto;

import lombok.Data;

@Data
public class LoginReqDto {
	private String email;
	private String pw2;
}
