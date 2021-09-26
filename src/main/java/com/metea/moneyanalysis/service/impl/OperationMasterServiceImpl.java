package com.metea.moneyanalysis.service.impl;

import com.metea.moneyanalysis.domain.BaseEntity;
import com.metea.moneyanalysis.domain.OperationDetail;
import com.metea.moneyanalysis.domain.OperationMaster;
import com.metea.moneyanalysis.domain.UserDetail;
import com.metea.moneyanalysis.dto.OperationMasterReadDTO;
import com.metea.moneyanalysis.dto.OperationMasterWriteDTO;
import com.metea.moneyanalysis.repository.OperationMasterRepository;
import com.metea.moneyanalysis.service.OperationMasterService;
import com.metea.moneyanalysis.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OperationMasterServiceImpl implements OperationMasterService {

    private final OperationMasterRepository operationMasterRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public OperationMasterServiceImpl(OperationMasterRepository operationMasterRepository,
                                      @Lazy UserService userService, ModelMapper modelMapper) {
        this.operationMasterRepository = operationMasterRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public OperationMasterReadDTO save(OperationMasterWriteDTO operationMasterWriteDTO) {
        final var operationMaster = new OperationMaster();
        operationMaster.setUserDetail(findUser(operationMasterWriteDTO.getUserId()));
        operationMaster.setCreatedBy("Admin");
        operationMaster.setCreatedAt(new Date());
        operationMaster.setStatus(BaseEntity.Status.NEW);
        operationMaster.setTotalAmount(BigDecimal.ZERO);
        operationMasterRepository.save(operationMaster);
        return prepareDTO(operationMaster);
    }

    @Override
    public OperationMasterReadDTO getById(Long id) {
        final var operationMaster = operationMasterRepository.findById(id);
        if (operationMaster.isEmpty()) {
            throw new IllegalArgumentException("Operation Not Found!");
        }
        return prepareDTO(operationMaster.get());
    }

    @Override
    public List<OperationMasterReadDTO> getAllByUserId(Long userId) {
        final var operationMasters = operationMasterRepository.findOperationMastersByUserDetailId(userId);
        if (operationMasters.isEmpty()) {
            throw new IllegalArgumentException("User have not operation!");
        }
        final var dtoList = new ArrayList<OperationMasterReadDTO>();
        operationMasters.forEach(operationMaster -> dtoList.add(prepareDTO(operationMaster)));
        return dtoList;
    }

    @Override
    public void calculateAmount(OperationDetail operationDetail) {
        final var operationMaster = operationMasterRepository
                .findById(operationDetail.getOperationMaster().getId());
        if (operationMaster.isEmpty()) {
            throw new IllegalArgumentException("Kullanıcı bulunamadı");
        }
        if (operationDetail.getOperationType().equals(OperationDetail.OperationType.INCOME)) {
            operationMaster.get().setTotalAmount(operationMaster.get().getTotalAmount().add(operationDetail.getValue()));
        } else {
            operationMaster.get().setTotalAmount(
                    operationMaster.get().getTotalAmount().subtract(operationDetail.getValue()));
        }
        operationMasterRepository.save(operationMaster.get());
    }

    @Override
    public Boolean delete(Long id) {
        operationMasterRepository.deleteById(id);
        return true;
    }

    private OperationMasterReadDTO prepareDTO(OperationMaster operationMaster) {
        final var operationMasterReadDTO = new OperationMasterReadDTO();
        modelMapper.map(operationMaster, operationMasterReadDTO);
        operationMasterReadDTO.setUserId(operationMaster.getUserDetail().getId());
        operationMasterReadDTO.setNameSurname(operationMaster.getUserDetail().getNameSurname());
        return operationMasterReadDTO;
    }

    private UserDetail findUser(Long id) {
        final var userDTO = userService.getById(id);
        if (Objects.isNull(userDTO)) {
            throw new IllegalArgumentException("User Not Found!");
        }
        final var user = new UserDetail();
        modelMapper.map(userDTO, user);
        return user;
    }
}
