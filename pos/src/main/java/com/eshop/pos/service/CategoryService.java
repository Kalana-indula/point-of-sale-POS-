package com.eshop.pos.service;

import com.eshop.pos.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    //Getting all categories
    List<Category> findAllCategories();

    //create a category
    Category createCategory(Category category);

    //get category by id
    Category findCategoryById(Long id);

    //update category
    Category updateCategory(Long id,Category category);

    //delete existing category
    String deleteCategory(Long id);
}
