package com.metea.moneyanalysis.service.impl;

import com.metea.moneyanalysis.domain.BaseEntity;
import com.metea.moneyanalysis.domain.OperationDetail;
import com.metea.moneyanalysis.dto.OperationDetailReadDTO;
import com.metea.moneyanalysis.dto.OperationDetailWriteDTO;
import com.metea.moneyanalysis.repository.OperationDetailRepository;
import com.metea.moneyanalysis.repository.OperationMasterRepository;
import com.metea.moneyanalysis.service.OperationDetailService;
import com.metea.moneyanalysis.service.OperationMasterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationDetailServiceImpl implements OperationDetailService {

    private final OperationDetailRepository operationDetailRepository;
    private final OperationMasterRepository operationMasterRepository;
    private final OperationMasterService operationMasterService;
    private final ModelMapper modelMapper;

    @Override
    public OperationDetailReadDTO save(OperationDetailWriteDTO operationDetailWriteDTO) {
        final var master = operationMasterRepository
                .findById(operationDetailWriteDTO.getMasterId());
        if (master.isEmpty()) {
            throw new IllegalArgumentException("User Not Found!");
        }
        final var operationDetail = new OperationDetail();
        operationDetail.setCreatedBy("Admin");
        operationDetail.setCreatedAt(new Date());
        operationDetail.setStatus(BaseEntity.Status.NEW);
        operationDetail.setOperationType(operationDetailWriteDTO.getOperationType());
        operationDetail.setValue(operationDetailWriteDTO.getValue());
        operationDetail.setDescription(operationDetailWriteDTO.getDescription());
        operationDetail.setOperationMaster(master.get());
        operationMasterService.calculateAmount(operationDetail);
        operationDetailRepository.save(operationDetail);

        return prepareDTO(operationDetail);
    }

    @Override
    public OperationDetailReadDTO getById(Long id) {
        final var operationDetail = operationDetailRepository.findById(id);
        if (operationDetail.isEmpty()) {
            throw new IllegalArgumentException("Operation Not Found!");
        }
        return prepareDTO(operationDetail.get());
    }

    @Override
    public List<OperationDetailReadDTO> getAllByMasterId(Long masterId) {
        final var operationDetails = operationDetailRepository.findOperationDetailsByOperationMasterId(masterId);
        if (operationDetails.isEmpty()) {
            throw new IllegalArgumentException("User have not operation!");
        }
        return prepareList(operationDetails);
    }

    @Override
    public Boolean delete(Long id) {
        operationDetailRepository.deleteById(id);
        return true;
    }

    @Override
    public List<OperationDetailReadDTO> getAllWeekly(Long masterId) {
        final var currentDate = new Date();
        final var operationDetails = operationDetailRepository
                .findOperationDetailsByCreatedAtBetweenAndOperationMasterId(
                        getDateBeforeWeekAgo(currentDate), currentDate, masterId);
        return prepareList(operationDetails);
    }

    @Override
    public List<OperationDetailReadDTO> getAllMonthly(Long masterId) {
        final var currentDate = new Date();
        final var operationDetails = operationDetailRepository
                .findOperationDetailsByCreatedAtBetweenAndOperationMasterId(
                        getDateBeforeMonthAgo(currentDate), currentDate, masterId);
        return prepareList(operationDetails);
    }

    private OperationDetailReadDTO prepareDTO(OperationDetail operationDetail) {
        final var operationDetailDTO = new OperationDetailReadDTO();
        modelMapper.map(operationDetail, operationDetailDTO);
        operationDetailDTO.setMasterId(operationDetail.getOperationMaster().getId());
        operationDetailDTO.setTotalAmount(operationDetail.getOperationMaster().getTotalAmount());
        return operationDetailDTO;

    }

    private List<OperationDetailReadDTO> prepareList(List<OperationDetail> operationDetailList) {
        final var dtoList = new ArrayList<OperationDetailReadDTO>();
        operationDetailList.forEach(operationDetail -> dtoList.add(prepareDTO(operationDetail)));
        return dtoList;
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
