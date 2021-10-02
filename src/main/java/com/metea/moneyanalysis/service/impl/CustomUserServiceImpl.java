package com.metea.moneyanalysis.service.impl;

import com.metea.moneyanalysis.exception.ServiceExecutionException;
import com.metea.moneyanalysis.repository.UserRepository;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new ServiceExecutionException("Hatalı giriş yaptınız.");
        }
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
