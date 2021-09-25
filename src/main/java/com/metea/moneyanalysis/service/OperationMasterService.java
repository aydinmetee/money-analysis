package com.metea.moneyanalysis.service;

import com.metea.moneyanalysis.domain.OperationDetail;
import com.metea.moneyanalysis.domain.OperationMaster;
import com.metea.moneyanalysis.dto.OperationMasterReadDTO;
import com.metea.moneyanalysis.dto.OperationMasterWriteDTO;

import java.util.List;

public interface OperationMasterService {
    OperationMasterReadDTO save(OperationMasterWriteDTO operationMasterWriteDTO);

    OperationMasterReadDTO getById(Long id);

    List<OperationMasterReadDTO> getAllByUserId(Long userId);

    void calculateAmount(OperationDetail operationDetail);

    Boolean delete(Long id);
}
