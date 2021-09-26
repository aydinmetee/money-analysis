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
@Table(name = "operation_mst")
public class OperationMaster extends BaseEntity {

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private UserDetail userDetail;
}
