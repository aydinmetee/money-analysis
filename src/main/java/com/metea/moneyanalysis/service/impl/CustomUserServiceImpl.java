package com.metea.moneyanalysis.service.impl;

import com.metea.moneyanalysis.exception.LoginExecutionException;
import com.metea.moneyanalysis.repository.UserRepository;
import com.metea.moneyanalysis.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class CustomUserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageUtil messageUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new LoginExecutionException(messageUtil.get("loginService.notFound.exception"));
        }
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
