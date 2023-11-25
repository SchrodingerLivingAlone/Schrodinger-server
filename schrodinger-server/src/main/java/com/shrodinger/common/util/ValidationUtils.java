package com.shrodinger.common.util;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

public class ValidationUtils {
    public static Map<String, String> getValidationErrors(Errors errors) {
        return errors.getAllErrors().stream()
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        error -> error.getDefaultMessage()
                ));
    }
}
