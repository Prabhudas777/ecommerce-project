package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories();
    String createCategory(Category category);

    String deleteCateory(Long categoryId);

    String updateCategory(Category category, Long categoryId);
}
