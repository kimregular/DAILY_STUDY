package sample.cafekiosk.spring.api.service.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRespository;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.BAKERY;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
@SpringBootTest
class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductRespository productRespository;

	@Test
	@DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
	void test1() {
		// given
		Product product1 = createProduct(HANDMADE, "001", 1000);
		Product product2 = createProduct(HANDMADE, "002", 3000);
		Product product3 = createProduct(BAKERY, "003", 5000);
		productRespository.saveAll(List.of(product1, product2, product3));

		OrderCreateRequest request = OrderCreateRequest.builder()
				.productNumbers(List.of("001", "002"))
				.build();

		// when
		LocalDateTime registeredDateTime = LocalDateTime.now();
		OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);


		// then
		assertThat(orderResponse.getId()).isNotNull();
		assertThat(orderResponse)
				.extracting("registeredDateTime", "totalPrice")
				.contains(registeredDateTime, 4000);
		assertThat(orderResponse.getProducts()).hasSize(2)
				.extracting("productNumber", "price")
				.containsExactlyInAnyOrder(
						tuple("001", 1000),
						tuple("002", 3000)
				);
	}

	private Product createProduct(ProductType type, String productNumber, int price) {
		return Product.builder()
				.productNumber(productNumber)
				.type(type)
				.sellingStatus(SELLING)
				.name("메뉴 이름")
				.price(price)
				.build();
	}

}