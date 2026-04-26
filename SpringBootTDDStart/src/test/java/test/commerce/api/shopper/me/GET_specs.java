package test.commerce.api.shopper.me;

import commerce.view.ShopperMeView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import test.commerce.api.CommerceApiTest;
import test.commerce.api.TestFixture;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.RequestEntity.get;
import static test.commerce.EmailGenerator.generateEmail;
import static test.commerce.PasswordGenerator.generatePassword;
import static test.commerce.UsernameGenerator.generateUsername;

@CommerceApiTest
@DisplayName("GET /shopper/me")
public class GET_specs {

    @Test
    void 올바르게_요청하면_200_OK_상태코드를_반환한다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        String token = fixture.createShopperThenIssueToken();

        // Act
        ResponseEntity<ShopperMeView> response = fixture.client().exchange(
            get("/shopper/me")
                .header("Authorization", "Bearer " + token)
                .build(),
            ShopperMeView.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    void 접근_토큰을_사용하지_않으면_401_Unauthorized_상태코드를_반환한다(
        @Autowired TestFixture fixture
    ) {
        // Act
        ResponseEntity<ShopperMeView> response = fixture.client().exchange(
            get("/shopper/me").build(),
            ShopperMeView.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(401);
    }

    @Test
    void 서로_다른_구매자의_식별자는_서로_다르다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        String token1 = fixture.createShopperThenIssueToken();
        String token2 = fixture.createShopperThenIssueToken();

        // Act
        ResponseEntity<ShopperMeView> response1 = fixture.client().exchange(
            get("/shopper/me")
                .header("Authorization", "Bearer " + token1)
                .build(),
            ShopperMeView.class
        );
        ResponseEntity<ShopperMeView> response2 = fixture.client().exchange(
            get("/shopper/me")
                .header("Authorization", "Bearer " + token2)
                .build(),
            ShopperMeView.class
        );

        // Assert
        assertThat(requireNonNull(response1.getBody()).id())
            .isNotEqualTo(requireNonNull(response2.getBody()).id());
    }

    @Test
    void 같은_구매자의_식별자는_항상_같다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        String email = generateEmail();
        String password = generatePassword();

        fixture.createShopper(email, generateUsername(), password);
        String token1 = fixture.issueShopperToken(email, password);
        String token2 = fixture.issueShopperToken(email, password);

        // Act
        ResponseEntity<ShopperMeView> response1 = fixture.client().exchange(
            get("/shopper/me")
                .header("Authorization", "Bearer " + token1)
                .build(),
            ShopperMeView.class
        );
        ResponseEntity<ShopperMeView> response2 = fixture.client().exchange(
            get("/shopper/me")
                .header("Authorization", "Bearer " + token2)
                .build(),
            ShopperMeView.class
        );

        // Assert
        assertThat(requireNonNull(response1.getBody()).id())
            .isEqualTo(requireNonNull(response2.getBody()).id());
    }

    @Test
    void 구매자의_기본_정보가_올바르게_설정된다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        String email = generateEmail();
        String username = generateUsername();
        String password = generatePassword();

        fixture.createShopper(email, username, password);
        fixture.setShopperAsDefaultUser(email, password);

        // Act
        ShopperMeView actual = fixture.client().getForObject(
            "/shopper/me",
            ShopperMeView.class
        );

        // Assert
        assertThat(actual.email()).isEqualTo(email);
        assertThat(actual.username()).isEqualTo(username);
    }
}
