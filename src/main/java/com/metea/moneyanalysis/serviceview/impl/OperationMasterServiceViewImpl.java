package com.metea.moneyanalysis.serviceview.impl;

import com.metea.moneyanalysis.domain.OperationMaster;
import com.metea.moneyanalysis.dto.OperationMasterReadDTO;
import com.metea.moneyanalysis.dto.OperationMasterWriteDTO;
import com.metea.moneyanalysis.service.OperationMasterService;
import com.metea.moneyanalysis.serviceview.OperationMasterServiceView;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationMasterServiceViewImpl implements OperationMasterServiceView {

    private final OperationMasterService operationMasterService;
    private final ModelMapper modelMapper;

    @Override
    public OperationMasterReadDTO save(OperationMasterWriteDTO operationMasterWriteDTO) {
        return convertToDto(operationMasterService.save(operationMasterWriteDTO));
    }

    @Override
    public OperationMasterReadDTO getById(Long id) {
        return convertToDto(operationMasterService.getById(id));
    }

    @Override
    public List<OperationMasterReadDTO> getAllByUserId(Long userId) {
        return operationMasterService.getAllByUserId(userId).stream()
                .map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        return operationMasterService.delete(id);
    }

    @Override
    public Page<OperationMasterReadDTO> search(Pageable pageable) {
        return operationMasterService.search(pageable).map(this::convertToDto);
    }

    private OperationMasterReadDTO convertToDto(OperationMaster operationMaster) {
        final var operationMasterReadDTO = new OperationMasterReadDTO();
        modelMapper.map(operationMaster, operationMasterReadDTO);
        operationMasterReadDTO.setUserId(operationMaster.getUserDetail().getId());
        operationMasterReadDTO.setNameSurname(operationMaster.getUserDetail().getNameSurname());
        return operationMasterReadDTO;
    }
}
