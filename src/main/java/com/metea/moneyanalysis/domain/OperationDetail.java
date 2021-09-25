package com.metea.moneyanalysis.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "operation_det")
public class OperationDetail extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private OperationType operationType;

    @Column(name = "value")
    private BigDecimal value;

    @JoinColumn(name = "master_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OperationMaster operationMaster;

    public enum OperationType {
        INCOME, EXPENSE
    }
}
