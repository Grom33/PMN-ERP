package ru.gromov.customer.web.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ExceptionField implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String objectName;

    private final String field;

    private final String message;

    public ExceptionField(String objectName, String field, String message) {
        this.objectName = objectName;
        this.field = field;
        this.message = message;
    }
}
