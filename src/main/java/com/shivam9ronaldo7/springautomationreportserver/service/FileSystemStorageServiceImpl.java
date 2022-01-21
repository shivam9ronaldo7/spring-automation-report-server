package com.shivam9ronaldo7.springautomationreportserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivam9ronaldo7.springautomationreportserver.configuration.FileUploadProperties;
import com.shivam9ronaldo7.springautomationreportserver.exceptions.FileNotFoundException;
import com.shivam9ronaldo7.springautomationreportserver.exceptions.FileStorageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileSystemStorageServiceImpl implements FileSytemStorageService {

    private static final Logger LOGGER = LogManager.getLogger(FileSystemStorageServiceImpl.class);

    private final Path dirLocation;

    @Autowired
    public FileSystemStorageServiceImpl(FileUploadProperties fileUploadProperties) {
        this.dirLocation = Paths.get(fileUploadProperties.getLocation())
                .toAbsolutePath()
                .normalize();
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(this.dirLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create upload dir!");
        }
    }

    @Override
    public String saveFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            Path destinationFile = this.dirLocation.resolve(fileName);
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            throw new FileStorageException("Could not upload file");
        }
    }

    @Override
    public Resource loadFile(String fileName) throws FileNotFoundException {
        try {
            Path file = this.dirLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Could not find file");
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not download file");
        }
    }

    @Override
    public List<String> getFiles() {
        try {
            List<String> files = Files.list(Paths.get(this.dirLocation.toUri()))
                    .map(Path::toFile).map(File::getAbsolutePath)
                    .collect(Collectors.toList());
            return files;
        } catch (IOException e) {
            throw new FileStorageException("Could not read upload dir!");
        }
    }

    @Override
    public String parseCucumberReport(String fileName) {
        try {
            Path file = this.dirLocation.resolve(fileName).normalize();
            return new ObjectMapper().readTree(new File(file.toUri())).asText();
        } catch (IOException e) {
            throw new FileStorageException("Could not read file!");
        }
    }

}
