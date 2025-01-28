package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmericanoTest {

    @Test
    @DisplayName("이름 확인 테스트")
    void test1(){
        // given
        Americano americano = new Americano();
        // when
        // then
        assertThat(americano.getName()).isEqualTo("아메리카노");
    }

    @Test
    @DisplayName("가격 확인 테스트")
    void test2() {
        // given
        Americano americano = new Americano();
        // when
        // then
        assertThat(americano.getPrice()).isEqualTo(4000);
    }
}