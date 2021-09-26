package com.metea.moneyanalysis.endpoint;

import com.metea.moneyanalysis.dto.UserLoginDTO;
import com.metea.moneyanalysis.dto.UserReadDTO;
import com.metea.moneyanalysis.dto.UserWriteDTO;
import com.metea.moneyanalysis.service.UserService;
import com.metea.moneyanalysis.service.impl.CustomUserServiceImpl;
import com.metea.moneyanalysis.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthRestController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserServiceImpl customUserService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String creteToken(@RequestBody UserLoginDTO userLoginDTO, BindingResult bindingResult) throws UsernameNotFoundException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword()));

        final UserDetails userDetails = customUserService.loadUserByUsername(userLoginDTO.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }

    @PostMapping("/register")
    @ApiOperation(value = "Create User", response = UserReadDTO.class)
    public ResponseEntity<UserReadDTO> register(@Valid @RequestBody UserWriteDTO user) {
        return ResponseEntity.ok(userService.save(user));
    }
}
