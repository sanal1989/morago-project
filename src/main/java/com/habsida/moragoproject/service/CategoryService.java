package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.CategoryDao;
import com.habsida.moragoproject.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> findAll(){
        return categoryDao.findAll();
    }

    public Category findById(Long id){
        return categoryDao.findById(id);
    }

    public Category addCategory(Category category){
        return categoryDao.addCategory(category);
    }

    public void deleteCategory(Long id){
        categoryDao.deleteCategory(id);
    }

    public Category editCategory(Category category){
        return categoryDao.editCategory(category);
    }
}
