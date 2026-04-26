package test.commerce.api.seller.products.id;

import java.time.LocalDateTime;
import java.util.UUID;

import commerce.command.RegisterProductCommand;
import commerce.view.SellerProductView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import test.commerce.api.CommerceApiTest;
import test.commerce.api.TestFixture;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static test.commerce.ProductAssertions.isDerivedFrom;
import static test.commerce.RegisterProductCommandGenerator.generateRegisterProductCommand;

@CommerceApiTest
@DisplayName("GET /seller/product/{id}")
public class GET_specs {

    @Test
    void 올바르게_요청하면_200_OK_상태코드를_반환한다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser();
        UUID id = fixture.registerProduct();

        // Act
        ResponseEntity<?> response = fixture.client().getForEntity(
            "/seller/products/" + id,
            SellerProductView.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    void 판매자가_아닌_사용자의_접근_토큰을_사용하면_403_Forbidden_상태코드를_반환한다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser();
        UUID id = fixture.registerProduct();

        fixture.createShopperThenSetAsDefaultUser();

        // Act
        ResponseEntity<?> response = fixture.client().getForEntity(
            "/seller/products/" + id,
            SellerProductView.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(403);
    }

    @Test
    void 존재하지_않는_상품_식별자를_사용하면_404_Not_Found_상태코드를_반환한다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser();
        UUID id = UUID.randomUUID();

        // Act
        ResponseEntity<?> response = fixture.client().getForEntity(
            "/seller/products/" + id,
            SellerProductView.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    void 다른_판매자가_등록한_상품_식별자를_사용하면_404_Not_Found_상태코드를_반환한다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser();
        UUID id = fixture.registerProduct();

        fixture.createSellerThenSetAsDefaultUser();

        // Act
        ResponseEntity<?> response = fixture.client().getForEntity(
            "/seller/products/" + id,
            SellerProductView.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    void 상품_식별자를_올바르게_반환한다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser();
        UUID id = fixture.registerProduct();

        // Act
        SellerProductView actual = fixture.client().getForObject(
            "/seller/products/" + id,
            SellerProductView.class
        );

        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual.id()).isEqualTo(id);
    }

    @Test
    void 상품_정보를_올바르게_반환한다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser();
        RegisterProductCommand command = generateRegisterProductCommand();
        UUID id = fixture.registerProduct(command);

        // Act
        SellerProductView actual = fixture.client().getForObject(
            "/seller/products/" + id,
            SellerProductView.class
        );

        // Assert
        assertThat(actual).satisfies(isDerivedFrom(command));
    }

    @Test
    void 상품_등록_시각을_올바르게_반환한다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser();
        LocalDateTime referenceTime = LocalDateTime.now(UTC);
        UUID id = fixture.registerProduct();

        // Act
        SellerProductView actual = fixture.client().getForObject(
            "/seller/products/" + id,
            SellerProductView.class
        );

        // Assert
        assertThat(actual.registeredTimeUtc())
            .isCloseTo(referenceTime, within(1, SECONDS));
    }
}
