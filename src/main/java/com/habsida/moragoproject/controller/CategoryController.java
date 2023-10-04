package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.model.input.CategoryInput;
import com.habsida.moragoproject.service.CategoryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @QueryMapping
    public List<Category> findAllCategory(){
        return categoryService.findAll();
    }

    @QueryMapping
    public Category findCategoryById(@Argument Long id){
        return categoryService.findById(id);
    }

    @MutationMapping
    public Category createCategory(@Argument CategoryInput categoryInput){
        return categoryService.createCategory(categoryInput);
    }

    @MutationMapping
    public String deleteCategoryById(@Argument Long id){
        return categoryService.deleteCategoryById(id);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument CategoryInput categoryInput){
        return categoryService.updateCategory(id, categoryInput);
    }
}
