package com.regular.fileextensionblocker.repository;

import com.regular.fileextensionblocker.domain.ExtensionType;
import com.regular.fileextensionblocker.domain.FileExtensionPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileExtensionPolicyRepository extends JpaRepository<FileExtensionPolicy, Long> {

    Optional<FileExtensionPolicy> findByName(String name);

    boolean existsByName(String name);

    List<FileExtensionPolicy> findAllByTypeOrderByNameAsc(ExtensionType type);

    long countByType(ExtensionType type);
}
