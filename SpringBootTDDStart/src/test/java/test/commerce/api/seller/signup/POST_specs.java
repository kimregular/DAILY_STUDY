package test.commerce.api.seller.signup;

import commerce.Seller;
import commerce.SellerRepository;
import commerce.command.CreateSellerCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import test.commerce.api.CommerceApiTest;

import static org.assertj.core.api.Assertions.assertThat;
import static test.commerce.EmailGenerator.generateEmail;
import static test.commerce.PasswordGenerator.generatePassword;
import static test.commerce.UsernameGenerator.generateUsername;

@CommerceApiTest
@DisplayName("POST /seller/signUp")
public class POST_specs {

    @Test
    void 올바르게_요청하면_204_No_Content_상태코드를_반환한다(
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        var command = new CreateSellerCommand(
            generateEmail(),
            generateUsername(),
            "password",
            generateEmail()
        );

        // Act
        ResponseEntity<Void> response = client.postForEntity(
            "/seller/signUp",
            command,
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(204);
    }

    @Test
    void email_속성이_지정되지_않으면_400_Bad_Request_상태코드를_반환한다(
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        var command = new CreateSellerCommand(
            null,
            generateUsername(),
            "password",
            generateEmail()
        );

        // Act
        ResponseEntity<Void> response = client.postForEntity(
            "/seller/signUp",
            command,
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "invalid-email",
        "invalid-email@",
        "invalid-email@test",
        "invalid-email@test.",
        "invalid-email@.com"
    })
    void email_속성이_올바른_형식을_따르지_않으면_400_Bad_Request_상태코드를_반환한다(
        String email,
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        var command = new CreateSellerCommand(
            email,
            generateUsername(),
            "password",
            generateEmail()
        );

        // Act
        ResponseEntity<Void> response = client.postForEntity(
            "/seller/signUp",
            command,
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    void username_속성이_지정되지_않으면_400_Bad_Request_상태코드를_반환한다(
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        var command = new CreateSellerCommand(
            generateEmail(),
            null,
            "password",
            generateEmail()
        );

        // Act
        ResponseEntity<Void> response = client.postForEntity(
            "/seller/signUp",
            command,
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "se",
        "seller ",
        "seller.",
        "seller!",
        "seller@"
    })
    void username_속성이_올바른_형식을_따르지_않으면_400_Bad_Request_상태코드를_반환한다(
        String username,
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        var command = new CreateSellerCommand(
            generateEmail(),
            username,
            "password",
            generateEmail()
        );

        // Act
        ResponseEntity<Void> response = client.postForEntity(
            "/seller/signUp",
            command,
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "seller",
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
        "0123456789",
        "seller_",
        "seller-"
    })
    void username_속성이_올바른_형식을_따르면_204_No_Content_상태코드를_반환한다(
        String username,
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        var command = new CreateSellerCommand(
            generateEmail(),
            username,
            "password",
            generateEmail()
        );

        // Act
        ResponseEntity<Void> response = client.postForEntity(
            "/seller/signUp",
            command,
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(204);
    }

    @Test
    void password_속성이_지정되지_않으면_400_Bad_Request_상태코드를_반환한다(
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        var command = new CreateSellerCommand(
            generateEmail(),
            generateUsername(),
            null,
            generateEmail()
        );

        // Act
        ResponseEntity<Void> response = client.postForEntity(
            "/seller/signUp",
            command,
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @ParameterizedTest
    @MethodSource("test.commerce.TestDataSource#invalidPasswords")
    void password_속성이_올바른_형식을_따르지_않으면_400_Bad_Request_상태코드를_반환한다(
        String password,
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        var command = new CreateSellerCommand(
            generateEmail(),
            generateUsername(),
            password,
            generateEmail()
        );

        // Act
        ResponseEntity<Void> response = client.postForEntity(
            "/seller/signUp",
            command,
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    void email_속성에_이미_존재하는_이메일_주소가_지정되면_400_Bad_Request_상태코드를_반환한다(
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        String email = generateEmail();

        client.postForEntity(
            "/seller/signUp",
            new CreateSellerCommand(
                email,
                generateUsername(),
                "password",
                generateEmail()
            ),
            Void.class
        );

        // Act
        ResponseEntity<Void> response = client.postForEntity(
            "/seller/signUp",
            new CreateSellerCommand(
                email,
                generateUsername(),
                "password",
                generateEmail()
            ),
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    void username_속성에_이미_존재하는_사용자이름이_지정되면_400_Bad_Request_상태코드를_반환한다(
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        String username = generateUsername();

        client.postForEntity(
            "/seller/signUp",
            new CreateSellerCommand(
                generateEmail(),
                username,
                "password",
                generateEmail()
            ),
            Void.class
        );

        // Act
        ResponseEntity<Void> response = client.postForEntity(
            "/seller/signUp",
            new CreateSellerCommand(
                generateEmail(),
                username,
                "password",
                generateEmail()
            ),
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    void 비밀번호를_올바르게_암호화한다(
        @Autowired TestRestTemplate client,
        @Autowired SellerRepository repository,
        @Autowired PasswordEncoder encoder
    ) {
        // Arrange
        var command = new CreateSellerCommand(
            generateEmail(),
            generateUsername(),
            generatePassword(),
            generateEmail()
        );

        // Act
        client.postForEntity("/seller/signUp", command, Void.class);

        // Assert
        Seller seller = repository
            .findAll()
            .stream()
            .filter(x -> x.getEmail().equals(command.email()))
            .findFirst()
            .orElseThrow();
        String actual = seller.getHashedPassword();
        assertThat(actual).isNotNull();
        assertThat(encoder.matches(command.password(), actual)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("test.commerce.TestDataSource#invalidEmails")
    void contactEmail_속성이_올바르게_지정되지_않으면_400_Bad_Request_상태코드를_반환한다(
        String contactEmail,
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        var command = new CreateSellerCommand(
            generateEmail(),
            generateUsername(),
            generatePassword(),
            contactEmail
        );

        // Act
        ResponseEntity<Void> response = client.postForEntity(
            "/seller/signUp",
            command,
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }
}
