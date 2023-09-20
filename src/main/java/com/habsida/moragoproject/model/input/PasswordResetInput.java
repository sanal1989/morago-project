package com.habsida.moragoproject.model.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetInput {

    private String phone;
    private String token;
    private Integer resetCode;
}
