package com.metea.moneyanalysis.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginExecutionException extends RuntimeException {

    private String message;

    public LoginExecutionException(String message) {
        this.message = message;
    }

}
