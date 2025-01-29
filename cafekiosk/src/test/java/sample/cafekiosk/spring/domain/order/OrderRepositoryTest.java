package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.IntegrationTestSupport;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@Transactional
class OrderRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("시작 날짜와 종료 날짜, 주문상태로 해당하는 모든 주문을 조회한다.")
    @Test
    void findOrdersBy() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, SELLING, "카페라떼", 4500);
        Product product3 = createProduct("003", HANDMADE, SELLING, "팥빙수", 7000);

        productRepository.saveAll(List.of(product1, product2, product3));

        List<Product> products1 = List.of(product1, product2, product3);
        List<Product> products2 = List.of(product1, product3);
        LocalDateTime registeredDateTime1 = LocalDateTime.of(2024, 04, 13, 13, 0, 0);
        LocalDateTime registeredDateTime2 = LocalDateTime.of(2024, 04, 13, 14, 0, 0);

        Order order1 = Order.create(products1, registeredDateTime1);
        Order order2 = Order.create(products2, registeredDateTime2);
        orderRepository.saveAll(List.of(order1, order2));

        LocalDateTime startDateTime = LocalDateTime.of(2024, 04, 13, 0, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 04, 14, 0, 0, 0);
        OrderStatus orderStatus = OrderStatus.INIT;

        // when
        List<Order> orders = orderRepository.findOrdersBy(startDateTime, endDateTime, orderStatus);

        // then
        assertThat(orders).hasSize(2)
                .extracting("orderStatus", "totalPrice", "registeredDateTime")
                .containsExactlyInAnyOrder(
                        tuple(OrderStatus.INIT, 15500, registeredDateTime1),
                        tuple(OrderStatus.INIT, 11000, registeredDateTime2)
                );
    }

    private Product createProduct(
            String productNumber,
            ProductType type,
            ProductSellingStatus sellingStatus,
            String name,
            int price
    ) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}
