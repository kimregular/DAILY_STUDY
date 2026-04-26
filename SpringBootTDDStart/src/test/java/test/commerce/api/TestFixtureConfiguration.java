package test.commerce.api;

import commerce.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

public class TestFixtureConfiguration {

    @Bean
    @Scope("prototype")
    TestFixture testFixture(
        Environment environment,
        ProductRepository productRepository
    ) {
        return TestFixture.create(environment, productRepository);
    }
}
