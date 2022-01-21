package com.shivam9ronaldo7.springautomationreportserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileResponse {

    private String fileName;
    private String fileUrl;
    private String message;

}
