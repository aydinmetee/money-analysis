package com.metea.moneyanalysis.serviceview.impl;

import com.metea.moneyanalysis.domain.UserDetail;
import com.metea.moneyanalysis.dto.UserLoginDTO;
import com.metea.moneyanalysis.dto.UserReadDTO;
import com.metea.moneyanalysis.dto.UserWriteDTO;
import com.metea.moneyanalysis.service.UserService;
import com.metea.moneyanalysis.serviceview.UserServiceView;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceViewImpl implements UserServiceView {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public UserReadDTO save(UserWriteDTO userWriteDTO) {
        return convertToDTO(userService.save(userWriteDTO));
    }

    @Override
    public UserReadDTO getById(Long id) {
        return convertToDTO(userService.getById(id));
    }

    @Override
    public UserReadDTO getByUsername(String username) {
        return convertToDTO(userService.getByUsername(username));
    }

    @Override
    public Boolean delete(Long id) {
        return userService.delete(id);
    }

    @Override
    public Boolean login(UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @Override
    public UserReadDTO getSessionInfo() {
        return convertToDTO(userService.getSessionInfo());
    }

    private UserReadDTO convertToDTO(UserDetail userDetail) {
        final var userReadDTO = new UserReadDTO();
        modelMapper.map(userDetail, userReadDTO);
        return userReadDTO;
    }
}
