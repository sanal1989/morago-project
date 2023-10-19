package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.model.input.CategoryInput;
import com.habsida.moragoproject.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundByIdException("Category -> Category doesn't find by Id " +id));
    }

    public Category createCategory(CategoryInput categoryInput){
        Category category = new Category();
        if(!isNull(categoryInput.getIsActive())){
            category.setIsActive(categoryInput.getIsActive());
        }else {
            category.setIsActive(false);
        }
        if(!isNull(categoryInput.getName()) && !categoryInput.getName().isEmpty()){
            category.setName(categoryInput.getName());
        }
        return categoryRepository.save(category);
    }

    public String deleteCategoryById(Long id){
        try{
            categoryRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "Category with Id "+id+" deleted";
    }

    public Category updateCategory(Long id, CategoryInput categoryInput){
        Category category = categoryRepository.findById(id).get();
        if(!isNull(categoryInput.getIsActive())){
            category.setIsActive(categoryInput.getIsActive());
        }else {
            category.setIsActive(false);
        }
        if(!isNull(categoryInput.getName()) && !categoryInput.getName().isEmpty()){
            category.setName(categoryInput.getName());
        }
        return categoryRepository.save(category);
    }
}
