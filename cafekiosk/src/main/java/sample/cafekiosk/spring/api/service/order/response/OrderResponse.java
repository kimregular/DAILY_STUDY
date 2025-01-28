package sample.cafekiosk.spring.api.service.order.response;

import lombok.Getter;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {

	private Long id;
	private OrderStatus orderStatus;
	private int totalPrice;
	private LocalDateTime registeredDateTime;
	private List<ProductResponse> products;

}
