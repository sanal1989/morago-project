package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.enums.EPlatform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppVersionInput {

    private String platform;
    private String latest;
    private String min;
}
