package com.regular.fileextensionblocker.service;


import com.regular.fileextensionblocker.domain.ExtensionType;
import com.regular.fileextensionblocker.domain.FileExtensionPolicy;
import com.regular.fileextensionblocker.repository.FileExtensionPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FixedExtensionSeeder implements ApplicationRunner {

    private final FileExtensionPolicyRepository repository;

    // 과제에서 자주 차단하는 확장자 예시(기본 unchecked=false → blocked=false)
    private static final List<String> DEFAULT_FIXED = List.of(
            "bat", "cmd", "com", "cpl", "exe", "scr", "js"
    );

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        // FIXED 데이터가 없으면만 seed
        long fixedCount = repository.countByType(ExtensionType.FIXED);
        if (fixedCount > 0) return;

        for (String ext : DEFAULT_FIXED) {
            // FIXED 기본은 uncheck => blocked=false
            repository.save(FileExtensionPolicy.fixed(ext, false));
        }
    }
}
