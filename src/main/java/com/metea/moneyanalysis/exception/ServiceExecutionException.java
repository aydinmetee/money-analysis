package com.metea.moneyanalysis.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceExecutionException extends RuntimeException {
    private String message;

    public ServiceExecutionException(String message) {
        this.message = message;
    }
}
