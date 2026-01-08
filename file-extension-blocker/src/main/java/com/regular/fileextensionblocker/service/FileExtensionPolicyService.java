package com.regular.fileextensionblocker.service;

import com.regular.fileextensionblocker.domain.ExtensionType;
import com.regular.fileextensionblocker.domain.FileExtensionPolicy;
import com.regular.fileextensionblocker.dto.ExtensionDtos;
import com.regular.fileextensionblocker.exception.BadRequestException;
import com.regular.fileextensionblocker.exception.CustomLimitExceededException;
import com.regular.fileextensionblocker.exception.DuplicateExtensionException;
import com.regular.fileextensionblocker.exception.NotFoundException;
import com.regular.fileextensionblocker.repository.FileExtensionPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileExtensionPolicyService {

    private static final int CUSTOM_MAX_COUNT = 200;
    private final FileExtensionPolicyRepository repository;

    @Transactional(readOnly = true)
    public List<ExtensionDtos.FixedResponse> getFixedPolicies() {
        return repository.findAllByTypeOrderByNameAsc(ExtensionType.FIXED)
                .stream()
                .map(ExtensionDtos.FixedResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ExtensionDtos.CustomResponse> getCustomPolicies() {
        return repository.findAllByTypeOrderByNameAsc(ExtensionType.CUSTOM)
                .stream()
                .map(ExtensionDtos.CustomResponse::from)
                .toList();
    }

    @Transactional
    public FileExtensionPolicy setFixedBlocked(Long id, boolean blocked) {
        FileExtensionPolicy policy = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("고정 확장자 정책을 찾을 수 없습니다. id=" + id));

        if (policy.getType() != ExtensionType.FIXED) {
            throw new BadRequestException("고정 확장자만 변경할 수 있습니다.");
        }

        policy.setBlocked(blocked);
        // JPA dirty checking
        return policy;
    }

    @Transactional
    public FileExtensionPolicy addCustomExtension(String rawName) {
        String normalized = ExtensionNormalizer.normalize(rawName);

        // 200개 제한
        long customCount = repository.countByType(ExtensionType.CUSTOM);
        if (customCount >= CUSTOM_MAX_COUNT) {
            throw new CustomLimitExceededException("커스텀 확장자는 최대 200개까지 추가할 수 있습니다.");
        }

        // 중복 체크
        if (repository.existsByName(normalized)) {
            throw new DuplicateExtensionException("이미 존재하는 확장자입니다: " + normalized);
        }

        try {
            return repository.save(FileExtensionPolicy.custom(normalized));
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateExtensionException("이미 존재하는 확장자입니다: " + normalized);
        }
    }

    @Transactional
    public void deleteCustomExtension(Long id) {
        FileExtensionPolicy policy = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("커스텀 확장자를 찾을 수 없습니다. id=" + id));

        if (policy.getType() != ExtensionType.CUSTOM) {
            throw new BadRequestException("커스텀 확장자만 삭제할 수 있습니다.");
        }

        repository.delete(policy);
    }

    @Transactional(readOnly = true)
    public boolean isBlocked(String rawName) {
        String normalized = ExtensionNormalizer.normalize(rawName);
        return repository.findByName(normalized)
                .map(policy -> {
                    if (policy.getType() == ExtensionType.FIXED) {
                        return Boolean.TRUE.equals(policy.getBlocked());
                    }
                    return true; // CUSTOM은 존재 자체가 차단임
                })
                .orElse(false);
    }
}
