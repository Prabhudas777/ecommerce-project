package com.ecommerce.project.serviceImpl;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    List<Category> categoryList = new ArrayList<>();
    @Override
    public List<Category> getAllCategories() {
        return categoryList;
    }

    @Override
    public String createCategory(Category category) {
        categoryList.add(category);
        return "Category Added Successfully";
    }

    @Override
    public String deleteCateory(Long categoryId) {
        Category category = categoryList.stream().filter(e->e.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));
        categoryList.remove(category);
        return "Deleted the cateory with Id "+category.getCategoryId();
    }

    @Override
    public String updateCategory(Category category, Long categoryId) {
        Category savedCategory = categoryList.stream().filter(e->e.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));
        savedCategory.setCategoryId(categoryId);
        savedCategory.setCategoryName(category.getCategoryName());
        return "Updated the category with Id: "+savedCategory.getCategoryId();
    }
}
