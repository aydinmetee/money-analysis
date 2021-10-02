package com.metea.moneyanalysis.serviceview.impl;

import com.metea.moneyanalysis.domain.OperationDetail;
import com.metea.moneyanalysis.dto.OperationDetailReadDTO;
import com.metea.moneyanalysis.dto.OperationDetailSearchCriteriaDTO;
import com.metea.moneyanalysis.dto.OperationDetailWriteDTO;
import com.metea.moneyanalysis.service.OperationDetailService;
import com.metea.moneyanalysis.serviceview.OperationDetailServiceView;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationDetailServiceViewImpl implements OperationDetailServiceView {
    private final OperationDetailService operationDetailService;
    private final ModelMapper modelMapper;

    @Override
    public OperationDetailReadDTO save(OperationDetailWriteDTO operationDetailWriteDTO) {
        return convertToDto(operationDetailService.save(operationDetailWriteDTO));
    }

    @Override
    public OperationDetailReadDTO getById(Long id) {
        return convertToDto(operationDetailService.getById(id));
    }

    @Override
    public List<OperationDetailReadDTO> getAllByMasterId(Long masterId) {
        return operationDetailService.getAllByMasterId(masterId).stream()
                .map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        return operationDetailService.delete(id);
    }

    @Override
    public List<OperationDetailReadDTO> getAllWeekly(Long masterId) {
        return operationDetailService.getAllWeekly(masterId).stream()
                .map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<OperationDetailReadDTO> getAllMonthly(Long masterId) {
        return operationDetailService.getAllMonthly(masterId).stream()
                .map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Page<OperationDetailReadDTO> getAllRecordByUser() {
        return operationDetailService.getAllRecordByUser().map(this::convertToDto);
    }

    @Override
    public Page<OperationDetailReadDTO> search(OperationDetailSearchCriteriaDTO filter, Pageable pageable) {
        return operationDetailService.search(filter, pageable).map(this::convertToDto);
    }

    private OperationDetailReadDTO convertToDto(OperationDetail operationDetail) {
        final var operationDetailDTO = new OperationDetailReadDTO();
        modelMapper.map(operationDetail, operationDetailDTO);
        operationDetailDTO.setMasterId(operationDetail.getOperationMaster().getId());
        operationDetailDTO.setTotalAmount(operationDetail.getOperationMaster().getTotalAmount());
        return operationDetailDTO;
    }
}
