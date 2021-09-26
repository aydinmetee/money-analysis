package com.metea.moneyanalysis.repository;

import com.metea.moneyanalysis.domain.OperationMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationMasterRepository extends JpaRepository<OperationMaster, Long> {
    List<OperationMaster> findOperationMastersByUserDetailId(Long userId);

}
