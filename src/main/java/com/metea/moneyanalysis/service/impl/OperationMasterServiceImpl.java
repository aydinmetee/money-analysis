package com.metea.moneyanalysis.service.impl;

import com.metea.moneyanalysis.domain.OperationDetail;
import com.metea.moneyanalysis.domain.OperationMaster;
import com.metea.moneyanalysis.domain.UserDetail;
import com.metea.moneyanalysis.dto.OperationMasterSearchCriteriaDTO;
import com.metea.moneyanalysis.dto.OperationMasterWriteDTO;
import com.metea.moneyanalysis.exception.ServiceExecutionException;
import com.metea.moneyanalysis.repository.OperationMasterRepository;
import com.metea.moneyanalysis.service.OperationMasterService;
import com.metea.moneyanalysis.service.UserService;
import com.metea.moneyanalysis.util.MessageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Service
public class OperationMasterServiceImpl implements OperationMasterService {

    private final OperationMasterRepository operationMasterRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final MessageUtil messageUtil;

    public OperationMasterServiceImpl(OperationMasterRepository operationMasterRepository,
                                      @Lazy UserService userService, ModelMapper modelMapper, MessageUtil messageUtil) {
        this.operationMasterRepository = operationMasterRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.messageUtil = messageUtil;
    }

    @Override
    public OperationMaster save(OperationMasterWriteDTO operationMasterWriteDTO) {
        final var operationMaster = new OperationMaster();
        operationMaster.setUserDetail(findUser(operationMasterWriteDTO.getUserId()));
        operationMaster.setCreatedBy("Admin");
        operationMaster.setCreatedAt(new Date());
        operationMaster.setTotalAmount(BigDecimal.ZERO);
        return operationMasterRepository.save(operationMaster);
    }

    @Override
    public OperationMaster getById(Long id) {
        final var operationMaster = operationMasterRepository.findById(id);
        if (operationMaster.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("operationMaster.notFound.exception"));
        }
        return operationMaster.get();
    }

    @Override
    public OperationMaster getMasterByUser() {
        final var operationMaster = operationMasterRepository
                .findOperationMasterByUserDetailId(userService.getSessionInfo().getId());
        if (Objects.isNull(operationMaster)) {
            throw new ServiceExecutionException(messageUtil.get("operationMaster.notFoundForUser.exception"));
        }
        return operationMaster;
    }

    @Override
    public void calculateAmount(OperationDetail operationDetail) {
        final var operationMaster = operationMasterRepository
                .findById(operationDetail.getOperationMaster().getId());
        if (operationMaster.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("operationMaster.notFound.exception"));
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

    @Override
    public Page<OperationMaster> search(OperationMasterSearchCriteriaDTO filter, Pageable pageable) {
        return operationMasterRepository.findAll(filter.OperationMasterSearchCriteriaFieldMapper(filter), pageable);
    }

    private UserDetail findUser(Long id) {
        final var userDTO = userService.getById(id);
        if (Objects.isNull(userDTO)) {
            throw new ServiceExecutionException(messageUtil.get("userService.notFound.exception"));
        }
        final var user = new UserDetail();
        modelMapper.map(userDTO, user);
        return user;
    }
}
