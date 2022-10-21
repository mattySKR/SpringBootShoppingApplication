package com.fdmgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.model.Product;
import com.fdmgroup.service.ProductService;

import java.util.List;

import javax.validation.Valid;

@Controller
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	/**
	 * Gets all the products and
	 * adds them to the model.
	 * 
	 * @param model for thymeleaf
	 * @return "list-product" view
	 */
	@GetMapping
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		return "list-product";
	}
	
	/**
	 * Gets the product based on id
	 * and adds it to the model.
	 * 
	 * @param id product ID
	 * @param model for thymeleaf
	 * @return "detail-product-id" view
	 */
	@GetMapping("id/{id}")
	public String getProduct(@PathVariable Long id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "detail-product-id";
	}
	
	// Get product by name method
	
	/**
	 * Creates a new product
	 * and adds it to the model.
	 * @param model for thymeleaf
	 * @return "create-product" view
	 */
	@GetMapping("/createProduct")
	public String createProduct(Model model) {
		model.addAttribute("product", new Product());
		return "create-product";
	}
	
	/**
	 * Saves the product if no errors
	 * come up, otherwise will give an error
	 * and return the user to the same page.
	 * 
	 * @param product product entity
	 * @param result for checking validations
	 * @param model for thymeleaf
	 * @return redirection to the newly created contact
	 */
	@PostMapping("/createProduct")
	public String saveProduct(@Valid Product product, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "create-product";
		}
		Product savedProduct = productService.createProduct(product);
		return "redirect:/id/" + savedProduct.getProductId();
	}
	
	// Update method
	
	// Delete method
}
