package domain.items;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Items {
	//상세 설명 사진으로?
	private int num;
	private String id;
	private String title;
	private String scontent;
	private String lcontent;
	private int price;
	private int likes;
	private int views;
	private int stars;
	private String ofile;
	private Date regidate;
	

}
