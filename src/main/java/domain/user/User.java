package domain.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
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
	private String userrole;
	private int userpoint;
	private Date birthday;
}
