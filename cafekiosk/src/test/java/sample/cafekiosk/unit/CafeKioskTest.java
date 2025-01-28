package sample.cafekiosk.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;

import static org.assertj.core.api.Assertions.assertThat;

class CafeKioskTest {

    @Test
    @DisplayName("add 테스트 - manualTest")
    void test1(){
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();

        // when
        cafeKiosk.add(new Americano());

        // then
        assertThat(cafeKiosk.getBeverages().get(0).getPrice()).isEqualTo(4000);
    }

    @Test
    @DisplayName("add 테스트 - automatedTest")
    void test2(){
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();

        // when
        cafeKiosk.add(new Americano());

        // then
        assertThat(cafeKiosk.getBeverages().get(0).getPrice()).isEqualTo(4000);
    }

}