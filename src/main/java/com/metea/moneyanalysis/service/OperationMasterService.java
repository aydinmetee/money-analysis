package com.metea.moneyanalysis.service;

import com.metea.moneyanalysis.domain.OperationDetail;
import com.metea.moneyanalysis.domain.OperationMaster;
import com.metea.moneyanalysis.dto.OperationMasterWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationMasterService {
    OperationMaster save(OperationMasterWriteDTO operationMasterWriteDTO);

    OperationMaster getById(Long id);

    OperationMaster getMasterByUser();

    void calculateAmount(OperationDetail operationDetail);

    Boolean delete(Long id);

    Page<OperationMaster> search(Pageable pageable);
}
