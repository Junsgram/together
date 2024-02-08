package domain.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	private int num;
	private String id;
	private String title;
	private int price;
	private String ofile;
	private int order_quantity;
}
