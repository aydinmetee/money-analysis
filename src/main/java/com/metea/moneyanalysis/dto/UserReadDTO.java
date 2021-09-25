package com.metea.moneyanalysis.dto;

import com.metea.moneyanalysis.domain.BaseEntity;

import java.util.Date;

public class UserReadDTO {
    private String id;
    private String username;
    private String nameSurname;
    private String email;
    private BaseEntity.Status status;
    private Date creDate;
}
