package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.model.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role extends AbstractAuditable{

    @Enumerated(EnumType.ORDINAL)
    private ERole name;

}
