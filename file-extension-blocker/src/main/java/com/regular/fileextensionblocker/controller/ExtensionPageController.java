package com.regular.fileextensionblocker.controller;

import com.regular.fileextensionblocker.service.FileExtensionPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ExtensionPageController {

    private final FileExtensionPolicyService service;

    @GetMapping("/extensions")
    public String extensionsPage(Model model) {
        var fixedList = service.getFixedPolicies();
        var customList = service.getCustomPolicies();

        model.addAttribute("fixedList", fixedList);
        model.addAttribute("customList", customList);
        model.addAttribute("customCount", customList.size());

        return "extensions";
    }
}
