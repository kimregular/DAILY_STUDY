package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
}