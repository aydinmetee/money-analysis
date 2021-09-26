package com.metea.moneyanalysis.util;

import com.metea.moneyanalysis.dto.UserReadDTO;
import com.metea.moneyanalysis.service.UserService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SessionContext {
    private UserReadDTO user;

    @Autowired
    @Lazy
    private UserService userService;

    public SessionContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        this.user = userService.getByUsername(currentPrincipalName);
    }
}
