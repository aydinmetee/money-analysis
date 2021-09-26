package com.metea.moneyanalysis.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
