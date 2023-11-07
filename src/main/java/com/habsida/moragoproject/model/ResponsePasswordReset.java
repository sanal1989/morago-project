package com.habsida.moragoproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePasswordReset {
    private Long id;
    private LocalDateTime localDateTime;
    private String hashCode;
}
