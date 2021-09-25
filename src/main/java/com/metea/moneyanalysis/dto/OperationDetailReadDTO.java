package com.metea.moneyanalysis.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode
public class OperationDetailReadDTO extends OperationDetailWriteDTO {
    private Long id;

    private BigDecimal totalAmount;
}
