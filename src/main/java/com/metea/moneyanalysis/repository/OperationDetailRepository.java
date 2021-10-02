package com.metea.moneyanalysis.repository;

import com.metea.moneyanalysis.domain.OperationDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface OperationDetailRepository extends JpaRepository<OperationDetail, Long>,
        JpaSpecificationExecutor<OperationDetail> {
    List<OperationDetail> findOperationDetailsByOperationMasterId(Long masterId);

    List<OperationDetail> findOperationDetailsByCreatedAtBetweenAndOperationMasterId(
            Date toDate, Date fromDate, Long masterId);

    Page<OperationDetail> findAllByOperationMasterId(Pageable pageable, Long id);
}
