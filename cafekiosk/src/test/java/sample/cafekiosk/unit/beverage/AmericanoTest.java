package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AmericanoTest {

    @Test
    void getName() {
        // given
        Americano americano = new Americano();

        // then
//        assertEquals(americano.getName(), "아메리카노");  // JUnit 5의 비교 api
        assertThat(americano.getName()).isEqualTo("아메리카노");  // AssertJ의 비교 api. 위 메서드 보다 더 명시적
    }

    @Test
    void getPrice() {
        // given
        Americano americano = new Americano();

        // then
        assertThat(americano.getPrice()).isEqualTo(4000);
    }
}
