package com.metea.moneyanalysis.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserLoginDTO {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
}
