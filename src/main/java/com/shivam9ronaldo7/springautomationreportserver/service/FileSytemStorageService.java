package com.shivam9ronaldo7.springautomationreportserver.service;

import com.shivam9ronaldo7.springautomationreportserver.exceptions.FileStorageException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileSytemStorageService {

    void init() throws FileStorageException, IOException;
    String saveFile(MultipartFile file);
    Resource loadFile(String fileName);
    List<String> getFiles();
    String parseCucumberReport(String fileName);

}
