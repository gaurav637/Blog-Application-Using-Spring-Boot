package com.Blog_Application.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class modelMapperConversion {
	
	
	@Bean
	public ModelMapper modelMapper() {// conversion 
		return new ModelMapper();// ModelMapper obj = new ModelMapper();
	}

}
