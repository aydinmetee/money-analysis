package com.metea.moneyanalysis.repository;

import com.metea.moneyanalysis.domain.OperationMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface OperationMasterRepository extends JpaRepository<OperationMaster, Long>,
        JpaSpecificationExecutor<OperationMaster> {
    OperationMaster findOperationMasterByUserDetailId(Long userId);

    Page<OperationMaster> findAll(Pageable pageable);

}
