package com.fdmgroup.model;

import javax.validation.constraints.NotBlank;

public class Product {

	private Long productId;
	@NotBlank(message = "Product Name cannot be blank")
	private String productName;
	@NotBlank(message = "Product Type cannot be blank")
	private String productType;
	private String productSize;
	
	
	public Product() {
		super();
	}

	public Product(Long productId, String productName, String productType, String productSize) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
		this.productSize = productSize;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productType=" + productType
				+ ", productSize=" + productSize + "]";
	}
}
