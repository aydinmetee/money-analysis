package com.metea.moneyanalysis.repository;

import com.metea.moneyanalysis.domain.OperationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OperationDetailRepository extends JpaRepository<OperationDetail, Long> {
    List<OperationDetail> findOperationDetailsByOperationMasterId(Long masterId);
    List<OperationDetail> findOperationDetailsByCreatedAtBetweenAndOperationMasterId(
            Date toDate,Date fromDate,Long masterId);
}