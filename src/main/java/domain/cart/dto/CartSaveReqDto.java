package domain.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartSaveReqDto {
	private String title;
	private String id;
	private int price;
	private String ofile;
	private int order_quantity;
}
