package com.fdmgroup.service;

import java.util.List;

import com.fdmgroup.model.Product;

public interface ProductRestClient {
	
	List<Product> getProducts();
	Product getProductById(Long id);
	List<Product> getProductsByName(String productName); 
	Product createProduct(Product product);
	Product updateProduct(Long id, Product product);
	void deleteProduct(Long id);
}
