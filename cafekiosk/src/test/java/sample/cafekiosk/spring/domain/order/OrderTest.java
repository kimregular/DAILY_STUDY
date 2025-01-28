package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.domain.product.Product;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

class OrderTest {

	@Test
	@DisplayName("주문 생성 시 상품 리스트에서 주문의 총 금액을 계산한다.")
	void test1() {
		// given
		List<Product> products = List.of(
				createProduct("001", 1000),
				createProduct("002", 2000)
		);

		// when
		Order order = Order.create(products, LocalDateTime.now());

		// then
		assertThat(order.getTotalPrice()).isEqualTo(3000);
	}

	@Test
	@DisplayName("주문 생성시 상태는 INIT이다.")
	void test2() {
		// given
		List<Product> products = List.of(
				createProduct("001", 1000),
				createProduct("002", 2000)
		);

		// when
		Order order = Order.create(products, LocalDateTime.now());

		// then
		assertThat(order.getOrderStatus()).isEqualByComparingTo(OrderStatus.INIT);
	}

	@Test
	@DisplayName("주문 생성시 주문 등록 시간을 기록한다.")
	void test3() {
		// given
		LocalDateTime registeredDateTime = LocalDateTime.now();
		List<Product> products = List.of(
				createProduct("001", 1000),
				createProduct("002", 2000)
		);

		// when
		Order order = Order.create(products, registeredDateTime);

		// then
		assertThat(order.getRegisteredDateTime()).isEqualTo(registeredDateTime);
	}

	private Product createProduct(String productNumber, int price) {
		return Product.builder()
				.productNumber(productNumber)
				.type(HANDMADE)
				.sellingStatus(SELLING)
				.name("메뉴 이름")
				.price(price)
				.build();
	}
}