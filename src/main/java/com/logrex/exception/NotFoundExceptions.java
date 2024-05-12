package com.logrex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundExceptions extends RuntimeException{

    private String resourceName;

    private String fieldName;

    private long fieldValues;

    public NotFoundExceptions( String resourceName, String fieldName, long fieldValues) {
        super(String.format("%s Not found with %s: '%s'",resourceName,fieldName,fieldValues));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValues = fieldValues;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValues() {
        return fieldValues;
    }
}