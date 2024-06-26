package com.webtutsplus.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtutsplus.ecommerce.model.Category;
import com.webtutsplus.ecommerce.model.Product;
import com.webtutsplus.ecommerce.repository.Categoryrepository;
import com.webtutsplus.ecommerce.repository.ProductRepository;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private Categoryrepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public CategoryService(Categoryrepository categoryrepository) {
		this.categoryRepository = categoryrepository;
	}
    
    @Transactional
    public Category createCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        for (Product product : savedCategory.getProducts()) {
            product.setCategory(savedCategory);
        }
        productRepository.saveAll(savedCategory.getProducts());
        return savedCategory;
    }
	
	
	public List<Category> listCategories() {
		return categoryRepository.findAll();
		
	}

	public Category readCategory(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName);
	}

	public Optional<Category> readCategory(Integer categoryId) {
		return categoryRepository.findById(categoryId);
	}

	public void updateCategory(Integer categoryID, Category newCategory) {
	    Optional<Category> optionalCategory = categoryRepository.findById(categoryID);
	    if (!optionalCategory.isPresent()) {
	        throw new RuntimeException("Category not found");
	    }

	    Category category = optionalCategory.get();
	    category.setCategoryName(newCategory.getCategoryName());
	    category.setDescription(newCategory.getDescription());
	    category.setImageUrl(newCategory.getImageUrl());

	    // Update the products with the current category
	    for (Product product : newCategory.getProducts()) {
	        product.setCategory(category);
	    }
	    category.setProducts(newCategory.getProducts());

	    categoryRepository.save(category);
	}


	public Category getById(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).orElse(null);
	}
}
