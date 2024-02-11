package com.Blog_Application.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	
	String resource;
	String fieldName;
	int fieldValue;
	
	public ResourceNotFoundException(String resource,String fieldName,int fieldValue) {
		super(String.format("%s not found  with name %s : %d",resource,fieldName,fieldValue));
		this.resource = resource;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException(String resource, String fieldName, Integer userId) {
		
	}

	
}
