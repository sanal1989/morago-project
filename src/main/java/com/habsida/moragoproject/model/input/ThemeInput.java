package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.model.entity.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThemeInput {

    private String description;
    private String koreanTitle;
    private String name;
    private Boolean isActive;
    private Boolean isPopular;
    private Double nightPrice;
    private Double price;
    private Long category;
    private Long file;
}
