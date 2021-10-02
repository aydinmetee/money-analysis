package com.metea.moneyanalysis.util;

import com.metea.moneyanalysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageUtil {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    @Lazy
    private UserService userService;


    public String get(String code) {
        return messageSource.getMessage(code, null,
                new Locale(userService.getSessionInfo().getLanguage().toString().toLowerCase()));

    }
}
