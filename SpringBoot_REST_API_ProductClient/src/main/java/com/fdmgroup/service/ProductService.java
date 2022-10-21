package com.fdmgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.model.Product;

import java.util.List;

@Service
public class ProductService {

	private ProductRestClient productClient;

	@Autowired
	public ProductService(ProductRestClient productClient) {
		super();
		this.productClient = productClient;
	}
	
	/**
	 * Communicates with our server 
	 * (.getProducts() from ProductWebClient),
	 * gets (GET) the all the products and
	 * returns them.
	 * 
	 * @return List<Product> all the products
	 */
	public List<Product> getProducts(){
		return productClient.getProducts();
	}
	
	/**
	 * Communicates with our server 
	 * (.getProductById() from ProductWebClient),
	 * gets (GET) the product based on Id if exists 
	 * and returns it.
	 * 
	 * @param id product ID
	 * @return Product product entity
	 */
	public Product getProductById(Long id) {
		return productClient.getProductById(id);
	}
	
	/**
	 * Communicates with our server 
	 * (.getProductsByName() from ProductWebClient),
	 * gets (GET) the product based on name if exists 
	 * and returns it.
	 * 
	 * @param productName name of the product
	 * @return Product product entity
	 */
	public List<Product> getProductsByName(String productName){
		return productClient.getProductsByName(productName);
	}
	
	/**
	 * Communicates with our server 
	 * (.createProduct() from ProductWebClient),
	 * creates (POST) the product and returns it.
	 * 
	 * @param product product entity
	 * @return Product new product entity
	 */
	public Product createProduct(Product product) {
		return productClient.createProduct(product);
	}
	
	/**
	 * Communicates with our server 
	 * (.updateProduct() from ProductWebClient),
	 * updates (PUT) the product if exists 
	 * and returns it.
	 * 
	 * @param id product ID
	 * @param product product entity
	 * @return Product updated product entity
	 */
	public Product updateProduct(Long id, Product product) {
		return productClient.updateProduct(id, product);
	}
	
	/**
	 * Communicates with our server 
	 * (.deleteProduct() from ProductWebClient),
	 * deletes (DELETE) the product if exists 
	 * and returns nothing.
	 * 
	 * @param id product ID
	 */
	public void deleteProduct(Long id) {
		productClient.deleteProduct(id);
	}
}
