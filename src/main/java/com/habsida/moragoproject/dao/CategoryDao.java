package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.repository.CategoryRepository;
import com.habsida.moragoproject.model.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDao {

    CategoryRepository categoryRepository;

    public CategoryDao(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).get();
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public Category editCategory(Category category){
        Category categoryFromDB = categoryRepository.findById(category.getId()).get();
        if(category.getIsActive() != categoryFromDB.getIsActive()){
            categoryFromDB.setIsActive(category.getIsActive());
        }
        if(!category.getName().equals(categoryFromDB.getName())){
            categoryFromDB.setName(category.getName());
        }

        return categoryRepository.save(categoryFromDB);
    }
}