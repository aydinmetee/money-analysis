package com.metea.moneyanalysis.service.impl;

import com.metea.moneyanalysis.domain.BaseEntity;
import com.metea.moneyanalysis.domain.UserDetail;
import com.metea.moneyanalysis.dto.OperationMasterWriteDTO;
import com.metea.moneyanalysis.dto.UserLoginDTO;
import com.metea.moneyanalysis.dto.UserWriteDTO;
import com.metea.moneyanalysis.exception.LoginExecutionException;
import com.metea.moneyanalysis.exception.ServiceExecutionException;
import com.metea.moneyanalysis.repository.UserRepository;
import com.metea.moneyanalysis.service.OperationMasterService;
import com.metea.moneyanalysis.service.UserService;
import com.metea.moneyanalysis.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OperationMasterService operationMasterService;
    private final ModelMapper modelMapper;
    private final MessageUtil messageUtil;

    @Override
    public UserDetail save(UserWriteDTO userWriteDTO) {
        var userDB = new UserDetail();
        modelMapper.map(userWriteDTO, userDB);
        userDB.setCreatedBy("Admin");
        userDB.setCreatedAt(new Date());
        userDB.setStatus(BaseEntity.Status.NEW);
        userDB = userRepository.save(userDB);
        CreateOperationMasterForNewUser(userDB.getId());
        return userDB;
    }

    @Override
    public UserDetail getById(Long id) {
        final var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("userService.notFound.exception"));
        }
        return user.get();
    }

    @Override
    public UserDetail getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean delete(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean login(UserLoginDTO userLoginDTO) {
        final var userDB = userRepository.findByUsername(userLoginDTO.getUsername());
        if (Objects.isNull(userDB)) {
            throw new LoginExecutionException(messageUtil.get("loginService.notFound.exception"));
        }
        return userDB.getPassword().equals(userLoginDTO.getPassword());
    }

    @Override
    public UserDetail getSessionInfo() {
        final var userinfo = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getByUsername(userinfo.getUsername());
    }

    private void CreateOperationMasterForNewUser(Long userId) {
        final var operationMaster = new OperationMasterWriteDTO();
        operationMaster.setUserId(userId);
        operationMasterService.save(operationMaster);
    }


}
