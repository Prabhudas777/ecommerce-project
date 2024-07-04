package com.ecommerce.project.serviceImpl;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    List<Category> categoryList = new ArrayList<>();
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public String createCategory(Category category) {
        categoryRepository.save(category);
        return "Category Added Successfully";
    }

    @Override
    public String deleteCateory(Long categoryId) {
        List<Category> categoryList =categoryRepository.findAll();
        Category category = categoryList.stream().filter(e->e.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));
        categoryRepository.delete(category);
        return "Deleted the cateory with Id "+category.getCategoryId();
    }

    @Override
    public String updateCategory(Category category, Long categoryId) {
        List<Category> categoryList =categoryRepository.findAll();
        Category savedCategory = categoryList.stream().filter(e->e.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));
        savedCategory.setCategoryId(categoryId);
        savedCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(savedCategory);
        return "Updated the category with Id: "+savedCategory.getCategoryId();
    }
}
