package test.commerce;

import java.lang.annotation.Retention;

import org.junit.jupiter.params.provider.MethodSource;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@MethodSource("test.commerce.TestDataSource#invalidEmails")
public @interface InvalidEmailSource {
}
