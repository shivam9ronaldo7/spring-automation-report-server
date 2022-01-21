package com.shivam9ronaldo7.springautomationreportserver.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file.upload")
@Data
public class FileUploadProperties {
    private String location;
}