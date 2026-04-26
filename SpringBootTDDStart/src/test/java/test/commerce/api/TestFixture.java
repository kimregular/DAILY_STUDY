package test.commerce.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import commerce.ProductRepository;
import commerce.command.CreateSellerCommand;
import commerce.command.CreateShopperCommand;
import commerce.command.RegisterProductCommand;
import commerce.query.IssueShopperToken;
import commerce.result.AccessTokenCarrier;
import commerce.result.PageCarrier;
import commerce.view.ProductView;
import commerce.view.SellerMeView;
import org.springframework.boot.test.web.client.LocalHostUriTemplateHandler;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.requireNonNull;
import static org.springframework.http.RequestEntity.get;
import static test.commerce.EmailGenerator.generateEmail;
import static test.commerce.PasswordGenerator.generatePassword;
import static test.commerce.RegisterProductCommandGenerator.generateRegisterProductCommand;
import static test.commerce.UsernameGenerator.generateUsername;

public record TestFixture(
    TestRestTemplate client,
    ProductRepository productRepository
) {

    public static TestFixture create(
        Environment environment,
        ProductRepository productRepository
    ) {
        var client = new TestRestTemplate(new RestTemplateBuilder());
        var uriTemplateHandler = new LocalHostUriTemplateHandler(environment);
        client.setUriTemplateHandler(uriTemplateHandler);
        return new TestFixture(client, productRepository);
    }

    public void createShopper(String email, String username, String password) {
        var command = new CreateShopperCommand(email, username, password);
        ensureSuccessful(
            client.postForEntity("/shopper/signUp", command, Void.class),
            command
        );
    }

    private void ensureSuccessful(
        ResponseEntity<Void> response,
        Object request
    ) {
        if (response.getStatusCode().is2xxSuccessful() == false) {
            String message = "Request with " + request
                + " failed with status code " + response.getStatusCode();
            throw new RuntimeException(message);
        }
    }

    public String issueShopperToken(String email, String password) {
        AccessTokenCarrier carrier = client.postForObject(
            "/shopper/issueToken",
            new IssueShopperToken(email, password),
            AccessTokenCarrier.class
        );
        return carrier.accessToken();
    }

    public String createShopperThenIssueToken() {
        String email = generateEmail();
        String password = generatePassword();
        createShopper(email, generateUsername(), password);
        return issueShopperToken(email, password);
    }

    public void setShopperAsDefaultUser(String email, String password) {
        String token = issueShopperToken(email, password);
        setDefaultAuthorization("Bearer " + token);
    }

    private void setDefaultAuthorization(String authorization) {
        RestTemplate template = client.getRestTemplate();
        template.getInterceptors().addFirst((request, body, execution) -> {
            if (request.getHeaders().containsKey("Authorization") == false) {
                request.getHeaders().add("Authorization", authorization);
            }

            return execution.execute(request, body);
        });
    }

    public void createSellerThenSetAsDefaultUser() {
        String email = generateEmail();
        String password = generatePassword();
        String contactEmail = generateEmail();
        createSeller(email, generateUsername(), password, contactEmail);
        setSellerAsDefaultUser(email, password);
    }

    public void createSeller(
        String email,
        String username,
        String password,
        String contactEmail
    ) {
        var command = new CreateSellerCommand(
            email,
            username,
            password,
            contactEmail
        );
        ensureSuccessful(
            client.postForEntity("/seller/signUp", command, Void.class),
            command
        );
    }

    private void setSellerAsDefaultUser(String email, String password) {
        String token = issueSellerToken(email, password);
        setDefaultAuthorization("Bearer " + token);
    }

    private String issueSellerToken(String email, String password) {
        AccessTokenCarrier carrier = client.postForObject(
            "/seller/issueToken",
            new IssueShopperToken(email, password),
            AccessTokenCarrier.class
        );
        return carrier.accessToken();
    }

    public void createShopperThenSetAsDefaultUser() {
        String email = generateEmail();
        String password = generatePassword();
        createShopper(email, generateUsername(), password);
        setShopperAsDefaultUser(email, password);
    }

    public UUID registerProduct() {
        return registerProduct(generateRegisterProductCommand());
    }

    public UUID registerProduct(RegisterProductCommand command) {
        ResponseEntity<Void> response = client.postForEntity(
            "/seller/products",
            command,
            Void.class
        );
        URI location = response.getHeaders().getLocation();
        String path = requireNonNull(location).getPath();
        String id = path.substring("/seller/products/".length());
        return UUID.fromString(id);
    }

    public List<UUID> registerProducts() {
        return List.of(registerProduct(), registerProduct(), registerProduct());
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    public List<UUID> registerProducts(int count) {
        List<UUID> ids = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ids.add(registerProduct());
        }

        return ids;
    }

    public SellerMeView getSeller() {
        return client.getForObject("/seller/me", SellerMeView.class);
    }

    public String consumeProductPage() {
        ResponseEntity<PageCarrier<ProductView>> response = client.exchange(
            get("/shopper/products").build(),
            new ParameterizedTypeReference<>() { }
        );
        return requireNonNull(response.getBody()).continuationToken();
    }

    public String consumeTwoProductPages() {
        String token = consumeProductPage();
        ResponseEntity<PageCarrier<ProductView>> response = client.exchange(
            get("/shopper/products?continuationToken=" + token).build(),
            new ParameterizedTypeReference<>() { }
        );
        return requireNonNull(response.getBody()).continuationToken();
    }
}
