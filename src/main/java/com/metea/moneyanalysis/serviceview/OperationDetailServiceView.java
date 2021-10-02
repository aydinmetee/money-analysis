package com.metea.moneyanalysis.serviceview;

import com.metea.moneyanalysis.dto.OperationDetailReadDTO;
import com.metea.moneyanalysis.dto.OperationDetailWriteDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OperationDetailServiceView {

    OperationDetailReadDTO save(OperationDetailWriteDTO operationDetailWriteDTO);

    OperationDetailReadDTO getById(Long id);

    List<OperationDetailReadDTO> getAllByMasterId(Long masterId);

    Boolean delete(Long id);

    List<OperationDetailReadDTO> getAllWeekly(Long masterId);

    List<OperationDetailReadDTO> getAllMonthly(Long masterId);

    Page<OperationDetailReadDTO> getAllRecordByUser();
}
