package com.metea.moneyanalysis.domain;

import lombok.*;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "username", length = 100, unique = true)
    private String username;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "name_surname", length = 200)
    private String nameSurname;

    @Column(name = "email", length = 100)
    private String email;

}
