package com.habsida.moragoproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private Category category = new Category();

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private File file = new File();
}
