package com.fdmgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.model.Product;
import com.fdmgroup.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	private ProductRepository productRepo;

	@Autowired
	public ProductService(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}
	
	/**
	 * Retrieves all products.
	 * 
	 * @return List<Product>
	 */
	public List<Product> retrieveProducts(){
		return productRepo.findAll();
	}
	
	/**
	 * Retrieves a specific product by its id.
	 * 
	 * @param productId
	 * @return Product if present, 
	 * runtime exception otherwise.
	 */
	public Product retrieveProductById(Long productId) {
		Optional<Product> productByIdOptional = productRepo.findById(productId);
		if(!productByIdOptional.isPresent()) {
			throw new ProductIdNotFound("Product with Id " + productId + " was not found...");
		}
		return productByIdOptional.get();
	}
	
	/**
	 * Retrieves a specific product by its name.
	 * 
	 * @param productName
	 * @return Product if present,
	 * runtime exception otherwise.
	 */
	public List<Product> retrieveProductsByName(String productName) {
		List<Product> productsByName = productRepo.findByProductName(productName);
		if(productsByName.isEmpty()) {
			throw new ProductNameNotFound("Product with name " + productName + " was not found...");
		}
		return productsByName;
	}
	
	/**
	 * Creates/saves the given product entity.
	 * 
	 * @param product
	 * @return Product (new)
	 */
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}
	
	/**
	 * Finds and updates the existing product entity,
	 * based on the given productId if present,
	 * otherwise throws the runtime exception.
	 * 
	 * @param product
	 * @return Product (updated) if present,
	 * runtime exception otherwise
	 */
	public Product updateProduct(Product product) {
		Optional<Product> prodByIdOptional = productRepo.findById(product.getProductId());
		if(!prodByIdOptional.isPresent()) {
			throw new ProductIdNotFound("Specified product was not found and therefore, cannot be updated...");
		}
		return productRepo.save(product);
	}
	
	/**
	 * Finds and deletes the product entity 
	 * based on the given productId if present,
	 * otherwise throws the runtime exception.
	 * 
	 * @param productId
	 */
	public void deleteProduct(Long productId) {
		Optional<Product> productByIdOptional = productRepo.findById(productId);
		if(!productByIdOptional.isPresent()) {
			throw new ProductIdNotFound("Product with Id " + productId + " was not found and therefore, cannot be deleted...");
		}
		productRepo.deleteById(productId);
	}
}
