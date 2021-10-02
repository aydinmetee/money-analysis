package com.metea.moneyanalysis.serviceview;

import com.metea.moneyanalysis.dto.OperationMasterReadDTO;
import com.metea.moneyanalysis.dto.OperationMasterSearchCriteriaDTO;
import com.metea.moneyanalysis.dto.OperationMasterWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationMasterServiceView {
    OperationMasterReadDTO save(OperationMasterWriteDTO operationMasterWriteDTO);

    OperationMasterReadDTO getById(Long id);

    OperationMasterReadDTO getMasterByUser();

    Boolean delete(Long id);

    Page<OperationMasterReadDTO> search(OperationMasterSearchCriteriaDTO filter, Pageable pageable);
}
