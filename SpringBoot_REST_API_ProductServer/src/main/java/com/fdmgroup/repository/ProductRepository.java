package com.fdmgroup.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * Custom query that finds the product by its name. 
	 * (Even if the substring of the productName is inputed,
	 * the related products will still be found by the query).
	 * 
	 * @param productName
	 * @return List<Product> or just an
	 * empty list if productName was not found
	 */
	@Query("SELECT p FROM Product p WHERE UPPER(p.productName) LIKE CONCAT('%',UPPER(:productName),'%')")
	List<Product> findByProductName(@Param("productName") String productName);
}
