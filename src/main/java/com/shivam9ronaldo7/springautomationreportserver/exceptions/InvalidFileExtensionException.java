package com.shivam9ronaldo7.springautomationreportserver.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InvalidFileExtensionException extends RuntimeException {
    private String message;
}
