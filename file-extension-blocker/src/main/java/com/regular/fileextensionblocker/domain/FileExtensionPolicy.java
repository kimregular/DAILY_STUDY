package com.regular.fileextensionblocker.domain;

import com.regular.fileextensionblocker.service.ExtensionNormalizer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "file_extension_policy",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_file_extension_policy_name", columnNames = "name")
        }
)
public class FileExtensionPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 10)
    private ExtensionType type;

    @Column(name = "blocked", nullable = false)
    private Boolean blocked = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    private FileExtensionPolicy(String name, ExtensionType type, Boolean blocked) {
        this.name = name;
        this.type = type;
        this.blocked = blocked;
    }

    public static FileExtensionPolicy fixed(String rawName, boolean blocked) {
        String normalized = ExtensionNormalizer.normalize(rawName);
        return new FileExtensionPolicy(normalized, ExtensionType.FIXED, blocked);
    }

    public static FileExtensionPolicy custom(String rawName) {
        String normalized = ExtensionNormalizer.normalize(rawName);
        return new FileExtensionPolicy(normalized, ExtensionType.CUSTOM, false);
    }

    public void setBlocked(boolean blocked) {
        if (this.type != ExtensionType.FIXED) {
            throw new IllegalStateException("CUSTOM 확장자는 blocked를 변경할 수 없습니다.");
        }
        this.blocked = blocked;
    }
}
