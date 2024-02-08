package domain.user.dto;

import lombok.Data;

@Data
public class EditReqDto {
	private String id;
	private String pw1;
	private String pw2;
	private String email;
	private String call1;
	private String call2;
	private String call3;
	private String zipcode;
	private String addr1;
	private String addr2;
	private String ofile;
	private String dogname;
	private String birthday;
}
