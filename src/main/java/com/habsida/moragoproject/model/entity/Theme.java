package com.habsida.moragoproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Theme extends AbstractAuditable{

    private String description;
    private Boolean isActive;
    private Boolean isPopular;
    private String koreanTitle;
    private String name;
    private Double nightPrice;
    private Double price;

    @ManyToOne
    private Category category = new Category();

    @OneToOne
    private File file = new File();
}
