package com.fdmgroup.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNameNotFound extends RuntimeException {

	public ProductNameNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
