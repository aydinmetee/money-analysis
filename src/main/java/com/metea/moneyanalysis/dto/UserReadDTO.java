package com.metea.moneyanalysis.dto;

import com.metea.moneyanalysis.domain.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserReadDTO {
    private Long id;
    private String username;
    private String nameSurname;
    private String email;
    private BaseEntity.Status status;
    private Date creDate;
}
