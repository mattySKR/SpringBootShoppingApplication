package com.fdmgroup.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.model.Product;
import com.fdmgroup.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

	private ProductService productService;

	
	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	
	@Operation(summary = "Gets all the products")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200",
						 description = "Gets data successfully",
						 content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")})})
	@GetMapping
	public ResponseEntity<List<Product>> getProducts(){
		List<Product> products = productService.retrieveProducts();
		return ResponseEntity.ok(products);
	}
	
	
	@Operation(summary = "Gets a product based on the provided id")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200",
						 description = "Gets data successfully",
						 content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")},
						 headers = {@Header(name = "location", description = "id/{id}")}), 
			@ApiResponse(responseCode = "404", 
						 description = "Product Not Found")})
	@GetMapping("id/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId) {
		Product product = productService.retrieveProductById(productId);
		return ResponseEntity.ok(product);
	}
	
	
	@Operation(summary = "Gets a product based on the provided productName")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200",
						 description = "Gets data successfully",
						 content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")},
						 headers = {@Header(name = "location", description = "productName/{productName}")}), 
			@ApiResponse(responseCode = "404", 
						 description = "Product Not Found")})
	@GetMapping("productName/{productName}")
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable("productName") String productName) {
		List<Product> productsByName = productService.retrieveProductsByName(productName);
		return ResponseEntity.ok(productsByName);
	}
	
	
	@Operation(summary = "Creates a new product")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200",
						 description = "Creates data successfully",
						 content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")})})
	@PostMapping
	public ResponseEntity<Product> createContact(@Valid @RequestBody Product product) {
		Product createdProduct = productService.createProduct(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdProduct.getProductId()).toUri();
		return ResponseEntity.created(location).body(createdProduct);
	}
	
	
	@Operation(summary = "Updates the product")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200",
						 description = "Updates data successfully",
						 content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")},
						 headers = {@Header(name = "location", description = "id/{id}")}), 
			@ApiResponse(responseCode = "404", 
						 description = "Product Not Found")})
	@PutMapping("id/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long productId, @Valid @RequestBody Product product){
		Product updatedProduct = productService.updateProduct(product);
		return ResponseEntity.ok(updatedProduct);
	}
	
	@Operation(summary = "Deletes the product")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200",
						 description = "Deletes data successfully",
						 content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")},
						 headers = {@Header(name = "location", description = "id/{id}")}), 
			@ApiResponse(responseCode = "404", 
						 description = "Product Not Found")})
	@DeleteMapping("id/{id}")
	public void deleteProduct(@PathVariable("id") Long productId) {
		productService.deleteProduct(productId);
	}
}
