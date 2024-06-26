package com.webtutsplus.ecommerce.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.webtutsplus.ecommerce.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.webtutsplus.ecommerce.common.ApiResponse;
import com.webtutsplus.ecommerce.exceptions.ErrorDetails;
import com.webtutsplus.ecommerce.model.Category;
import com.webtutsplus.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")

public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/getall")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
    }
	@GetMapping("/getbyid/{id}")
	public Category getById(@Valid @PathVariable int id) {
		Category c1=categoryService.getById(id);
		return c1;
		
	}

	@PostMapping("/categories")
	public ResponseEntity<Object> createCategory(@Valid @RequestBody Category category, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        Map<String, String> errors = new HashMap<>();
	        Map<String, String> fieldErrors = new HashMap<>();
	        
	        for (FieldError error : bindingResult.getFieldErrors()) {
	            String fieldName = error.getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	            fieldErrors.put(fieldName, null);  // Null values for fields with errors
	        }

	        // Creating an error message string for the overall error
	        String errorMessage = "Validation failed for fields: " + errors.keySet().toString();
	        ErrorDetails errorDetails = new ErrorDetails(errorMessage, LocalDateTime.now(), errors);
	        return ResponseEntity.badRequest().body(errorDetails);
	    }

	    Category createdCategory = categoryService.createCategory(category);
	    return ResponseEntity.ok(createdCategory);
	}



	@PutMapping("/update/{categoryID}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID,@Valid @RequestBody Category category) {
		// Check to see if the category exists.
		if (Helper.notNull(categoryService.readCategory(categoryID))) {
			// If the category exists then update it.
			categoryService.updateCategory(categoryID, category);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
		}

		// If the category doesn't exist then return a response of unsuccessful.
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
	}
}
