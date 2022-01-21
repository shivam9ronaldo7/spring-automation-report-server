package com.shivam9ronaldo7.springautomationreportserver.exceptions;

import com.shivam9ronaldo7.springautomationreportserver.model.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class FileExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException exc) {
        List<String> details = new ArrayList<String>();
        details.add(exc.getMessage());
        ResponseError err = new ResponseError(LocalDateTime.now(), "File Not Found" ,details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        List<String> details = new ArrayList<String>();
        details.add(exc.getMessage());
        ResponseError err = new ResponseError(LocalDateTime.now(), "File Size Exceeded" ,details);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(err);
    }

    @ExceptionHandler(EmptyFileException.class)
    public ResponseEntity<Object> handleEmptyFileException(EmptyFileException exc) {
        List<String> details = new ArrayList<String>();
        details.add(exc.getMessage());
        ResponseError err = new ResponseError(LocalDateTime.now(), "Empty File" ,details);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler(InvalidFileExtensionException.class)
    public ResponseEntity<Object> handleInvalidFileExtensionException(InvalidFileExtensionException exc) {
        List<String> details = new ArrayList<String>();
        details.add(exc.getMessage());
        ResponseError err = new ResponseError(LocalDateTime.now(), "Invalid file extension(Only json supported)" ,details);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

}
