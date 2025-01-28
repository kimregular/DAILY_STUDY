# Test - JUNIT5

## JUnit5

- 단위 테스트를 위한 테스트 프레임워크

## AssertJ

- 테스트 코드 작성을 돕는 테스트 라이브러리
- 다양한 API, 메서드 체이닝 지원

## Unit Test

`org.springframework.boot:spring-boot=starter-test`

- **작은** 코드 단위를 **독립적**으로 검증하는 테스트
    - 작은 단위는 클래스 혹은, 메서드를 의미
    - 독립적이라는 의미는 외부 상황에 의존하지 않음을 의미
- 검증 속도가 빠르고, 안정적이다.
- JUnit의 `@Test` 를 통해 단위 테스트 진행 가능

## 테스트 케이스 세분화하기

1. 해피 케이스
2. 예외 케이스
3. 경계값 테스트

범위(이상, 이하, 초과, 미만), 구간, 날짜 등을 테스트 해봐야한다.
정수값이 있을 때 이 값이 3이상이어야 조건을 만족한다고 가정하자.
이때는 경계값인 3의 테스트를 해봐야한다.
당연히 예외 케이스도 검증해야하므로 2의 테스트도 진행해야한다.

## 테스트하기 어려운 영역을 분리하기

어떤 영역이 테스트하기 어려운 영역인가?

- 관측할 때마다 다른 값에 의존하는 코드
    - 현재 날짜/시간, 랜덤 값, 전역 변수/함수, 사용자 입력 등
- 외부 세계에 영향을 주는 코드
    - 표준 출력, 메시지 발송, 데이터베이스 쓰기 등

### 예시

`가게 운영 시간(10:00 ~ 22:00) 외에는 주문을 생성할 수 없다.`  
위와 같은 조건이 있다고 가정해보자.

```java
public Order createOrder() {
    LocalDateTime currentDateTime = LocalDateTime.now();
    LocalTime currentTime = currentDateTime.toLocalTime();
    if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
        throw new IllegalStateException("주문 시간이 아닙니다. 관리자에게 문의하세요");
    }

    return new Order(currentDateTime, beverages);
}
```

위 코드에서 주문을 만들 때 지금 시간을 메서드에서 처리하고 있다.

```java

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
```

따라서 위 테스트 코드는 테스트 코드를 실행하는 시간에 따라 성공하거나 실패하게 된다.
즉 시간에 따라 테스트 결과가 달라진다. -> 분리 필요

```java
public Order createOrder(LocalDateTime currentDateTime) {
    LocalTime currentTime = currentDateTime.toLocalTime();
    if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
        throw new IllegalStateException("주문 시간이 아닙니다. 관리자에게 문의하세요");
    }

    return new Order(currentDateTime, beverages);
}
```

위와 같이 현재 시간을 입력받도록 코드를 변경한다.

```java

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
```

현재 시간을 분리함으로써 시간에 의존하지 않는 테스트를 작성할 수 있게 되었다.
그리고 경계값 테스트도 가능하게 되었다.

## @DisplayName 작성 가이드

- Bad : 음로 1개 추가 **테스트**
- Good : 음료를 1개 추가할 수 있다.
- 명사의 나열보다는 문장으로 테스트를 서술한다.`~테스트`라는 이름은 지양한다.


- Bad : 음료를 1개 추가할 수 있다.
- Good : 음료를 1개 추가하면 주문 목록에 담긴다.
- 테스트 행위에 대한 결과까지 기술한다.


- Bad : 특정 시간 이전에 무준을 생성하면 **실패**한다.
- Good : **영업 시작 시간** 전에는 주문을 생성할 수 없다.
- "영업 시작 시간" 등의 도메인 용어를 사용해서 추상화된 내용을 기술한다.
- 테스트의 현상을 중점으로 기술하기를 지양한다. 실패한다는 서술은 테스트의 내용과는 무관하다.

## 통합 테스트

- 여러 모듈이 협력하는 기능을 통합적으로 검증하는 테스트
- 일반적으로 작은 범위의 단위 테스트만으로는 기능 전체의 신뢰성을 보장할 수 없다.
- 풍부한 단위 테스트, 큰 기능 단위를 검증하는 통합 테스트

## Persistence Layer

- Data Access 역할
- 비즈니스 가공 로직이 포함되면 안 된다. Data CRUD에만 집중한다.

## Business Layer

- 비즈니스 로직을 구현하는 역할
- Persistence Layer와의 상호작용을 통해 비즈니스 로직을 전개
- 트랜잭션 보장