package com.ecommerce.project.serviceImpl;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.stereotype.Service;

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
}
