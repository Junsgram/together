package domain.board.House;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor //모든 생성자 생성
@NoArgsConstructor //기본생성자 생성
public class House {
	private int num;
	private String id;
	private String houseName;
	private String address;
	private String scontent;
	private String lcontent;
	private int likes;
	private int views;
	private int stars;
	private Date regidate;
}
