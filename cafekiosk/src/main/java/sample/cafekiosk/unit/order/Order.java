package sample.cafekiosk.unit.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sample.cafekiosk.unit.beverage.Beverage;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor  // final과 @NotNull이 붙은 필드만 생성자를 만들어줌 (필수 값들)
public class Order {

    private final LocalDateTime orderDateTime;
    private final List<Beverage> beverages;
}
