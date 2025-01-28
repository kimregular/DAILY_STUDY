package sample.cafekiosk.spring.domain.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String productNumber;

    @Enumerated(STRING)
    private ProductType type;

    @Enumerated(STRING)
    private ProductSellingType sellingType;

    private int price;
}
