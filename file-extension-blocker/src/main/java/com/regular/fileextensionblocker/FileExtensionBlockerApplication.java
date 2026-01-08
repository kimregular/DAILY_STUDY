package com.regular.fileextensionblocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FileExtensionBlockerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FileExtensionBlockerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FileExtensionBlockerApplication.class, args);
    }

}
