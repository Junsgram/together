package domain.commuComment;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommuComment {
	private int id;
	private int commuNum;
	private String userId;
	private String content;
	private Date createDate;
}
