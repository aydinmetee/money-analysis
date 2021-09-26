package com.metea.moneyanalysis.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "user_det")
public class UserDetail extends BaseEntity {

    @Column(name = "username", length = 100, unique = true)
    private String username;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "name_surname", length = 200)
    private String nameSurname;

    @Column(name = "email", length = 100)
    private String email;

}
