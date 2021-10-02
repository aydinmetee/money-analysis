package com.metea.moneyanalysis.service;

import com.metea.moneyanalysis.domain.OperationDetail;
import com.metea.moneyanalysis.domain.OperationMaster;
import com.metea.moneyanalysis.dto.OperationMasterReadDTO;
import com.metea.moneyanalysis.dto.OperationMasterWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OperationMasterService {
    OperationMaster save(OperationMasterWriteDTO operationMasterWriteDTO);

    OperationMaster getById(Long id);

    List<OperationMaster> getAllByUserId(Long userId);

    void calculateAmount(OperationDetail operationDetail);

    Boolean delete(Long id);

    Page<OperationMaster> search(Pageable pageable);
}
