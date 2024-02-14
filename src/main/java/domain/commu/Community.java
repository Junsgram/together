package domain.commu;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Community {
	
		private int num;
		private String id;
		private String commuName;
		private String address;
		private String scontent;
		private String lcontent;
		private int likes;
		private int views;
		private int stars;
		private Date regidate;
	
}
