package com.metea.moneyanalysis.dto;

import com.metea.moneyanalysis.domain.OperationDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode
public class OperationDetailWriteDTO {
    private String description;
    private OperationDetail.OperationType operationType;
    private BigDecimal value;
    private Long masterId;
}
