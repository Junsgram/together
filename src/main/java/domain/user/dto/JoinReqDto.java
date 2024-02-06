package domain.user.dto;

import java.util.Date;

import lombok.Data;

@Data
public class JoinReqDto {
	private String id;
	private String pw1;
	private String pw2;
	private String email;
	private int call1;
	private int call2;
	private int call3;
	private String zipcode;
	private String addr1;
	private String addr2;
	private String ofile;
	private String dogname;
	private String birthday;
}
