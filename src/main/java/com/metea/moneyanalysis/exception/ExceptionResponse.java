package com.metea.moneyanalysis.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {
    private String message;
    private LocalDateTime localDateTime;

    public ExceptionResponse(String message) {
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }
}
