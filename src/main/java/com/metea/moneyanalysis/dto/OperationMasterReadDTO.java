package com.metea.moneyanalysis.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode
public class OperationMasterReadDTO extends OperationMasterWriteDTO {
    private Long id;

    private BigDecimal totalAmount;

    private Long userId;

    private String nameSurname;

}
