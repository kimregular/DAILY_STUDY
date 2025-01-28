package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductRespositoryTest {

    @Autowired
    private ProductRespository productRespository;

    @Test
    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    void test1() {
        // given
        Product
        // when

        // then
        assertThat()
    }
}