package com.regular.fileextensionblocker.controller;

import com.regular.fileextensionblocker.domain.FileExtensionPolicy;
import com.regular.fileextensionblocker.dto.ExtensionDtos;
import com.regular.fileextensionblocker.service.FileExtensionPolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/extensions")
@RequiredArgsConstructor
public class ExtensionApiController {

    private final FileExtensionPolicyService service;

    @PatchMapping("/fixed/{id}")
    public ExtensionDtos.FixedResponse updateFixed(
            @PathVariable Long id,
            @Valid @RequestBody ExtensionDtos.UpdateFixedRequest request
    ) {
        FileExtensionPolicy updated = service.setFixedBlocked(id, request.blocked());
        return ExtensionDtos.FixedResponse.from(updated);
    }

    @PostMapping("/custom")
    @ResponseStatus(HttpStatus.CREATED)
    public ExtensionDtos.CustomResponse addCustom(
            @Valid @RequestBody ExtensionDtos.AddCustomRequest request
    ) {
        FileExtensionPolicy created = service.addCustomExtension(request.name());
        return ExtensionDtos.CustomResponse.from(created);
    }

    @DeleteMapping("/custom/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustom(@PathVariable Long id) {
        service.deleteCustomExtension(id);
    }

    @PostMapping("/upload-check")
    public ExtensionDtos.UploadCheckResponse uploadCheck(@RequestParam("file") org.springframework.web.multipart.MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename == null || !filename.contains(".")) {
            return new ExtensionDtos.UploadCheckResponse(true, "확장자가 없는 파일은 허용됩니다.");
        }

        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        boolean blocked = service.isBlocked(extension);

        if (blocked) {
            return new ExtensionDtos.UploadCheckResponse(false, "." + extension + " 확장자는 차단되었습니다.");
        }

        return new ExtensionDtos.UploadCheckResponse(true, "업로드 가능한 파일입니다.");
    }
}
