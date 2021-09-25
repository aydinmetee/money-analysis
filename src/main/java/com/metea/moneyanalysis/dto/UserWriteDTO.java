package com.metea.moneyanalysis.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserWriteDTO {
    private String password;
    private String username;
    private String nameSurname;
    private String email;
}
