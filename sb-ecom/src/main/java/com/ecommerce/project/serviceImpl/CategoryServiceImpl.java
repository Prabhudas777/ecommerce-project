package com.ecommerce.project.serviceImpl;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.service.CategoryService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty())
            throw new APIException("Category List is Empty, Please add the categories!!");
        List<CategoryDTO> categoryDTOS = categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public String createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory!=null)
            throw new APIException("Category with category name : "+category.getCategoryName()+" is already Exists!!");
        categoryRepository.save(category);
        return "Category Added Successfully";
    }

    @Override
    public String deleteCateory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("resourceName","field","fieldName"));
        categoryRepository.delete(category);
        return "Deleted the cateory with Id "+category.getCategoryId();
    }

    @Override
    public String updateCategory(Category category, Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("resourceName","field",categoryId));
        savedCategory.setCategoryId(categoryId);
        savedCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(savedCategory);
        return "Updated the category with Id: "+savedCategory.getCategoryId();
    }
}
