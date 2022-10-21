package com.fdmgroup.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductIdNotFound extends RuntimeException {

	public ProductIdNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
