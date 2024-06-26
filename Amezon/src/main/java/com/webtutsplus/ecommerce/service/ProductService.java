package com.webtutsplus.ecommerce.service;

import com.webtutsplus.ecommerce.dto.product.ProductDto;
import com.webtutsplus.ecommerce.exceptions.ProductNotExistException;
import com.webtutsplus.ecommerce.model.Category;
import com.webtutsplus.ecommerce.model.Product;
import com.webtutsplus.ecommerce.repository.Categoryrepository;
import com.webtutsplus.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private Categoryrepository categoryRepository;

    @Transactional
    public Product createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = new Product(productDto, category);
        return productRepository.save(product);
    }

    
    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto productDto = getDtoFromProduct(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public static ProductDto getDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        return product;
    }

    public void createProduct(ProductDto productDto, Category category) {
    	Product product =new Product();
    	product.setDescription(productDto.getDescription());
    	product.setImageURL(productDto.getImageURL());
    	product.setName(productDto.getName());
        product.setCategory(product.getCategory());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

   /*-- public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        product.setId(productID);
        productRepository.save(product);
    }---*/


    public ProductDto  getProductDto(Product product) {
    	ProductDto productDto =new ProductDto();
    	productDto.setDescription(productDto.getDescription());
    	productDto.setImageURL(productDto.getImageURL());
    	productDto.setName(productDto.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(productDto.getPrice());
        return productDto;
    }
    public List<ProductDto> getAllProducts(){
    	List<Product> allProducts =productRepository.findAll();
    	
    	List<ProductDto> productDtos=new ArrayList<>();
    	for(Product product:allProducts) {
    		productDtos.add(getProductDto(product));
    	}
    	return productDtos;
    }

	public void updateProduct(Integer productID, @Valid ProductDto productDto) throws Exception {
		// TODO Auto-generated method stub
		Optional<Product>product=productRepository.findById(productID);
		if(!product.isPresent()) {
			throw new Exception("product not present ");
		}
		Product product1 =new Product();
    	product1.setDescription(productDto.getDescription());
    	product1.setImageURL(productDto.getImageURL());
    	product1.setName(productDto.getName());
     
        product1.setPrice(productDto.getPrice());
        productRepository.save(product1);
	}

	public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

	

	
	// @Transactional(readOnly = true)
	public Product getUserById(int id)  {
		Optional<Product> pro = productRepository.findById(id);
		if (pro.isPresent()) {
			Product product = pro.get();
			return product;
		} else {
					}
		return null;
	}
	
	
}



