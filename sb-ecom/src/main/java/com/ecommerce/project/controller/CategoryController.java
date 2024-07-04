package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> response = categoryService.getAllCategories();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        String response = categoryService.createCategory(category);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try{
            String response = categoryService.deleteCateory(categoryId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<String>(e.getReason(),e.getStatusCode());
        }
    }
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryId){
        try {
            String response =categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<String>(e.getReason(),e.getStatusCode());
        }
    }
}
