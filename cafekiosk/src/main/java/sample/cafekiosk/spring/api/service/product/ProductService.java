package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * readOnly = true : 읽기전용
 * CRUD 에서 CUD 동작 X / only Read
 * JPA를 사용할 때 영향점 : CUD 스냅샷 저장, 변경감지 X (성능 향상)
 *
 * CQRS 패턴 - Command / Query를 의도적으로 분리해서 책임을 분리한다.
 * 보통의 서비스라면 Command 보다는 Query의 빈도가 2:8정도로 높다.
 * Query가 많이 몰려서 Command까지 같이 동작을 안해버리면 큰 문제. 그렇기 때문에 애플리케이션 단에서 분리해서 관리하자고 나온 것이 CQRS 패턴. DB에 대한 엔드포인트를 관리할 수 있다.
 * 요즘엔 Read용 DB랑 Write용 DB를 나눠서 쓰는 편. Master-Slave 구조. Master DB에 Write만 하고 마스터의 레플리카인 Slave DB는 Read만 한다.
 * readOnly = true가 붙어있으면 Slave로 보내자 이렇게 의식할 수 있음. 이렇게 DB 엔드포인트를 구분함으로써 장애 격리를 할 수 있는 좋은 포인트가 된다.
 * AWS Aurora DB 클러스트 모드를 쓰면 같은 엔드포인트라도 readOnly 값을 보고 알아서 구분을 해준다.
 *
 * CQRS 패턴을 사용하지 않더라도 readOnly = true를 붙이는 것은 좋은 습관이다.
 * 메서드마다 달면 깜박하고 누락될 수 있기 때문에 서비스 클래스 상단에 readOnly = true를 걸고 CUD를 하는 부분에만 @Transactional을 붙이는 것을 추천한다.
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 이곳도 동시성 이슈가 발생할 수 있다.
     * 해결 방법은 여러가지가 있겠지만
     * 그 중 한 예시로 빈도수가 높지 않다면 DB의 productNumber 필드에 유니크 제약 조건을 걸고 시스템에서 최대 3회정도 재시도하는 로직을 만들 수도 있다.
     * 동시 접속자가 너무 많아 크리티컬한 경우에는 productNumber를 증가하는 값이 아니라 UUID 같은 걸 사용한다던지 정책을 다르게 가져가야 할 수도 있다.
     */
    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        String nextProductNumber = createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }

        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }
}
