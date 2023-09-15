package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileInput {

    private String originalTitle;
    private String path;
    private String type;

    private Long user;
}
