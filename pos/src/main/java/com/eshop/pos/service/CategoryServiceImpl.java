package com.eshop.pos.service;

import com.eshop.pos.entity.Category;
import com.eshop.pos.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    //establishing connectivity with category repository
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategories() {

        List<Category> categories=categoryRepository.findAll();

        if(!categories.isEmpty()){
            return categories;
        }else{
            return null;
        }
    }

    @Override
    public Category createCategory(Category category) {

        return categoryRepository.save(category);
    }

    @Override
    public Category findCategoryById(Long id) {

        Category existingCategory=categoryRepository.findById(id).orElse(null);

        if(existingCategory!=null){
            return existingCategory;
        }else{
            return null;
        }

    }

    @Override
    public Category updateCategory(Long id, Category category) {

        Category existingCategory=categoryRepository.findById(id).orElse(null);

        if(existingCategory!=null) {
            existingCategory.setName(category.getName());

            return categoryRepository.save(existingCategory);
        }else{
            return null;
        }
    }

    @Override
    public String deleteCategory(Long id) {

        Boolean isExisted=categoryRepository.existsById(id);

        if(isExisted){
            categoryRepository.deleteById(id);
            return "Category Deleted Successfully";
        }else{
            return "No category found for entered id";
        }


    }
}
