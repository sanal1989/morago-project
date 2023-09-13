package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.CallRepository;
import com.habsida.moragoproject.dao.repository.CategoryRepository;
import com.habsida.moragoproject.entity.Call;
import com.habsida.moragoproject.entity.Category;
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
        if(category.getActive() != categoryFromDB.getActive()){
            categoryFromDB.setActive(category.getActive());
        }
        if(!category.getName().equals(categoryFromDB.getName())){
            categoryFromDB.setName(category.getName());
        }

        return categoryRepository.save(categoryFromDB);
    }
}