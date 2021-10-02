package com.metea.moneyanalysis.serviceview;

import com.metea.moneyanalysis.dto.OperationMasterReadDTO;
import com.metea.moneyanalysis.dto.OperationMasterWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OperationMasterServiceView {
    OperationMasterReadDTO save(OperationMasterWriteDTO operationMasterWriteDTO);

    OperationMasterReadDTO getById(Long id);

    List<OperationMasterReadDTO> getAllByUserId(Long userId);

    Boolean delete(Long id);

    Page<OperationMasterReadDTO> search(Pageable pageable);
}
