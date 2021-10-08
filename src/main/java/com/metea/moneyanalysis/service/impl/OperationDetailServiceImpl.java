package com.metea.moneyanalysis.service.impl;

import com.metea.moneyanalysis.domain.BaseEntity;
import com.metea.moneyanalysis.domain.OperationDetail;
import com.metea.moneyanalysis.dto.OperationDetailSearchCriteriaDTO;
import com.metea.moneyanalysis.dto.OperationDetailWriteDTO;
import com.metea.moneyanalysis.exception.ServiceExecutionException;
import com.metea.moneyanalysis.repository.OperationDetailRepository;
import com.metea.moneyanalysis.repository.OperationMasterRepository;
import com.metea.moneyanalysis.service.OperationDetailService;
import com.metea.moneyanalysis.service.OperationMasterService;
import com.metea.moneyanalysis.service.UserService;
import com.metea.moneyanalysis.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationDetailServiceImpl implements OperationDetailService {

    private final OperationDetailRepository operationDetailRepository;
    private final OperationMasterRepository operationMasterRepository;
    private final OperationMasterService operationMasterService;
    private final UserService userService;
    private final MessageUtil messageUtil;

    @Override
    public OperationDetail save(OperationDetailWriteDTO operationDetailWriteDTO) {
        final var master = operationMasterRepository
                .findById(operationDetailWriteDTO.getMasterId());
        if (master.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("operationMaster.notFound.exception"));
        }
        final var operationDetail = new OperationDetail();
        operationDetail.setCreatedBy("Admin");
        operationDetail.setCreatedAt(new Date());
        operationDetail.setOperationType(operationDetailWriteDTO.getOperationType());
        operationDetail.setValue(operationDetailWriteDTO.getValue());
        operationDetail.setDescription(operationDetailWriteDTO.getDescription());
        operationDetail.setOperationMaster(master.get());
        operationMasterService.calculateAmount(operationDetail);
        operationDetailRepository.save(operationDetail);

        return operationDetail;
    }

    @Override
    public OperationDetail getById(Long id) {
        final var operationDetail = operationDetailRepository.findById(id);
        if (operationDetail.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("operationDetail.notFound.exception"));
        }
        return operationDetail.get();
    }

    @Override
    public List<OperationDetail> getAllByMasterId(Long masterId) {
        final var operationDetails = operationDetailRepository
                .findOperationDetailsByOperationMasterId(masterId);
        if (operationDetails.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("operationDetail.notFoundForMaster.exception"));
        }
        return operationDetails;
    }

    @Override
    public Boolean delete(Long id) {
        operationDetailRepository.deleteById(id);
        return true;
    }

    @Override
    public List<OperationDetail> getAllWeekly(Long masterId) {
        final var currentDate = new Date();
        return operationDetailRepository
                .findOperationDetailsByCreatedAtBetweenAndOperationMasterId(
                        getDateBeforeWeekAgo(currentDate), currentDate, masterId);
    }

    @Override
    public List<OperationDetail> getAllMonthly(Long masterId) {
        final var currentDate = new Date();
        return operationDetailRepository
                .findOperationDetailsByCreatedAtBetweenAndOperationMasterId(
                        getDateBeforeMonthAgo(currentDate), currentDate, masterId);
    }

    @Override
    public Page<OperationDetail> getAllRecordByUser() {
        final var masterId = operationMasterRepository
                .findOperationMasterByUserDetailId(userService.getSessionInfo().getId()).getId();
        final var details = operationDetailRepository.findAllByOperationMasterId(
                PageRequest.of(0, 100, Sort.by("createdAt").descending()), masterId);
        if (!details.hasContent()) {
            throw new ServiceExecutionException(messageUtil.get("operationDetail.notFoundForMaster.exception"));
        }
        return details;
    }

    @Override
    public Page<OperationDetail> search(OperationDetailSearchCriteriaDTO filter, Pageable pageable) {
        final var details = operationDetailRepository
                .findAll(filter.OperationMasterSearchCriteriaFieldMapper(filter), pageable);
        if (!details.hasContent()) {
            throw new ServiceExecutionException(messageUtil.get("operationDetail.notFound.exception"));
        }
        return details;
    }

    private Date getDateBeforeWeekAgo(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -7);
        return calendar.getTime();
    }

    private Date getDateBeforeMonthAgo(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -30);
        return calendar.getTime();
    }
}
