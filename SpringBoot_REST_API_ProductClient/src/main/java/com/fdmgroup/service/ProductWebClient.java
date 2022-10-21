package com.fdmgroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.model.Product;

import reactor.core.publisher.Mono;

@Component
public class ProductWebClient implements ProductRestClient{

	private WebClient productRestWebClient;
	
	
	@Autowired
	public ProductWebClient(WebClient productRestWebClient) {
		super();
		this.productRestWebClient = productRestWebClient;
	}

	/**
	 * 1. Communicate with our URL "localhost:8080/api/v1/products",
	 * 2. sends get request to that URL,
	 * 3. retrieves the response
	 * 4. maps it to the list of products (Flux)
	 * 5. converts it into a list
	 * 6. encapsulate it in the body of the HTTP Response.
	 * 
	 * @return HTTP response block with multiple Product entities
	 */
	@Override
	public List<Product> getProducts() {
		return productRestWebClient.get()
				.retrieve()
				.bodyToFlux(Product.class)
				.collectList()
				.block();
	}

	/**
	 * Gets the product based on the id.
	 * Essentially appends the /id/{id} to
	 * our URI, receives the response with
	 * Product entity, throws an exception if 
	 * product not found.
	 * 
	 * @param id productId
	 * @return HTTP response block with one Product entity
	 */
	@Override
	public Product getProductById(Long id) {
		return productRestWebClient.get()
				.uri("/id/" + id)
				.retrieve()
				.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
				response -> Mono.error(new ProductNotFound("Product with " + id + " was not found...")))
				.bodyToMono(Product.class)
				.block();
	}
	
	/**
	 * Gets the product based on the name.
	 * Essentially appends the 
	 * /productName/{productName} to
	 * our URI, receives the response with
	 * Product entity, throws an exception if 
	 * product not found.
	 * 
	 * @param id productId
	 * @return HTTP response block with one Product entity
	 */
	@Override
	public List<Product> getProductsByName(String productName) {
		return productRestWebClient.get()
				.uri("/productName/" + productName)
				.retrieve()
				.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
				response -> Mono.error(new ProductNameNotFound("Product with " + productName + " was not found...")))
				.bodyToFlux(Product.class)
				.collectList()
				.block();
	}

	/**
	 * Creates the product entity,
	 * throws it into the body, creates
	 * the request, retrieves the response(mono),
	 * and finally blocks it all.
	 * (No URI needs to be added since we can
	 * POST at the base).
	 * 
	 * @param product product entity
	 * @return HTTP response block with one Product entity
	 */
	@Override
	public Product createProduct(Product product) {
		return productRestWebClient.post()
				.bodyValue(product)
				.retrieve()
				.bodyToMono(Product.class)
				.block();
	}

	/**
	 * Updates the product entity,
	 * throws it into the body, creates
	 * the request, retrieves the response(mono),
	 * and finally blocks it all.
	 * (URI "/id/{id}" was added for PUT).
	 * 
	 * @param id productId
	 * @param product product entity
	 * @return
	 */
	@Override
	public Product updateProduct(Long id, Product product) {
		return productRestWebClient.put()
				.uri("/id/" + product.getProductId())
				.bodyValue(product)
				.retrieve()
				.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
				response -> Mono.error(new ProductNotFound("Specified product was not found and therefore, cannot be updated...")))
				.bodyToMono(Product.class)
				.block();
	}

	/**
	 * Deletes the product entity,
	 * throws it into the body, creates
	 * the request, retrieves BodilessEntity
	 * since we are deleting,
	 * and finally blocks it all.
	 * (URI "/id/{id}" was added for DELETE).
	 * @param id productId
	 */
	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		productRestWebClient.delete()
		.uri("/id/" + id)
		.retrieve()
		.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
		response -> Mono.error(
				new ProductNotFound("Product with Id " + id + " was not found and therefore, cannot be deleted...")))
		.toBodilessEntity()
		.block();
	}
}
