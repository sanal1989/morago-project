package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.entity.Category;
import com.habsida.moragoproject.service.CategoryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
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
    public Category addCategory(@Argument Boolean isActive, @Argument String name){
        Category category = new Category();
        category.setActive(isActive);
        category.setName(name);
        return categoryService.addCategory(category);
    }

    @MutationMapping
    public void deleteCategory(@Argument Long id){
        categoryService.deleteCategory(id);
    }

    @MutationMapping
    public Category editCategory(Category category){
        return categoryService.editCategory(category);
    }
}
