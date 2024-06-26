package com.webtutsplus.ecommerce.controller;



import com.webtutsplus.ecommerce.common.ApiResponse;
import com.webtutsplus.ecommerce.dto.product.ProductDto;
import com.webtutsplus.ecommerce.exceptions.ErrorDetails;
import com.webtutsplus.ecommerce.model.Category;
import com.webtutsplus.ecommerce.model.Product;
import com.webtutsplus.ecommerce.repository.Categoryrepository;
import com.webtutsplus.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    Categoryrepository categoryRepository;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public Product getUserById(@PathVariable int id) {
        Product product = productService.getUserById(id);
		return product;
      }
    @PostMapping("/add")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            
            for (FieldError error : bindingResult.getFieldErrors()) {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            }

            // Creating an error message string for the overall error
            String errorMessage = "Validation failed for fields: " + errors.keySet().toString();
            ErrorDetails errorDetails = new ErrorDetails(errorMessage, LocalDateTime.now(), errors);
            return ResponseEntity.badRequest().body(errorDetails);
        }

        Product createdProduct = productService.createProduct(productDto); // Corrected service method
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID, @RequestBody @Valid ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.BAD_REQUEST);
        }
       // Category category = optionalCategory.get();
        productService.updateProduct(productID, productDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }
}
