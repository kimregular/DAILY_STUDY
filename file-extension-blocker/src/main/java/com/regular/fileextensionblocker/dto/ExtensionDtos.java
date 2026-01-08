package com.regular.fileextensionblocker.dto;

import com.regular.fileextensionblocker.domain.FileExtensionPolicy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public final class ExtensionDtos {

    private ExtensionDtos() {}

    // ===== Requests =====

    public record UpdateFixedRequest(Boolean blocked) {
        // blocked는 null이면 안 됨
        public UpdateFixedRequest {
            if (blocked == null) throw new IllegalArgumentException("blocked는 필수입니다.");
        }
    }

    public record AddCustomRequest(
            @NotBlank(message = "확장자는 필수입니다.")
            @Size(max = 20, message = "확장자 최대 길이는 20자리입니다.")
            String name
    ) {}

    // ===== Responses =====

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FixedResponse {
        private Long id;
        private String name;
        private boolean blocked;

        public static FixedResponse from(FileExtensionPolicy p) {
            return new FixedResponse(p.getId(), p.getName(), Boolean.TRUE.equals(p.getBlocked()));
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CustomResponse {
        private Long id;
        private String name;

        public static CustomResponse from(FileExtensionPolicy p) {
            return new CustomResponse(p.getId(), p.getName());
        }
    }

    // 에러 응답 공통 포맷
    public record ErrorResponse(String code, String message) {}

    public record UploadCheckResponse(boolean allowed, String message) {}
}
