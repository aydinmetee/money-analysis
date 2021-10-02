package com.metea.moneyanalysis.service;

import com.metea.moneyanalysis.domain.OperationDetail;
import com.metea.moneyanalysis.dto.OperationDetailSearchCriteriaDTO;
import com.metea.moneyanalysis.dto.OperationDetailWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OperationDetailService {
    OperationDetail save(OperationDetailWriteDTO operationDetailWriteDTO);

    OperationDetail getById(Long id);

    List<OperationDetail> getAllByMasterId(Long masterId);

    Boolean delete(Long id);

    List<OperationDetail> getAllWeekly(Long masterId);

    List<OperationDetail> getAllMonthly(Long masterId);

    Page<OperationDetail> getAllRecordByUser();

    Page<OperationDetail> search(OperationDetailSearchCriteriaDTO filter, Pageable pageable);
}
