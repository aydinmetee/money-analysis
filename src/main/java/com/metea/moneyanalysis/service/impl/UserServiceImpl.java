package com.metea.moneyanalysis.service.impl;

import com.metea.moneyanalysis.domain.BaseEntity;
import com.metea.moneyanalysis.domain.UserDetail;
import com.metea.moneyanalysis.dto.OperationMasterWriteDTO;
import com.metea.moneyanalysis.dto.UserLoginDTO;
import com.metea.moneyanalysis.dto.UserReadDTO;
import com.metea.moneyanalysis.dto.UserWriteDTO;
import com.metea.moneyanalysis.repository.UserRepository;
import com.metea.moneyanalysis.service.OperationMasterService;
import com.metea.moneyanalysis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OperationMasterService operationMasterService;
    private final ModelMapper modelMapper;

    @Override
    public UserReadDTO save(UserWriteDTO userWriteDTO) {
        var userDB = new UserDetail();
        modelMapper.map(userWriteDTO, userDB);
        userDB.setCreatedBy("Admin");
        userDB.setCreatedAt(new Date());
        userDB.setStatus(BaseEntity.Status.NEW);
        userDB = userRepository.save(userDB);
        CreateOperationMasterForNewUser(userDB.getId());
        return convertToDTO(userDB);
    }

    @Override
    public UserReadDTO getById(Long id) {
        final var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found!");
        }
        return convertToDTO(user.get());
    }

    @Override
    public UserReadDTO getByUsername(String username) {
        return convertToDTO(userRepository.findByUsername(username));
    }

    @Override
    public Boolean delete(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean login(UserLoginDTO userLoginDTO) {
        final var userDB = userRepository.findByUsername(userLoginDTO.getUserName());
        if (Objects.isNull(userDB)) {
            throw new IllegalArgumentException("Username not found!");
        }
        if (userDB.getPassword().equals(userLoginDTO.getPassword())) {
            return true;
        }
        return false;
    }

    private void CreateOperationMasterForNewUser(Long userId) {
        final var operationMaster = new OperationMasterWriteDTO();
        operationMaster.setUserId(userId);
        operationMasterService.save(operationMaster);
    }

    private UserReadDTO convertToDTO(UserDetail userDetail) {
        final var userReadDTO = new UserReadDTO();
        modelMapper.map(userDetail, userReadDTO);
        return userReadDTO;
    }

}
