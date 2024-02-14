package domain.items.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveReqDto {
private String id;
private String title;
private String scontent;
private String lcontent;
private int price;
private String ofile;
private int quantity;
}
