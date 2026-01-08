package com.regular.fileextensionblocker.service;

import com.regular.fileextensionblocker.exception.InvalidExtensionException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ExtensionNormalizer {

    public static String normalize(String raw) {
        if (raw == null) throw new InvalidExtensionException("확장자가 비어있습니다.");

        String s = raw.trim();
        if (s.isEmpty()) throw new InvalidExtensionException("확장자가 비어있습니다.");

        // 앞뒤 공백 제거 후 내부에 공백이 있는지 확인
        if (s.contains(" ")) {
            throw new InvalidExtensionException("확장자에 공백을 포함할 수 없습니다.");
        }

        // 선행 dot 제거
        while (s.startsWith(".")) {
            s = s.substring(1);
        }

        s = s.trim().toLowerCase(Locale.ROOT);

        if (s.isEmpty()) throw new InvalidExtensionException("확장자가 비어있습니다.");

        if (s.length() > 20) {
            throw new InvalidExtensionException("확장자 최대 길이는 20자리입니다.");
        }

        // 공백 포함 금지
        if (s.contains(" ")) {
            throw new InvalidExtensionException("확장자에 공백을 포함할 수 없습니다.");
        }

        // 허용 문자 체크 (영문/숫자/_/-)
        if (!s.matches("^[a-z0-9_-]+$")) {
            throw new InvalidExtensionException("확장자는 영문/숫자/(_,-)만 허용합니다.");
        }

        return s;
    }
}
