package sample.cafekiosk.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;

import static org.assertj.core.api.Assertions.*;
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
        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).getPrice()).isEqualTo(4000);
    }

    @Test
    @DisplayName("remove test")
    void test3() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        // when
        cafeKiosk.add(americano);

        // then
        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @Test
    @DisplayName("clear test")
    void test4() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();
        // when
        cafeKiosk.add(americano);
        cafeKiosk.add(latte);

        // then
        assertThat(cafeKiosk.getBeverages()).hasSize(2);
        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }
}