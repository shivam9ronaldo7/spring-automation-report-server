package com.shivam9ronaldo7.springautomationreportserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivam9ronaldo7.springautomationreportserver.configuration.FileUploadProperties;
import com.shivam9ronaldo7.springautomationreportserver.exceptions.EmptyFileException;
import com.shivam9ronaldo7.springautomationreportserver.exceptions.InvalidFileExtensionException;
import com.shivam9ronaldo7.springautomationreportserver.model.Execution;
import com.shivam9ronaldo7.springautomationreportserver.model.Feature;
import com.shivam9ronaldo7.springautomationreportserver.model.FileResponse;
import com.shivam9ronaldo7.springautomationreportserver.service.ExecutionsServiceImpl;
import com.shivam9ronaldo7.springautomationreportserver.service.FileSytemStorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FileController {

    private static final Logger LOGGER = LogManager.getLogger(FileController.class);

    @Autowired
    FileSytemStorageService fileSytemStorageService;

    @Autowired
    ExecutionsServiceImpl executionsServiceImpl;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadSingleFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new EmptyFileException("File is empty");
        }
        String upfile = fileSytemStorageService.saveFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/download/")
                .path(upfile)
                .toUriString();
        return ResponseEntity.status(HttpStatus.OK).body(new FileResponse(upfile, fileDownloadUri, "File uploaded with success!"));
    }

    @PostMapping("/uploadfiles")
    public ResponseEntity<List<FileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        if (files.length == 0) {
            throw new EmptyFileException("Upload atleast 1 file");
        }
        List<FileResponse> responses = Arrays
                .asList(files)
                .stream()
                .map(file -> {
                            String upfile = fileSytemStorageService.saveFile(file);
                            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                    .path("/api/download/")
                                    .path(upfile)
                                    .toUriString();
                            return new FileResponse(upfile, fileDownloadUri, "File uploaded with success!");
                        }
                )
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Resource resource = fileSytemStorageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/getFileList")
    public ResponseEntity<List<String>> getFileList() {
        List<String> files = fileSytemStorageService.getFiles();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/processReport")
    public ResponseEntity<String> processReport(@RequestParam("filename") String filename,
                                                @RequestParam("executionStartTime") String executionStartTime,
                                                @RequestParam("executionBuildNumber") String executionBuildNumber,
                                                @RequestParam("startedByUser") String startedByUser) throws IOException {
        LOGGER.debug("Started reading");
        List<Feature> features = fileSytemStorageService.parseCucumberReport(filename);
        Execution execution = new Execution();
        execution.setExecutionStartTime(executionStartTime);
        execution.setExecutionBuildNumber(Long.valueOf(executionBuildNumber));
        execution.setStartedByUser(startedByUser);
        execution.setFeatures(features);
        executionsServiceImpl.addExecution(execution);
        LOGGER.debug("Uploading done");
        return ResponseEntity.status(HttpStatus.OK).body(execution.toString());
    }

}
