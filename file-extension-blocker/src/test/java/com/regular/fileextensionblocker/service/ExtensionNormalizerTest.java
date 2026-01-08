package com.regular.fileextensionblocker.service;

import com.regular.fileextensionblocker.exception.InvalidExtensionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExtensionNormalizerTest {

    @Test
    @DisplayName("확장자 정규화: 소문자 변환 및 선행 점 제거")
    void normalize() {
        assertThat(ExtensionNormalizer.normalize(".JPG")).isEqualTo("jpg");
        assertThat(ExtensionNormalizer.normalize("..png")).isEqualTo("png");
        assertThat(ExtensionNormalizer.normalize("  .txt  ")).isEqualTo("txt");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", ". ", "...", "verylongextensionnameover20characters"})
    @DisplayName("유효하지 않은 확장자 형식은 예외가 발생한다")
    void invalid(String raw) {
        assertThatThrownBy(() -> ExtensionNormalizer.normalize(raw))
                .isInstanceOf(InvalidExtensionException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"sh x", "e xe", "a b"})
    @DisplayName("공백을 포함한 확장자는 예외가 발생한다")
    void containsSpace(String raw) {
        assertThatThrownBy(() -> ExtensionNormalizer.normalize(raw))
                .isInstanceOf(InvalidExtensionException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"한글", "abc!", "test@"})
    @DisplayName("허용되지 않는 문자가 포함된 경우 예외가 발생한다")
    void invalidCharacters(String raw) {
        assertThatThrownBy(() -> ExtensionNormalizer.normalize(raw))
                .isInstanceOf(InvalidExtensionException.class);
    }

    @Test
    @DisplayName("허용되는 특수문자(_, -) 확인")
    void allowedSpecialCharacters() {
        assertThat(ExtensionNormalizer.normalize("abc_def")).isEqualTo("abc_def");
        assertThat(ExtensionNormalizer.normalize("abc-def")).isEqualTo("abc-def");
    }
}
