package com.metea.moneyanalysis.service;

import com.metea.moneyanalysis.dto.OperationDetailReadDTO;
import com.metea.moneyanalysis.dto.OperationDetailWriteDTO;

import java.util.List;

public interface OperationDetailService {
    OperationDetailReadDTO save(OperationDetailWriteDTO operationDetailWriteDTO);

    OperationDetailReadDTO getById(Long id);

    List<OperationDetailReadDTO> getAllByMasterId(Long masterId);

    Boolean delete(Long id);

    List<OperationDetailReadDTO> getAllWeekly(Long masterId);

    List<OperationDetailReadDTO> getAllMonthly(Long masterId);
}
