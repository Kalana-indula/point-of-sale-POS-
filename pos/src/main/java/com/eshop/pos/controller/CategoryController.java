package com.eshop.pos.controller;

import com.eshop.pos.entity.Category;
import com.eshop.pos.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    //establishing connectivity with category service
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> findAllCategories(){

        List<Category> categories=categoryService.findAllCategories();

        try{
            if(categories!=null){
                return ResponseEntity.status(HttpStatus.FOUND).body(categories);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No categories found in records");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    //create a category
    @PostMapping("/categories")
    public ResponseEntity<?> creeateCategory(@RequestBody Category category){

        Category newCategory=categoryService.createCategory(category);

        try{
           if(newCategory!=null){
               return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
           }else{
               return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Please check details, entries are null");
           }
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //find category by id
    @GetMapping("/categories/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long id){

        Category category= categoryService.findCategoryById(id);

        try{
            if(category!=null){
                return ResponseEntity.status(HttpStatus.FOUND).body(category);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No category found for entered id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //update existing category
    @PutMapping("/update/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,@RequestBody Category category){

        Category updatedCategory=categoryService.updateCategory(id,category);

        try {
            if(updatedCategory!=null){
                return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No category found for entered id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //delete category
    @DeleteMapping("/delete/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategory(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

}
