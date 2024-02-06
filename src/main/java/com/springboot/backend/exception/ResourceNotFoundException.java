package com.springboot.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private static String fieldName;
	private Object fieldValue;
	
	//Constructor
	public ResourceNotFoundException(String resourceName, String feildName, Object fieldValue) {
		super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = feildName;
		this.fieldValue = fieldValue;
	}

	//Getter methods
	public String getResourceName() {
		return resourceName;
	}

	public static String getFieldName() {
		return fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}
	
	

}
