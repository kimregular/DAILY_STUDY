package sample.cafekiosk.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

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
    @DisplayName("add 테스트 - 1잔")
    void test21(){
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();

        // when
        cafeKiosk.add(new Americano());

        // then
        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).getPrice()).isEqualTo(4000);
    }

    @Test
    @DisplayName("add 테스트 - 여러잔")
    void test22(){
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        // when
        cafeKiosk.add(americano, 2);

        // then
        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
    }

    @Test
    @DisplayName("add 테스트 - 경계값 0")
    void test() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        // when
        // then
        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("음료는 1잔 이상 주문할 수 있습니다.");
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

    @Test
    @DisplayName("create Order test")
    void test51() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);
        // when
        Order order = cafeKiosk.createOrder();
        // then
        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    @DisplayName("create Order test with CurrentLocalDateTime")
    void test52() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);
        // when
        Order order = cafeKiosk.createOrder(LocalDateTime.of(2025, 1, 26, 10, 0));
        // then
        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    @DisplayName("exception - create Order test with CurrentLocalDateTime")
    void test53() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);
        // when
        // then
        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2025, 1, 26, 9, 59)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요");
    }

    @Test
    @DisplayName("exception - create Order test with CurrentLocalDateTime")
    void test54() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);
        // when
        // then
        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2025, 1, 26, 22, 1)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요");
    }
}