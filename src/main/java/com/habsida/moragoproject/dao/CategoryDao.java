package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.CallRepository;
import com.habsida.moragoproject.dao.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryDao {

    CategoryRepository categoryRepository;

    public CategoryDao(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
