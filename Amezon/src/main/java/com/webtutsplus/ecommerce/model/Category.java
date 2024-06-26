package com.webtutsplus.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Entity
@Table(name = "categories")
@Validated
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 512, unique = true)
    @NotBlank(message = "Product name cannot be blank")
    private @NotBlank String categoryName;

    @Column(nullable = false, length = 512, unique = true)
    @NotBlank(message = "Product description cannot be blank")
    private @NotBlank String description;

    private @NotBlank String imageUrl;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products;

    public Category() {}

    public Category(@NotBlank String categoryName, @NotBlank String description, @NotBlank String imageUrl) {
        this.categoryName = categoryName;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Category(@NotBlank String categoryName, @NotBlank String description, @NotBlank String imageUrl, Set<Product> products) {
        this.categoryName = categoryName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.products = products;
    }
    
}
